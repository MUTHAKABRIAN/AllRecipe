package com.moringaschool.allrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    public static final String TAG =MainActivity.class.getSimpleName();
//    private Button mFindRecipeButton;
//    private EditText mRecipeEditText;

    @BindView(R.id.findRecipeButton)Button mFindRecipeButton;
    @BindView (R.id.RecipeEditText)EditText mRecipeEditText;
    @BindView (R.id.AppNameTextView) TextView mAppNameTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        mRecipeEditText=(EditText)findViewById(R.id.RecipeEditText);
//        mFindRecipeButton =(Button)findViewById(R.id.findRecipeButton);
        mFindRecipeButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                String recipe =mRecipeEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this,RecipeActivity.class);
                intent.putExtra("recipe",recipe);
//                Log.d(TAG,recipe);

                startActivity(intent);
//                Toast.makeText(MainActivity.this,recipe,Toast.LENGTH_LONG).show();

            }
        });
    }
}