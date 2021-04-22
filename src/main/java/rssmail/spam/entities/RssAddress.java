package rssmail.spam.entities;

import javax.persistence.*;

@Entity
public class RssAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String rssAddress;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRssAddress() {
        return rssAddress;
    }

    public void setRssAddress(String rssAddress) {
        this.rssAddress = rssAddress;
    }
}
