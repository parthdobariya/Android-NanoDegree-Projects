package app.parth.in.capstoneprojectstage_2.ui.favourites;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app.parth.in.capstoneprojectstage_2.R;
import app.parth.in.capstoneprojectstage_2.ui.categories.Quotes;
import app.parth.in.capstoneprojectstage_2.ui.categories.QuotesAdapter;

public class FavouritesActivity extends AppCompatActivity implements FavouriteAdapter.ClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.favourite_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<Favourite> favouriteList = new ArrayList<>();

        favouriteList.add(new Favourite("dummy1", "author"));
        favouriteList.add(new Favourite("dummy2","author"));
        favouriteList.add(new Favourite("dummy3","author"));

        FavouriteAdapter mAdapter = new FavouriteAdapter(favouriteList, this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClickListener(Favourite quotes) {

    }
}
