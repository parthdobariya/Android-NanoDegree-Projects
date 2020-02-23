package app.parth.in.capstoneprojectstage_2.model;

public class Favourite {
    String name;
    String author;

    public Favourite(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }
}
