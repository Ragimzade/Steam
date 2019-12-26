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

public class MailUtils extends BaseEntity {
    private static final String SMTP_HOST = config.getSmtpHost();
    private static final String SMTP_USER_LOGIN = "mmihalichenkoo@gmail.com";
    private static final String SMTP_PASSWORD = "9802357s";
    private static final String PROTOCOL = config.getSmtpProtocol();
    private static final int TIMEOUT_IN_SECONDS = 50;
    private static final int DELAY_IN_MILLIS = 500;
    private static Store connection;

    private static synchronized Store getConnection() {
        if (connection == null) {
            connection = getStore();
        }
        return connection;
    }

    @SneakyThrows
    private static Store getStore() {
        Session session = config.createMailSession();
        Store store = session.getStore(PROTOCOL);
        store.connect(SMTP_HOST, SMTP_USER_LOGIN, SMTP_PASSWORD);
        return store;
    }

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
                    boolean isEqualSubject = message.getSubject().equals(subject);
                    boolean isSeen = message.isSet(Flags.Flag.SEEN);
                    boolean isMessageSentAfterDate = message.getSentDate().after(sendTime);
                    if (isEqualSubject && !isSeen && isMessageSentAfterDate) {
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

    public static Message findMessage(String subject, Date sentTime) throws MessagingException {
        return findMessage(subject, "inbox", sentTime);
    }

    public static String getMessageContent(String subject, String folderName, Date sendTime) {
        try {
            Multipart part = (Multipart) Objects.requireNonNull(findMessage(subject, folderName, sendTime)).getContent();
            return (String) part.getBodyPart(0).getContent();
        } catch (IOException | MessagingException ex) {
            log.error("Impossible to get message content", ex);
        }
        return null;
    }

    public static String getMessageContent(String subject, Date sentTime) {
        return getMessageContent(subject, "inbox", sentTime);
    }

    public static String getMessageSubject(String subject, String folder, Date sentTime) throws MessagingException {
        return findMessage(subject, folder, sentTime).getSubject();
    }

    public static String getMessageSubject(String subject, Date sentTime) throws MessagingException {
        return getMessageSubject(subject, "inbox", sentTime);
    }

    public static void deleteAllMessages(String folderName) {
        try {
            Folder folder = getStore().getFolder(folderName);
            folder.open(Folder.READ_WRITE);
            Message[] messages = folder.getMessages();
            for (Message message : messages) {
                message.setFlag(Flags.Flag.DELETED, true);
            }
            folder.close(true);
            log.info(String.format("All  messages from %s folder are deleted", folderName));
        } catch (MessagingException ex) {
            log.error("Message deleting error", ex);
        }
    }

    public static void deleteAllInboxMessages() {
        deleteAllMessages("inbox");
    }

}