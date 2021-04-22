package rssmail.spam.model;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.stereotype.Component;
import rssmail.spam.repositories.RssRepositoryManager;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class RssContent {
    RssRepositoryManager rssRepositoryManager;

    public RssContent(RssRepositoryManager rssRepositoryManager) {
        this.rssRepositoryManager = rssRepositoryManager;
    }

    public String createMail() throws IOException, FeedException {
        StringBuilder stringBuilder = new StringBuilder();
        return createMailContentList().stream()
                .filter(r -> !r.equals("undefined"))
                .filter(r -> !r.isEmpty())
                .filter(r -> !r.isBlank())
                .map(x -> stringBuilder.append(x + "\n"))
                .collect(Collectors.joining());
    }

    public List<String> createMailContentList() throws IOException, FeedException {
        return buildContent().stream()
                .flatMap(x -> x.stream()).collect(Collectors.toList());
    }

    public List<List<String>> buildContent() throws IOException, FeedException {
        return getAllRssAddress().stream().map(url -> {
            try {
                return getTitleStringList(url);
            } catch (FeedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
    }

    private List<String> getTitleStringList(String url) throws FeedException, IOException {
        SyndFeed feed = new SyndFeedInput().build(new XmlReader(new URL(url)));
        return Stream.of(feed.getEntries().stream())
                .flatMap(x -> x.map(z -> z.getTitle()))
                .collect(Collectors.toList());
    }

    public List<String> getAllRssAddress() {
        return rssRepositoryManager.findAll()
                .stream().map(x -> x.getRssAddress())
                .collect(Collectors.toList());
    }
}
