package com.moringaschool.allrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeActivity extends AppCompatActivity {
//    private TextView mRecipeTextView;
//    private ListView mListView;
    @BindView(R.id.recipeTextView) TextView  mRecipeTextView;
    @BindView(R.id.listView) ListView mListView;
    private String[] recipes=new String[]{"Chicken & Chorizo jambalaya","Old Delhi-style butter chicken","Chicken madras","Creamy chicken stew","Chicken supreme","Summer chicken stew","Bang bang chicken & vegetable noodles","Poached chicken breast","Next Level fried Chicken","Easy Teriyaki chicken","Mauritian chicken curry","Lighter chicken tacos","Spring roast chicken","Cashew chicken stir-fry","Nutty chicken satay strips","Easy Roast chicken","Beer can chicken" };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        ButterKnife.bind(this);

//        mRecipeTextView =(TextView)findViewById((R.id.recipeTextView));
//        mListView =(ListView) findViewById(R.id.listView);


        ArrayAdapter adapter =new ArrayAdapter(this,android.R.layout.simple_list_item_1,recipes);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String recipe = ((TextView)view).getText().toString();
                Toast.makeText(RecipeActivity.this, recipe, Toast.LENGTH_LONG).show();
            }
        });

        Intent intent =getIntent();
        String recipe =intent.getStringExtra("recipe");
        mRecipeTextView.setText("Here are all the Recipes for: "+recipe);

    }
}