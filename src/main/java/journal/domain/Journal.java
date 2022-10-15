package journal.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Journal {
    private String title;
    private Contacts contacts = new Contacts();
    private List<Articles> articlesList = new ArrayList<>();

    public Journal() {
    }

    public Journal(String title, Contacts contacts, List<Articles> articlesList) {
        this.title = title;
        this.contacts = contacts;
        this.articlesList = articlesList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    public List<Articles> getArticlesList() {
        return articlesList;
    }

    public void setArticlesList(List<Articles> articlesList) {
        this.articlesList = articlesList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Journal journal = (Journal) o;
        return Objects.equals(title, journal.title) && Objects.equals(contacts, journal.contacts) && Objects.equals(articlesList, journal.articlesList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, contacts, articlesList);
    }

    @Override
    public String toString() {
        return "---------Journal--------- \n" + "Title: " + title + "\n" + contacts + "\n" + articlesList;
    }
}
