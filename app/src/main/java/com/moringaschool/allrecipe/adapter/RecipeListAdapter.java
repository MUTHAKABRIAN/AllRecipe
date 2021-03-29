//package com.moringaschool.allrecipe.adapter;
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.moringaschool.allrecipe.R;
//import com.moringaschool.allrecipe.models.Recipe;
//import com.moringaschool.allrecipe.ui.RecipeDetailActivity;
//import com.squareup.picasso.Picasso;
//
//import org.parceler.Parcels;
//
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//
//
//public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {
//    private List<Recipe> mRecipes;
//    private Context mContext;
//
//
//    public RecipeListAdapter(List<Recipe> mRecipes, Context mContext) {
//        this.mRecipes = mRecipes;
//        this.mContext = mContext;
//    }
//
//
//    @Override
//    public RecipeListAdapter.RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list_item, parent, false);
//        RecipeViewHolder viewHolder = new RecipeViewHolder(view);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(RecipeViewHolder holder, int position) {
//        holder.bindRecipe(mRecipes.get(position));
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return mRecipes.size();
//    }
//
//    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//        @BindView(R.id.recipeImageView)
//        ImageView mRecipeImageView;
//        @BindView(R.id.recipeNameTextView)
//        TextView mNameTextView;
//        @BindView(R.id.cuisineTypeTextView) TextView mCuisineTypeTextView;
//
//        private Context mContext;
//
//        public RecipeViewHolder(View itemView){
//            super(itemView);
//            ButterKnife.bind(this,itemView);
//            mContext=itemView.getContext();
//            itemView.setOnClickListener(this);
//
//
//        }
//        public void bindRecipe(Recipe recipe){
//            mNameTextView.setText(recipe.getLabel());
//            mCuisineTypeTextView.setText(recipe.getCuisineType().get(0).getTitle());
//            Picasso.get().load(recipe.getImage()).into(mRecipeImageView);
//
//        }
//
//        @Override
//        public void onClick(View v) {
//            //used getAdapterPosition instead of layoutPosition
//            int itemPosition = getAdapterPosition();
//            Intent intent = new Intent(mContext, RecipeDetailActivity.class);
//            intent.putExtra("position", itemPosition);
//            intent.putExtra("recipes", Parcels.wrap(mRecipes));
//            mContext.startActivity(intent);
//
//        }
//    }
//
//}
