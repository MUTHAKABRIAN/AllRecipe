package com.moringaschool.allrecipe.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.allrecipe.R;
import com.moringaschool.allrecipe.adapter.RecipeListAdapter;
import com.moringaschool.allrecipe.models.Recipe;
import com.moringaschool.allrecipe.models.RecipesResponse;
import com.moringaschool.allrecipe.network.RecipeApi;
import com.moringaschool.allrecipe.network.RecipeClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeListActivity {
    private static final String TAG = RecipeListActivity.class.getSimpleName();
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private RecipeListAdapter mAdapter;
    @BindView(R.id.errorTextView)
    TextView mErrorTextView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    public List<Recipe> recipes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        ButterKnife.bind(this);



//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                String restaurant = ((TextView) view).getText().toString();
//                Toast.makeText(RestaurantsActivity.this, restaurant, Toast.LENGTH_LONG).show();
//
//
//            }
//        });
        Intent intent = getIntent();
        String recipe = intent.getStringExtra("recipe");


        RecipeApi client = RecipeClient.getClient();

        Call<RecipesResponse> call = client.getRecipe(recipe, "048b63e1");

        call.enqueue(new Callback<RecipesResponse>() {
            @Override
            public void onResponse(Call<RecipesResponse> call, Response<RecipesResponse> response) {
                hideProgressBar();

                if (response.isSuccessful()) {
                    recipes = response.body().get();
                    mAdapter = new RecipeListAdapter(recipes, RecipeListActivity.this);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(RecipeListActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);

                    showRecipes();
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<RecipesResponse> call, Throwable t) {
                hideProgressBar();
                showFailureMessage();
            }

        });
    }
    private void showFailureMessage(){
        mErrorTextView.setText("Something went Wrong.please check your internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }
    private void showUnsuccessfulMessage(){
        mErrorTextView.setText("Something went wrong .please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showRestaurants() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }
    private void hideProgressBar(){
        mProgressBar.setVisibility(View.GONE);
    }
}
}
