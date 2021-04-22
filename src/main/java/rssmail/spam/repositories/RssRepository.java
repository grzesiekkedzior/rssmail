package rssmail.spam.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rssmail.spam.entities.RssAddress;

import java.util.List;
import java.util.Optional;

@Repository
public interface RssRepository extends CrudRepository<RssAddress, Integer> {
    Optional<RssAddress> findRssAddressById(int id);

    @Override
    List<RssAddress> findAll();
}
