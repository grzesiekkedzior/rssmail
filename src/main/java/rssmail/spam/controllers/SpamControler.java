package rssmail.spam.controllers;

import com.rometools.rome.io.FeedException;
import org.springframework.web.bind.annotation.*;
import rssmail.spam.entities.Mail;
import rssmail.spam.entities.RssAddress;
import rssmail.spam.model.RssContent;
import rssmail.spam.repositories.MailRepositoryManager;
import rssmail.spam.repositories.RssRepositoryManager;

import java.io.IOException;

@CrossOrigin
@RestController
public class SpamControler {
    RssRepositoryManager rssRepositoryManager;
    MailRepositoryManager mailRepositoryManager;
    RssContent rssContent;

    public SpamControler(RssRepositoryManager rssRepositoryManager, MailRepositoryManager mailRepositoryManager, RssContent rssContent) {
        this.rssRepositoryManager = rssRepositoryManager;
        this.mailRepositoryManager = mailRepositoryManager;
        this.rssContent = rssContent;
    }

    // Add rss
    @PostMapping("/rssmail/managedrss")
    public void saveRss(@RequestBody RssAddress rss) {
        rssRepositoryManager.saveRssAddress(rss);
    }

    // Add email
    @PostMapping("/rssmail/managedmail")
    public void saveMailAddress(@RequestBody Mail mail) {
        mailRepositoryManager.saveMail(mail);
    }

    // Preview
    @GetMapping("/rssmail/mailcreator")
    @ResponseBody
    public String getContent() throws IOException, FeedException {
        return rssContent.createMail();
    }
}
