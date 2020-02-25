package app.parth.in.capstoneprojectstage_2.ui.quotes;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import app.parth.in.capstoneprojectstage_2.R;

public class QuoteDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String authorName = getIntent().getStringExtra("EXTRA_AUTHOR_NAME");
        String titleName = getIntent().getStringExtra("EXTRA_TITLE_NAME");

        TextView textViewAuthor = (TextView) findViewById(R.id.author_name);
        TextView textViewTitle = (TextView) findViewById(R.id.title_name);

        textViewAuthor.setText(authorName);
        textViewTitle.setText(titleName);
        
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
