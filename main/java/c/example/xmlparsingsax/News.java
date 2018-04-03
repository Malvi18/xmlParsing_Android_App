package c.example.xmlparsingsax;

/**
 * Created by Dell on 08-02-2018.
 */

class News {
    private String title,link,pubDate,description,fullImage;

    public News() {
        this.title = title;
        this.link = link;
        this.pubDate = pubDate;
        this.description = description;
        this.fullImage = fullImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFullImage() {
        return fullImage;
    }

    public void setFullImage(String fullImage) {
        this.fullImage = fullImage;
    }
}
