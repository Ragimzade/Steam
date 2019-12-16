package utils;

import base.BaseEntity;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.SearchTerm;
import java.util.Objects;

public class MailUtils extends BaseEntity {
    private static final String SMTP_HOST = config.getSmtpHost();
    private static final String SMTP_USER_LOGIN = "nurlan.rahimzada@gmail.com";
    private static final String SMTP_PASSWORD = "9802357s";
    private static final String PROTOCOL = config.getSmtpProtocol();
    private static final int TIMEOUT_IN_SECONDS = 90;
    private static final int DELAY_IN_MILLIS = 5000;
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

    public static Message getMessage(String subject, String folderName) {
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
            getDelay(TIMEOUT_IN_SECONDS, DELAY_IN_MILLIS).until(() -> folder.search(term).length > 0);
            Message[] messages = folder.search(term);
            return messages[0];
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getTextFromMessage(String subject) throws Exception {
        Message message = getMessage(subject, "inbox");
        if (Objects.requireNonNull(message).isMimeType("text/plain")) {
            return message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            StringBuilder result = new StringBuilder();
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            int count = mimeMultipart.getCount();
            for (int i = 0; i < count; i++) {
                BodyPart bodyPart = mimeMultipart.getBodyPart(i);
                if (bodyPart.isMimeType("text/plain")) {
                    result.append("\n").append(bodyPart.getContent());
                    break;
                } else if (bodyPart.isMimeType("text/html")) {
                    String html = (String) bodyPart.getContent();
                    result.append("\n").append(Jsoup.parse(html).text());
                }
            }
            message.setFlag(Flags.Flag.SEEN, true);
            return result.toString();
        }
        return "";
    }

    public static boolean isMailHasCorrectSubject(String subject, String folder) {
        try {
            return Objects.requireNonNull(getMessage(subject, folder)).getSubject().equals(subject);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void deleteAllMessages(String folderName) {
        try {
            Folder inbox = getStore().getFolder(folderName);
            inbox.open(Folder.READ_WRITE);
            Message[] messages = inbox.getMessages();
            for (Message message : messages) {
                message.setFlag(Flags.Flag.DELETED, true);
            }
            inbox.close(true);
            log.info("Messages are deleted");
        } catch (MessagingException e) {
            log.error(String.format("Somethong gone wrong, exception:: %s", e));

        }
    }

}