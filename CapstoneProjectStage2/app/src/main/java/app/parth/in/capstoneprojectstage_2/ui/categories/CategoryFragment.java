package app.parth.in.capstoneprojectstage_2.ui.categories;

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
import app.parth.in.capstoneprojectstage_2.model.Category;
import app.parth.in.capstoneprojectstage_2.ui.quotes.QuotesActivity;


public class CategoryFragment extends Fragment implements CategoryAdapter.ClickListener {
    
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        final RecyclerView recyclerView = view.findViewById(R.id.category_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // Initialize database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        // Access categories table
        DatabaseReference mCategoriesReference = database.getReference("categories");

        // Attach a listener to read the data at our posts reference
        ValueEventListener categoriesListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Category> mCategoriesList = new ArrayList<>();

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    mCategoriesList.add(new Category((String) child.getValue()));
                }

                CategoryAdapter mAdapter = new CategoryAdapter(mCategoriesList, CategoryFragment.this);
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
    public void onClickListener(Category category) {
        Intent intent = new Intent(getContext(), QuotesActivity.class);
        intent.putExtra("EXTRA_CATEGORY_NAME", category.getName());
        startActivity(intent);
    }
}