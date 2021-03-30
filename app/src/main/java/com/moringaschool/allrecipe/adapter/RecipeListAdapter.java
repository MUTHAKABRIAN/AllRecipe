package com.moringaschool.allrecipe.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.allrecipe.R;
import com.moringaschool.allrecipe.models.Hit;
import com.moringaschool.allrecipe.models.Recipe;
import com.moringaschool.allrecipe.ui.RecipeDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {
    private List<Hit> adapterHits;
    private Context mContext;


    public RecipeListAdapter(List<Hit> mRecipes, Context mContext) {
        this.adapterHits = mRecipes;
        this.mContext = mContext;
    }


    @Override
    public RecipeListAdapter.RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list_item, parent, false);
        RecipeViewHolder viewHolder = new RecipeViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        holder.bindRecipe(adapterHits.get(position));

    }

    @Override
    public int getItemCount() {
        return adapterHits.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.recipeImageView) ImageView mRecipeImageView;
        @BindView(R.id.recipeNameTextView) TextView mNameTextView;

        private Context mContext;

        public RecipeViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
            mContext=itemView.getContext();
            itemView.setOnClickListener(this);


        }
        public void bindRecipe(Hit recipe){
            Picasso.get().load(recipe.getRecipe().getImage()).into(mRecipeImageView);
            mNameTextView.setText(recipe.getRecipe().getLabel());

        }

        @Override
        public void onClick(View v) {
            //used getAdapterPosition instead of layoutPosition


        }
    }

}
