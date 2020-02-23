package app.parth.in.capstoneprojectstage_2.ui.quotes;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

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

        RecyclerView recyclerView = findViewById(R.id.quotes_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<Quotes> quotesList = new ArrayList<>();

        quotesList.add(new Quotes("dummy1", "author"));
        quotesList.add(new Quotes("dummy2", "author"));
        quotesList.add(new Quotes("dummy3", "author"));

        QuotesAdapter mAdapter = new QuotesAdapter(quotesList, this);
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onClickListener(Quotes quotes) {


    }
}
