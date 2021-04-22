package rssmail.spam.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rssmail.spam.entities.Mail;
import rssmail.spam.entities.RssAddress;

import java.util.List;
import java.util.Optional;

@Repository
public interface MailRepository extends CrudRepository<Mail, Integer> {
    Optional<Mail> findAllByMailAddress(String mailAddress);
    @Override
    List<Mail> findAll();
}
