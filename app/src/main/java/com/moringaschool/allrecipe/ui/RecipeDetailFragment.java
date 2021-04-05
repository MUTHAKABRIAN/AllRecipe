package com.moringaschool.allrecipe.ui;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.allrecipe.Constants;
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
public class RecipeDetailFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private Hit recipeInFragments;
     @BindView (R.id.saveRecipeButton) Button mSaveRecipeButton;
    @BindView(R.id.fragmentImageView) ImageView  hereImage;
    @BindView(R.id.fragmentNameTextView) TextView fragmentName;
    @BindView(R.id.ingredientsTextView) TextView ingredientViews;
    @BindView(R.id.labelTextView)TextView labelIngredient;
    @BindView(R.id.ingredientsTextView2)TextView ingredientViews2;
    @BindView (R.id.ingredientsTextView3)TextView ingredientView3;
//    @BindView (R.id.ingredientsTextView4)TextView ingredientView4;
//    @BindView (R.id.ingredientsTextView5)TextView ingredientView5;
//    @BindView (R.id.ingredientsTextView6)TextView ingredientView6;
//    @BindView (R.id.ingredientsTextView7)TextView ingredientView7;
//    @BindView (R.id.ingredientsTextView8)TextView ingredientView8;
//    @BindView (R.id.ingredientsTextView9)TextView ingredientView9;











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
        ingredientViews2.setText(recipeInFragments.getRecipe().getIngredientLines().get(1));
        ingredientView3.setText(recipeInFragments.getRecipe().getIngredientLines().get(2));
//        ingredientView4.setText(recipeInFragments.getRecipe().getIngredientLines().get(3));
//        ingredientView5.setText(recipeInFragments.getRecipe().getIngredientLines().get(4));
//        ingredientView6.setText(recipeInFragments.getRecipe().getIngredientLines().get(5));
//        ingredientView7.setText(recipeInFragments.getRecipe().getIngredientLines().get(6));
//        ingredientView8.setText(recipeInFragments.getRecipe().getIngredientLines().get(7));
//        ingredientView9.setText(recipeInFragments.getRecipe().getIngredientLines().get(8));
//        mYaingredients.setText(recipeInFragments.getRecipe().getIngredients().size());
 mSaveRecipeButton.setOnClickListener(this);
        return view;

    }
    @Override
    public void onClick(View v){
        if (v == mSaveRecipeButton){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid =user.getUid();
            DatabaseReference recipeRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_RECIPES)
                    .child(uid);
            DatabaseReference pushRef = recipeRef.push();
            String pushId=pushRef.getKey();
            recipeInFragments.setPushId(pushId);
            pushRef.setValue(recipeInFragments);
//            recipeRef.push().setValue(recipeInFragments);
            Toast.makeText(getContext(),"Saved",Toast.LENGTH_SHORT).show();
        }
    }
}