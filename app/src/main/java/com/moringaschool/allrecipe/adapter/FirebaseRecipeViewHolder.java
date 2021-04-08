package com.moringaschool.allrecipe.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.allrecipe.Constants;
import com.moringaschool.allrecipe.R;
import com.moringaschool.allrecipe.models.Hit;
import com.moringaschool.allrecipe.ui.RecipeDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;

public class FirebaseRecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    View mView;
    Context mContext;

    public FirebaseRecipeViewHolder(View itemView) {
        super(itemView);
        this.mView = itemView;
        mContext =itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindRecipe(Hit recipe){
        ImageView mRecipeImageView = (ImageView) mView.findViewById((R.id.recipeImageView));
        TextView mNameTextView = (TextView) mView.findViewById(R.id.recipeNameTextView);
        TextView brianSourceText = (TextView) mView.findViewById(R.id.sourceTextView);
        Picasso.get().load(recipe.getRecipe().getImage()).into(mRecipeImageView);
        mNameTextView.setText(recipe.getRecipe().getLabel());
//            Hit newCalories;
//            newCalories.getRecipe().getCalories().
        brianSourceText.setText("Source: "+recipe.getRecipe().getSource());




    }

    @Override
    public void onClick(View v) {
        final ArrayList<Hit> recipes= new ArrayList<>();
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_RECIPES);
                   ref.addListenerForSingleValueEvent(new ValueEventListener() {


                       @Override
                       public void onDataChange( DataSnapshot dataSnapshot) {
                           for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                               recipes.add(snapshot.getValue(Hit.class));

                           }
                           int itemPosition =getLayoutPosition();
                           Intent intent =new Intent (mContext , RecipeDetailActivity.class);
                           intent.putExtra("position",itemPosition +"");
                           intent.putExtra("recipes", Parcels.wrap(recipes));
                           mContext.startActivity(intent);

                       }

                       @Override
                       public void onCancelled(DatabaseError error) {

                       }

        });

    }
}
