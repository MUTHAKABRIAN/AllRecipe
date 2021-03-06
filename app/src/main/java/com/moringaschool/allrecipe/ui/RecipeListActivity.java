package com.moringaschool.allrecipe.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.moringaschool.allrecipe.Constants;
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
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentSearch;

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
        Intent intent = getIntent();
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentSearch = mSharedPreferences.getString(Constants.PREFERENCES_RECIPE_KEY, null);
        //Log.d("Shared Pref Recipe", mRecentSearch);


//        mRecipeTextView =(TextView)findViewById((R.id.recipeTextView));
//        mListView =(ListView) findViewById(R.id.listView);


//        String recipe =mRecentSearch;
        String recipe = intent.getStringExtra("recipe");

//        mRecipeTextView.setText("Here are all the Recipes for: "+recipe);
//
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                String recipe = ((TextView)view).getText().toString();
//                Toast.makeText(RecipeActivity.this, recipe, Toast.LENGTH_LONG).show();
//            }
//        });
        if (mRecentSearch != null) {
            fetchRecipes(mRecentSearch);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.menu_search,menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();
        MenuItem menuItem =menu.findItem(R.id.action_search);
        SearchView searchView =(SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String recipe) {
                addToSharedPreferences(recipe);
                fetchRecipes(recipe);
                return false;
            }


            @Override
            public boolean onQueryTextChange(String recipe) {
                return false;
            }
        });

        return true;
    }



            @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        return super.onOptionsItemSelected(item);
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
        private void addToSharedPreferences(String recipe){
            mEditor.putString(Constants.PREFERENCES_RECIPE_KEY,recipe).apply();
        }

private void fetchRecipes(String recipe){
    RecipeApi client = RecipeClient.getClient();
    Call<RecipesResponse> call = client.getRecipe(recipe, "048b63e1", "28c7682c4af597714b4790cbd28ee328");
    call.enqueue(new Callback<RecipesResponse>() {
        @Override
        public void onResponse(Call<RecipesResponse> call, Response<RecipesResponse> response) {
            hideProgressBar();
            if (response.isSuccessful()) {
                recipeDetails = response.body().getHits();
                mRecipeListAdapter = new RecipeListAdapter(recipeDetails, RecipeListActivity.this);
                mRecyclerview.setAdapter(mRecipeListAdapter);
                RecyclerView.LayoutManager layoutManager =
                        new LinearLayoutManager(RecipeListActivity.this);
                mRecyclerview.setLayoutManager(layoutManager);
                mRecyclerview.setHasFixedSize(true);
                Log.d(TAG, "onResponse: " + recipeDetails);
                showRecipes();
            } else {
                showUnsuccessfulMessage();
            }

        }
        @Override
        public void onFailure(Call<RecipesResponse> call, Throwable t) {
            hideProgressBar();
            showFailureMessage();
            Log.e(TAG, "onFailure: failing terribly", t);
        }
    });
}


}