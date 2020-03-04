package app.parth.in.capstoneprojectstage_2.ui.favourites;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import app.parth.in.capstoneprojectstage_2.R;
import app.parth.in.capstoneprojectstage_2.database.Quote;
import app.parth.in.capstoneprojectstage_2.database.QuoteDatabase;
import app.parth.in.capstoneprojectstage_2.model.Quotes;
import app.parth.in.capstoneprojectstage_2.ui.quotes.QuoteDetailsActivity;

public class FavouritesFragment extends Fragment implements FavouriteAdapter.ClickListener {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        return inflater.inflate(R.layout.fragment_favourites, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        final RecyclerView recyclerView = view.findViewById(R.id.favourite_recycler_view);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        final TextView textView = view.findViewById(R.id.status_tv);
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            // connected to the internet
            Toast.makeText(getContext(), "Network connection is available", Toast.LENGTH_SHORT).show();
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi

            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to mobile data
            }
        } else {
            // not connected to the internet
            Toast.makeText(getContext(), "Network connection is not available", Toast.LENGTH_SHORT).show();
            textView.setText("Network connection is not available");
        }

        QuoteDatabase quoteDatabase = Room.databaseBuilder(getContext(), QuoteDatabase.class, "quote_db")
                .allowMainThreadQueries().build();

        List<Quote> quoteDaoList = quoteDatabase.quoteDao().getAll();

        List<Quotes> quoteList = new ArrayList<>();
        for (Quote quoteDao : quoteDaoList) {
            quoteList.add(new Quotes(quoteDao.getAuthor(), quoteDao.getCategory(), quoteDao.getTitle()));
        }

        recyclerView.setAdapter(new FavouriteAdapter(quoteList, FavouritesFragment.this));
    }

    @Override
    public void onClickListener(Quotes quotes) {
        Intent intent = new Intent(getContext(), QuoteDetailsActivity.class);
        intent.putExtra("EXTRA_AUTHOR_NAME", quotes.getAuthor());
        intent.putExtra("EXTRA_TITLE_NAME", quotes.getTitle());
        intent.putExtra("EXTRA_CATEGORY_NAME", quotes.getCategory());
        startActivity(intent);
    }
}
