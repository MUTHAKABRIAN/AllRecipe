package com.moringaschool.allrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class RecipeActivity extends AppCompatActivity {
    private TextView mRecipeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        mRecipeTextView =(TextView)findViewById((R.id.recipeTextView));
        Intent intent =getIntent();
        String recipe =intent.getStringExtra("recipe");
        mRecipeTextView.setText("Here are all the Recipes for: "+recipe);

    }
}