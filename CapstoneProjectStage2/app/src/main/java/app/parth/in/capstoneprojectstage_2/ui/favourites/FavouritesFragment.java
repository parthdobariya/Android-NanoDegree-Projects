package app.parth.in.capstoneprojectstage_2.ui.favourites;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import app.parth.in.capstoneprojectstage_2.R;
import app.parth.in.capstoneprojectstage_2.model.Favourite;

public class FavouritesFragment extends Fragment implements FavouriteAdapter.ClickListener{

    private FavouritesViewModel favouritesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        favouritesViewModel =
                ViewModelProviders.of(this).get(FavouritesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        favouritesViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Intent myIntent = new Intent(getActivity(), FavouritesActivity.class);
                getActivity().startActivity(myIntent);
            }
        });
        return root;
    }

    @Override
    public void onClickListener(Favourite quotes) {
     /*   Intent i = new Intent(getContext(), FavouritesActivity.class);
        startActivity(i);*/
    }
}