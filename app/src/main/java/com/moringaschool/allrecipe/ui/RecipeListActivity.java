package com.moringaschool.allrecipe.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.moringaschool.allrecipe.R;
import com.moringaschool.allrecipe.models.RecipesResponse;
import com.moringaschool.allrecipe.network.RecipeApi;
import com.moringaschool.allrecipe.network.RecipeClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeListActivity extends AppCompatActivity {
    //    private TextView mRecipeTextView;
//    private ListView mListView;
//    @BindView(R.id.recipeTextView) TextView  mRecipeTextView;
//    @BindView(R.id.listView) ListView mListView;
//

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

            }

            @Override
            public void onFailure(Call<RecipesResponse> call, Throwable t) {

            }
        });



    }
}