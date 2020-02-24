package app.parth.in.capstoneprojectstage_2.model;

public class Quotes {
    String author;
    String category;
    String title;

    public Quotes(String author, String category, String title) {
        this.author = author;
        this.category = category;
        this.title = title;
    }

    public Quotes() {
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
