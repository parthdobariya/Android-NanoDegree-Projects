package app.parth.in.capstoneprojectstage_2.ui.authors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import app.parth.in.capstoneprojectstage_2.R;
import app.parth.in.capstoneprojectstage_2.model.Author;

public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.AuthorViewHolder> {

    private List<Author> authorList;
    private AuthorAdapter.ClickListener listener;

    AuthorAdapter(List<Author> authorList, AuthorFragment listener) {
        super();
        this.authorList = authorList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AuthorAdapter.AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.author_item_cell, parent, false);

        return new AuthorAdapter.AuthorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorAdapter.AuthorViewHolder holder, final int position) {
        Author author = authorList.get(position);
        holder.categoryText.setText(author.getName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickListener(authorList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return authorList.size();
    }

    interface ClickListener {
        void onClickListener(Author author);
    }

    static class AuthorViewHolder extends RecyclerView.ViewHolder {
        TextView categoryText;
        CardView cardView;

        AuthorViewHolder(View itemView) {
            super(itemView);
            categoryText = itemView.findViewById(R.id.tableno);
            cardView = itemView.findViewById(R.id.table);
        }
    }
}
