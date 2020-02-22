package app.parth.in.capstoneprojectstage_2.ui.authors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import app.parth.in.capstoneprojectstage_2.R;

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
        holder.authorName.setText(author.getName());
        Picasso.get()
                .load(author.getProfilePicture())
                .into(holder.authorProfile_pic);

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
        TextView authorName;
        CardView cardView;
        ImageView authorProfile_pic;

        AuthorViewHolder(View itemView) {
            super(itemView);
            authorName = itemView.findViewById(R.id.author_tv);
            cardView = itemView.findViewById(R.id.card_view);
            authorProfile_pic = itemView.findViewById(R.id.iv_author);

        }
    }
}
