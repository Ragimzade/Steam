package utils;

import base.BaseEntity;
import org.jsoup.Jsoup;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.SearchTerm;

public class MailUtils extends BaseEntity {
    private static final String SMTP_HOST = config.getSmtpHost();
    private static final String SMTP_USER_LOGIN = "nurlan.rahimzada@gmail.com";
    private static final String SMTP_PASSWORD = "9802357s";
    private static final String PROTOCOL = config.getSmtpProtocol();
    private static final int TIMEOUT = 90;
    private static final int DELAY = 20;

    public static Message getMessage(String subject) {
        Message mail = null;
        try {
            Session session = config.createMailSession();
            Store store = session.getStore(PROTOCOL);
            store.connect(SMTP_HOST, SMTP_USER_LOGIN, SMTP_PASSWORD);
            Folder inbox = store.getFolder("inbox");
            inbox.open(Folder.READ_WRITE);
            int messageCount = inbox.getMessageCount();
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

            getDelay(TIMEOUT, DELAY).until(() -> inbox.search(term).length > 0);
            Message[] messages = inbox.search(term);
            for (Message message : messages) {
                mail = message;
            }
            return mail;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getTextFromMessage(String subject) throws Exception {
        Message message = getMessage(subject);
        if (message.isMimeType("text/plain")) {
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

    public static boolean isMailHasCorrectSubject(String subject) {
        try {
            return getMessage(subject).getSubject().contains(subject);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }
}