package journal.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Articles {
    private String id;
    private String title;
    private String author;
    private String url;
    private List<Hotkeys> hotkeys = new ArrayList<>();

    public Articles() {
    }

    public Articles(String id, String title, String author, String url, List<Hotkeys> hotkeys) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.url = url;
        this.hotkeys = hotkeys;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Hotkeys> getHotkeys() {
        return hotkeys;
    }

    public void setHotkeys(List<Hotkeys> hotkeys) {
        this.hotkeys = hotkeys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Articles articles = (Articles) o;
        return id == articles.id && Objects.equals(title, articles.title) && Objects.equals(author, articles.author) && Objects.equals(url, articles.url) && Objects.equals(hotkeys, articles.hotkeys);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, url, hotkeys);
    }

    @Override
    public String toString() {
        return "Articles " + id + ": title = " + title + ", author = " + author + ", url =" + url + ", hotkeys " + hotkeys;
    }
}
