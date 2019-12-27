package utils;

import base.BaseEntity;
import lombok.SneakyThrows;

import javax.mail.*;
import javax.mail.search.SearchTerm;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static javax.mail.Flags.Flag;

/**
 * Class for working with emails
 */
public class MailUtils extends BaseEntity {
    private static final String SMTP_HOST = config.getSmtpHost();
    private static final String SMTP_USER_LOGIN = "mmihalichenkoo@gmail.com";
    private static final String SMTP_PASSWORD = "9802357s";
    private static final String PROTOCOL = config.getSmtpProtocol();
    private static final int TIMEOUT_IN_SECONDS = 50;
    private static final int DELAY_IN_MILLIS = 500;
    private static Store connection;

    /**
     * Gets store connected
     *
     * @return store
     */
    private static synchronized Store getConnection() {
        if (connection == null) {
            connection = getStore();
        }
        return connection;
    }

    /**
     * Connects to java mail
     *
     * @return store
     * @throws IOException
     * @throws MessagingException
     */
    @SneakyThrows
    private static Store getStore() {
        Session session = config.createMailSession();
        Store store = session.getStore(PROTOCOL);
        store.connect(SMTP_HOST, SMTP_USER_LOGIN, SMTP_PASSWORD);
        return store;
    }

    /**
     * Waits for certain message and finds it in specified folder with specified subject and send time
     *
     * @param subject    subject of the message
     * @param folderName folder name
     * @param sendTime   message send time
     * @return message if found
     * @throws MessagingException if message is not found
     */
    public static Message findMessage(String subject, String folderName, Date sendTime) throws MessagingException {
        Store store = getConnection();
        Folder folder = store.getFolder(folderName);
        folder.open(Folder.READ_WRITE);
        int messageCount = folder.getMessageCount();
        log.info("Total Messages:- " + messageCount);
        SearchTerm term = new SearchTerm() {
            @Override
            public boolean match(Message message) {
                try {
                    boolean isMatchSubject = message.getSubject().equals(subject);
                    boolean isSeen = message.isSet(Flag.SEEN);
                    boolean isMessageSentAfterDate = message.getSentDate().after(sendTime);
                    if (isMatchSubject && !isSeen && isMessageSentAfterDate) {
                        return true;
                    }
                } catch (MessagingException ex) {
                    log.error("message not found", ex);
                }
                return false;
            }
        };
        List<Message> messages = waitForList(TIMEOUT_IN_SECONDS, DELAY_IN_MILLIS,
                String.format("Message is not found in %s folder", folderName), () -> Arrays.asList(folder.search(term)));
        return messages.get(0);
    }

    /**
     * Default method that finds message in inbox folder
     *
     * @see #findMessage(String, String, Date)
     */
    public static Message findMessage(String subject, Date sentTime) throws MessagingException {
        return findMessage(subject, "inbox", sentTime);
    }

    /**
     * Find and gets message's content
     *
     * @see #findMessage(String, String, Date)
     */
    public static String getMessageContent(String subject, String folderName, Date sendTime) {
        try {
            Multipart part = (Multipart) Objects.requireNonNull(findMessage(subject, folderName, sendTime)).getContent();
            return (String) part.getBodyPart(0).getContent();
        } catch (IOException | MessagingException ex) {
            log.error("Impossible to get message content", ex);
        }
        return null;
    }

    /**
     * Default method that finds and gets message's content message in inbox folder
     *
     * @see #getMessageContent(String, String, Date)
     */
    public static String getMessageContent(String subject, Date sentTime) {
        return getMessageContent(subject, "inbox", sentTime);
    }

    /**
     * Gets subject from specified message
     *
     * @return message subject
     * @throws MessagingException if message is not found
     * @see #findMessage(String, String, Date)
     */
    public static String getMessageSubject(String subject, String folder, Date sentTime) throws MessagingException {
        return findMessage(subject, folder, sentTime).getSubject();
    }

    /**
     * Default method for getting message subject from inbox folder
     *
     * @see #getMessageSubject(String, String, Date)
     */
    public static String getMessageSubject(String subject, Date sentTime) throws MessagingException {
        return getMessageSubject(subject, "inbox", sentTime);
    }

    /**
     * Deletes all messages from specified folder
     *
     * @param folderName folder name
     */
    public static void deleteAllMessages(String folderName) {
        try {
            Folder folder = getStore().getFolder(folderName);
            folder.open(Folder.READ_WRITE);
            Message[] messages = folder.getMessages();
            for (Message message : messages) {
                message.setFlag(Flag.DELETED, true);
            }
            folder.close(true);
            log.info(String.format("All  messages from %s folder are deleted", folderName));
        } catch (MessagingException ex) {
            log.error("Message deleting error", ex);
        }
    }

    /**
     * Deletes all messages from inbox folder
     *
     * @see #deleteAllMessages(String)
     */
    public static void deleteAllInboxMessages() {
        deleteAllMessages("inbox");
    }

}