package com.moringaschool.allrecipe.ui;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import com.moringaschool.allrecipe.R;
import com.moringaschool.allrecipe.models.Hit;
import com.moringaschool.allrecipe.models.Recipe;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecipeDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class RecipeDetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private Hit recipeInFragments;

    @BindView(R.id.fragmentImageView) ImageView  hereImage;
    @BindView(R.id.fragmentNameTextView) TextView fragmentName;
    @BindView(R.id.ingredientsTextView) TextView ingredientViews;
    @BindView(R.id.labelTextView)TextView labelIngredient;




    // TODO: Rename and change types and number of parameters
    public static RecipeDetailFragment newInstance(Hit newHits) {
        RecipeDetailFragment fragment = new RecipeDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("newHits", Parcels.wrap(newHits));
        fragment.setArguments(args);
        return fragment;
    }

    public RecipeDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
       recipeInFragments =  Parcels.unwrap(getArguments().getParcelable("newHits"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        ButterKnife.bind(this, view);
        Picasso.get().load(recipeInFragments.getRecipe().getImage()).into(hereImage);
        fragmentName.setText(recipeInFragments.getRecipe().getLabel());
        ingredientViews.setText(recipeInFragments.getRecipe().getIngredientLines().get(0));
//        mYaingredients.setText(recipeInFragments.getRecipe().getIngredients().size());

        return view;

    }
}