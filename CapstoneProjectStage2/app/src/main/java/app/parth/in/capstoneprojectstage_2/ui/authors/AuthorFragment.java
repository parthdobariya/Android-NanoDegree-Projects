package app.parth.in.capstoneprojectstage_2.ui.authors;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import app.parth.in.capstoneprojectstage_2.ui.categories.QuotesActivity;

public class AuthorFragment extends Fragment implements AuthorAdapter.ClickListener {

    private ArrayList<Author> mAuthorList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        return inflater.inflate(R.layout.fragment_author, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        final RecyclerView recyclerView = view.findViewById(R.id.author_recyclerview);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mCategoriesReference = database.getReference("authors");

        ValueEventListener categoriesListener = new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    mAuthorList.add(child.getValue(Author.class));
                }

                AuthorAdapter mAdapter = new AuthorAdapter(mAuthorList, AuthorFragment.this);
                recyclerView.setAdapter(mAdapter);
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
        Intent i = new Intent(getContext(), QuotesActivity.class);
        startActivity(i);
    }

}