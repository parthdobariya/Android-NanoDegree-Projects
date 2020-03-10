package app.parth.in.capstoneprojectstage_2.ui.categories;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import app.parth.in.capstoneprojectstage_2.R;
import app.parth.in.capstoneprojectstage_2.model.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<Category> categoryList;
    private ClickListener listener;

    CategoryAdapter(List<Category> categoryList, ClickListener listener) {
        super();
        this.categoryList = categoryList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categori_item_cell, parent, false);

        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, final int position) {
        holder.categoryText.setText(categoryList.get(position).getName());
        holder.categoryContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickListener(categoryList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return  categoryList.size();
      //  return categoryList==null?0:categoryList.size();
    }

    public interface ClickListener {
        void onClickListener(Category category);
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView categoryText;
        CardView categoryContainer;

        CategoryViewHolder(View itemView) {
            super(itemView);
            categoryText = itemView.findViewById(R.id.category_tv);
            categoryContainer = itemView.findViewById(R.id.category_container);
        }
    }
}