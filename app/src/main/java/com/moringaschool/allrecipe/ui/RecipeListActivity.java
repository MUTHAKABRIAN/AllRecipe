package com.moringaschool.allrecipe.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.moringaschool.allrecipe.R;
import com.moringaschool.allrecipe.adapter.RecipeListAdapter;
import com.moringaschool.allrecipe.models.Hit;
import com.moringaschool.allrecipe.models.RecipesResponse;
import com.moringaschool.allrecipe.network.RecipeApi;
import com.moringaschool.allrecipe.network.RecipeClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeListActivity extends AppCompatActivity {

    private static final String TAG = "in recipeListActivity";
    //    private TextView mRecipeTextView;
    @BindView(R.id.errorTextView)TextView mErrorTextView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerview;

    private List<Hit> recipeDetails;
    private RecipeListAdapter mRecipeListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        ButterKnife.bind(this);

//        mRecipeTextView =(TextView)findViewById((R.id.recipeTextView));
//        mListView =(ListView) findViewById(R.id.listView);




        Intent intent =getIntent();
        String recipe =intent.getStringExtra("recipe");
//        mRecipeTextView.setText("Here are all the Recipes for: "+recipe);
//
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                String recipe = ((TextView)view).getText().toString();
//                Toast.makeText(RecipeActivity.this, recipe, Toast.LENGTH_LONG).show();
//            }
//        });
        RecipeApi  client = RecipeClient.getClient();
        Call<RecipesResponse> call = client.getRecipe(recipe,"048b63e1","28c7682c4af597714b4790cbd28ee328");
        call.enqueue(new Callback<RecipesResponse>() {
            @Override
            public void onResponse(Call<RecipesResponse> call, Response<RecipesResponse> response) {
                hideProgressBar();
                if(response.isSuccessful()){
                    recipeDetails = response.body().getHits();
                    mRecipeListAdapter = new RecipeListAdapter(recipeDetails, RecipeListActivity.this);
                    mRecyclerview.setAdapter(mRecipeListAdapter);
                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(RecipeListActivity.this);
                    mRecyclerview.setLayoutManager(layoutManager);
                    mRecyclerview.setHasFixedSize(true);
                    Log.d(TAG, "onResponse: "+ recipeDetails);
                    showRecipes();
                }else{
                    showUnsuccessfulMessage();
                }

            }

            @Override
            public void onFailure(Call<RecipesResponse> call, Throwable t) {
                hideProgressBar();
                showFailureMessage();
                Log.e(TAG, "onFailure: failing terribly", t );
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

    private void showRecipes() {
        mRecyclerview.setVisibility(View.VISIBLE);
    }
    private void hideProgressBar(){
        mProgressBar.setVisibility(View.GONE);
    }
}