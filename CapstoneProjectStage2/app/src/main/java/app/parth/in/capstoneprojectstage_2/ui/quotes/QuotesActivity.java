package app.parth.in.capstoneprojectstage_2.ui.quotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app.parth.in.capstoneprojectstage_2.R;
import app.parth.in.capstoneprojectstage_2.model.Quotes;

public class QuotesActivity extends AppCompatActivity implements QuotesAdapter.ClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_white);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        String categoryName = getIntent().getStringExtra("EXTRA_CATEGORY_NAME");
        String authorName = getIntent().getStringExtra("EXTRA_AUTHOR_NAME");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            if (categoryName == null) {
                actionBar.setTitle(authorName);
            } else {
                actionBar.setTitle(categoryName);
            }
        }
        final RecyclerView recyclerView = findViewById(R.id.quotes_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Initialize database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        // Access quotes table
        DatabaseReference mQuotesReference = database.getReference("quotes");

        // Attach a listener to read the data at our posts reference
        ValueEventListener quotesListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Quotes> quotesList = new ArrayList<>();

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Quotes quotes = child.getValue(Quotes.class);
                    quotesList.add(quotes);
                }

                QuotesAdapter mAdapter = new QuotesAdapter(quotesList, QuotesActivity.this);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        };

        if (categoryName != null) {
            mQuotesReference.orderByChild("category").equalTo(categoryName).addValueEventListener(quotesListener);
        } else {
            mQuotesReference.orderByChild("author").equalTo(authorName).addValueEventListener(quotesListener);
        }
    }

    @Override
    public void onClickListener(Quotes quotes) {
        Intent intent = new Intent(this, QuoteDetailsActivity.class);
        intent.putExtra("EXTRA_AUTHOR_NAME", quotes.getAuthor());
        intent.putExtra("EXTRA_TITLE_NAME", quotes.getTitle());
        intent.putExtra("EXTRA_CATEGORY_NAME", quotes.getCategory());
        startActivity(intent);
    }
}
