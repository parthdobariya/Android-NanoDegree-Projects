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
import app.parth.in.capstoneprojectstage_2.model.Favourite;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder> {

    private List<Favourite> favouriteList;
    private FavouriteAdapter.ClickListener listener;

    FavouriteAdapter(List<Favourite> favouriteList, ClickListener listener) {
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
        Favourite favourite = favouriteList.get(position);
        holder.favouriteText.setText(favourite.getName());
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
        void onClickListener(Favourite quotes);
    }

    static class FavouriteViewHolder extends RecyclerView.ViewHolder {
        TextView favouriteText;
        CardView cardView;

        FavouriteViewHolder(View itemView) {
            super(itemView);
            favouriteText = itemView.findViewById(R.id.favourite_tv);
            cardView = itemView.findViewById(R.id.favourite_card_view);
        }
    }
}
