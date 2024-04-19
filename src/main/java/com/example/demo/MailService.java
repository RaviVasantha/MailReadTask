package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import java.util.Properties;

@Service
public class MailService {

    @Value("${mail.host}")
    private String host;

    @Value("${mail.port}")
    private int port;

    @Value("${mail.username}")
    private String username;

    @Value("${mail.password}")
    private String password;

    public void readEmails() {
        try {
            Properties properties = new Properties();
            properties.put("mail.store.protocol", "imaps");
            properties.put("mail.imap.host", host);
            properties.put("mail.imap.port", port);
            properties.put("mail.imap.ssl.trust", "*");

            Session session = Session.getDefaultInstance(properties);
            Store store = session.getStore("imaps");
            store.connect(host, username, password);

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            Message[] messages = inbox.getMessages();

            for (Message message : messages) {
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + InternetAddress.toString(message.getFrom()));
                System.out.println("Body: " + message.getContent());
            }
            inbox.close(false);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



