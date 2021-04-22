package rssmail.spam.repositories;

import org.springframework.stereotype.Component;
import rssmail.spam.entities.Mail;

import javax.mail.internet.AddressException;
import java.util.List;

@Component
public class MailRepositoryManager {
    MailRepository mailRepository;

    public MailRepositoryManager(MailRepository mailRepository) {
        this.mailRepository = mailRepository;
    }

    public void saveMail(Mail mail) {
        mailRepository.save(mail);
    }

    public Mail getMail(String data) throws AddressException {
        return mailRepository.findAllByMailAddress(data).orElseThrow(
                () -> new AddressException("address not found")
        );
    }

    public List<Mail> findAll() {return mailRepository.findAll();}
}
