package app.parth.in.capstoneprojectstage_2.ui.favourites;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import app.parth.in.capstoneprojectstage_2.R;
import app.parth.in.capstoneprojectstage_2.model.Quotes;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder> {

    private List<Quotes> favouriteList;
    private FavouriteAdapter.ClickListener listener;

    FavouriteAdapter(List<Quotes> favouriteList, ClickListener listener) {
        super();
        this.favouriteList = favouriteList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favourite_item_cell, parent, false);

        return new FavouriteAdapter.FavouriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteAdapter.FavouriteViewHolder holder, final int position) {
        Quotes quotes = favouriteList.get(position);
        holder.favouriteQuoteText.setText(quotes.getTitle());
        holder.favouriteAuthorText.setText(quotes.getAuthor());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickListener(favouriteList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return favouriteList.size();
    }

    interface ClickListener {
        void onClickListener(Quotes quotes);
    }

    static class FavouriteViewHolder extends RecyclerView.ViewHolder {
        TextView favouriteQuoteText, favouriteAuthorText;
        CardView cardView;

        FavouriteViewHolder(View itemView) {
            super(itemView);
            favouriteQuoteText = itemView.findViewById(R.id.fav_quote_tv);
            cardView = itemView.findViewById(R.id.favourite_card_view);
            favouriteAuthorText = itemView.findViewById(R.id.fav_author_name);
        }
    }
}
