package utils;

import base.BaseEntity;
import lombok.SneakyThrows;

import javax.mail.*;
import javax.mail.search.SearchTerm;
import java.io.IOException;
import java.util.Arrays;
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


    public static Message getMessage(String subject, String folderName) throws MessagingException {
        try {
            Store store = getConnection();
            Folder folder = store.getFolder(folderName);
            folder.open(Folder.READ_WRITE);
            int messageCount = folder.getMessageCount();
            log.info("Total Messages:- " + messageCount);
            SearchTerm term = new SearchTerm() {
                @Override
                public boolean match(Message message) {
                    try {
                        if (message.getSubject().contains(subject) && !message.isSet(Flags.Flag.SEEN)) {
                            return true;
                        }
                    } catch (MessagingException ex) {
                        ex.printStackTrace();
                    }
                    return false;
                }
            };
            List<Message> messages = waitForList(TIMEOUT_IN_SECONDS, DELAY_IN_MILLIS,
                    String.format("Message is not found in %s folder", folderName), () -> Arrays.asList(folder.search(term)));
            return messages.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Message getMessage(String subject) throws MessagingException {
        log.info(String.valueOf(getMessage(subject, "inbox").getSentDate()));
        return getMessage(subject, "inbox");
    }

    public static String getTextFromMessage(String subject, String folder) {
        try {
            Multipart part = (Multipart) Objects.requireNonNull(getMessage(subject, folder)).getContent();
            return (String) part.getBodyPart(0).getContent();
        } catch (IOException | MessagingException ex) {
            log.error("Impossible to get message content", ex);
        }
        return null;
    }

    public static String getTextFromMessage(String subject) {
        return getTextFromMessage(subject, "inbox");
    }

    public static String getMessageSubject(String folder, String subject) throws MessagingException {
        return Objects.requireNonNull(getMessage(subject, folder)).getSubject();
    }

    public static String getMessageSubject(String subject) throws MessagingException {
        return getMessageSubject("inbox", subject);
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