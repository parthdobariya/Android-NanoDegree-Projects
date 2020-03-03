package app.parth.in.capstoneprojectstage_2.database;

import java.util.List;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface QuoteDao {

    @Query("SELECT * FROM quote")
    List<Quote> getAll();

    @Query("SELECT * FROM quote WHERE author = :author AND title = :title AND category = :category")
    Quote getQuote(String author, String title, String category);

    @Delete
    void delete(Quote quote);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Quote quote);
}
