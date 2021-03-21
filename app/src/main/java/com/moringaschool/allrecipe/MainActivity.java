package com.moringaschool.allrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mFindRecipeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFindRecipeButton =(Button)findViewById(R.id.findRecipeButton);
        mFindRecipeButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,RecipeActivity.class);
                startActivity(intent);
//                Toast.makeText(MainActivity.this,"Hello world!",Toast.LENGTH_LONG).show();

            }
        });
    }
}