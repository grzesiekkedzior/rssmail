package rssmail.spam.repositories;

import org.springframework.stereotype.Component;
import rssmail.spam.entities.RssAddress;

import javax.mail.internet.AddressException;
import java.util.List;

@Component
public class RssRepositoryManager {
    private RssRepository rssRepository;

    public RssRepositoryManager(RssRepository rssRepository) {
        this.rssRepository = rssRepository;
    }

    public void saveRssAddress(RssAddress rssAddress) {
        rssRepository.save(rssAddress);
    }

    public RssAddress getRssAddress(int id) throws AddressException {
        return rssRepository.findRssAddressById(id).orElseThrow(
                () -> new AddressException("Address not found")
        );
    }

    public List<RssAddress> findAll() {
        return rssRepository.findAll();
    }
}
