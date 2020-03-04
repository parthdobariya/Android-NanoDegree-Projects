package app.parth.in.capstoneprojectstage_2.ui.quotes;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;
import app.parth.in.capstoneprojectstage_2.R;
import app.parth.in.capstoneprojectstage_2.database.Quote;
import app.parth.in.capstoneprojectstage_2.database.QuoteDatabase;

public class QuoteDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_white);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        final String authorName = getIntent().getStringExtra("EXTRA_AUTHOR_NAME");
        final String titleName = getIntent().getStringExtra("EXTRA_TITLE_NAME");
        final String categoryName = getIntent().getStringExtra("EXTRA_CATEGORY_NAME");

        TextView textViewAuthor = findViewById(R.id.author_name);
        TextView textViewTitle = findViewById(R.id.title_name);

        textViewAuthor.setText(authorName);
        textViewTitle.setText(titleName);

        final QuoteDatabase quoteDatabase = Room.databaseBuilder(getApplicationContext(), QuoteDatabase.class, "quote_db")
                .allowMainThreadQueries().build();

        Quote quote = quoteDatabase.quoteDao().getQuote(authorName, titleName, categoryName);

        final FloatingActionButton fabLike = findViewById(R.id.like_btn);
        final FloatingActionButton fabUnlike = findViewById(R.id.unlike_btn);

        fabLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Quote quotes = new Quote(authorName, categoryName, titleName);

                quoteDatabase.quoteDao().delete(quotes);
                Toast.makeText(QuoteDetailsActivity.this, "Removed From Favourite",
                        Toast.LENGTH_LONG).show();
                fabLike.hide();
                fabUnlike.show();
            }
        });

        fabUnlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Quote quotes = new Quote(authorName, categoryName, titleName);
                quoteDatabase.quoteDao().insert(quotes);
                Toast.makeText(QuoteDetailsActivity.this, "Added To Favourite",
                        Toast.LENGTH_LONG).show();
                fabLike.show();
                fabUnlike.hide();
            }
        });

        if (quote == null) {
            // Data not found
            fabLike.hide();
            fabUnlike.show();
        } else {
            // Data found
            fabLike.show();
            fabUnlike.hide();
        }
    }
}
