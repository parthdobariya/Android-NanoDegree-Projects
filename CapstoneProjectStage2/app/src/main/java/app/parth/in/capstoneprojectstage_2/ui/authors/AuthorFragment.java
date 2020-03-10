package app.parth.in.capstoneprojectstage_2.ui.authors;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app.parth.in.capstoneprojectstage_2.R;
import app.parth.in.capstoneprojectstage_2.model.Author;
import app.parth.in.capstoneprojectstage_2.ui.quotes.QuotesActivity;

public class AuthorFragment extends Fragment implements AuthorAdapter.ClickListener {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        return inflater.inflate(R.layout.fragment_author, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        final RecyclerView recyclerView = view.findViewById(R.id.author_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi

            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to mobile data
            }
        } else {
            // not connected to the internet
            TextView author_tv = view.findViewById(R.id.author_status_tv);
            author_tv.setText(getResources().getString(R.string.Internet_not_available));
        }

        // Initialize database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        // Access authors table
        DatabaseReference mCategoriesReference = database.getReference("authors");

        ValueEventListener categoriesListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Author> mAuthorList = new ArrayList<>();

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    mAuthorList.add(child.getValue(Author.class));
                }

                recyclerView.setAdapter(new AuthorAdapter(mAuthorList, AuthorFragment.this));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        };

        mCategoriesReference.addValueEventListener(categoriesListener);
    }

    @Override
    public void onClickListener(Author author) {
        Intent intent = new Intent(getContext(), QuotesActivity.class);
        intent.putExtra("EXTRA_AUTHOR_NAME", author.getName());
        startActivity(intent);
    }
}