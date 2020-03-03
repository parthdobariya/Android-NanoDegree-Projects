package app.parth.in.capstoneprojectstage_2.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Quote.class}, version = 1)

public abstract class QuoteDatabase extends RoomDatabase {
    public abstract QuoteDao quoteDao();
}
