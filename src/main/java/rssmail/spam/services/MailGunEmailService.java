package rssmail.spam.services;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.rometools.rome.io.FeedException;
import org.springframework.stereotype.Service;
import rssmail.spam.model.RssContent;

import java.io.IOException;


@Service
public class MailGunEmailService {

    private String password;
    private String messagesUrl;
    private String username;
    private RssContent rssContent;


    public MailGunEmailService(String mailGunAPIMessagesUrl, String mailGunAPIUsername,
                               String mailGunAPIPassword, RssContent rssContent) {
        this.username = mailGunAPIUsername;
        this.password = mailGunAPIPassword;
        this.messagesUrl = mailGunAPIMessagesUrl;
        this.rssContent = rssContent;
    }


    public void sendHTML(String to, String subject, String content) {
                    try {
                        sendInlineImage(to,subject,content);

                    } catch (IOException | UnirestException | FeedException e) {
                        e.printStackTrace();
                    }
    }

    public   HttpResponse<String> sendInlineImage(String to, String subject, String content) throws UnirestException, IOException, FeedException {

        HttpResponse<String> response = Unirest.post(messagesUrl)
                .basicAuth(username, password)
                .field("from", "WSB project grzesiekkedzior@gmail.com")
                .field("to", to)
                .field("subject", subject)
                .field("text", "Testing out some Mailgun awesomeness!")
                .field("html", "<html><div>"+ rssContent.createMail()+ "</div> <img src=\"cid:"+"\"></html>")
                .asString();

        return response;
    }

}
