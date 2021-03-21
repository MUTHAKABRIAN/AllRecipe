package com.moringaschool.allrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class RecipeActivity extends AppCompatActivity {
    private TextView mRecipeTextView;
    private ListView mListView;
    private String[] recipes=new String[]{"Chicken & Chorizo jambalaya","Old Delhi-style butter chicken","Chicken madras","Creamy chicken stew","Chicken supreme","Next Level fried Chicken","Easy Teriyaki chicken ","Mauritian chicken curry","Nutty chicken satay strips","Easy Roast chicken" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        mRecipeTextView =(TextView)findViewById((R.id.recipeTextView));
        Intent intent =getIntent();
        String recipe =intent.getStringExtra("recipe");
        mRecipeTextView.setText("Here are all the Recipes for: "+recipe);

        mListView =(ListView) findViewById(R.id.listView);
        ArrayAdapter adapter =new ArrayAdapter(this,android.R.layout.simple_list_item_1,recipes);
        mListView.setAdapter(adapter);

    }
}