package app.parth.in.capstoneprojectstage_2.database;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Quote {
    @NonNull
    @PrimaryKey
    public String author;

    public String category;

    public String title;

    public Quote(@NonNull String author, String category, String title) {
        this.author = author;
        this.category = category;
        this.title = title;
    }

    public Quote(String s) {

    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
