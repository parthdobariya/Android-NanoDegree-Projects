package app.parth.in.capstoneprojectstage_2.ui.quotes;

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

public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.QuotesViewHolder> {
    private List<Quotes> quotesList;
    private QuotesAdapter.ClickListener listener;

    QuotesAdapter(List<Quotes> quotesList, QuotesAdapter.ClickListener listener) {
        super();
        this.quotesList = quotesList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public QuotesAdapter.QuotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.quotes_item_cell, parent, false);

        return new QuotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuotesViewHolder holder, final int position) {
        Quotes quotes = quotesList.get(position);
        holder.quotesText.setText(quotes.getName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickListener(quotesList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return quotesList.size();
    }

    interface ClickListener {
        void onClickListener(Quotes quotes);
    }

    static class QuotesViewHolder extends RecyclerView.ViewHolder {
        TextView quotesText;
        CardView cardView;

        QuotesViewHolder(View itemView) {
            super(itemView);
            quotesText = itemView.findViewById(R.id.quotes_tv);
            cardView = itemView.findViewById(R.id.quotes_card_view);
        }
    }

}
