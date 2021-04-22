package rssmail.spam.config;


import com.rometools.rome.io.FeedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rssmail.spam.model.RssContent;
import rssmail.spam.repositories.MailRepositoryManager;
import rssmail.spam.repositories.RssRepositoryManager;
import rssmail.spam.services.MailGunEmailService;

import java.io.IOException;

@RestController
@RequestMapping("/emails")
public class EmailController {

    private final MailGunEmailService mailGunEmailService;
    private final MailRepositoryManager mailRepositoryManager;
    private final RssContent rssContent;

    public EmailController(MailGunEmailService mailGunEmailService, MailRepositoryManager mailRepositoryManager, RssRepositoryManager rssRepositoryManager, RssContent rssContent) {
        this.mailGunEmailService = mailGunEmailService;
        this.mailRepositoryManager = mailRepositoryManager;
        this.rssContent = rssContent;
    }

    @PostMapping
    @CrossOrigin
    public ResponseEntity<Void> sendEmail(
    ) throws IOException, FeedException {
        mailRepositoryManager.findAll().stream().forEach(x -> {
            try {
                mailGunEmailService.sendHTML(x.getMailAddress(),"RssInfo",rssContent.createMail());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (FeedException e) {
                e.printStackTrace();
            }
        });
        return ResponseEntity.noContent().build();
    }
}
