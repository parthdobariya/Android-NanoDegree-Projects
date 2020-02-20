package app.parth.in.capstoneprojectstage_2.ui.authors;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app.parth.in.capstoneprojectstage_2.R;
import app.parth.in.capstoneprojectstage_2.model.Author;
import app.parth.in.capstoneprojectstage_2.ui.categories.QuotesActivity;

public class AuthorFragment extends Fragment implements AuthorAdapter.ClickListener {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        return inflater.inflate(R.layout.fragment_author, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.author_recyclerview);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        List<Author> categoryList = new ArrayList<>();

//        categoryList.add(new Author("dummy1"));
//        categoryList.add(new Author("dummy2"));
//        categoryList.add(new Author("dummy3"));

        AuthorAdapter mAdapter = new AuthorAdapter(categoryList, this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClickListener(Author author) {

        Intent i = new Intent(getContext(), QuotesActivity.class);
        startActivity(i);
    }

}