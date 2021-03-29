package com.moringaschool.allrecipe.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.moringaschool.allrecipe.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
    public static final String TAG = MainActivity.class.getSimpleName();
//    private Button mFindRecipeButton;
//    private EditText mRecipeEditText;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.findRecipeButton)
    Button mFindRecipeButton;
    @BindView(R.id.RecipeEditText)
    EditText mRecipeEditText;
    @BindView(R.id.AppNameTextView)
    TextView mAppNameTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        mRecipeEditText=(EditText)findViewById(R.id.RecipeEditText);
//        mFindRecipeButton =(Button)findViewById(R.id.findRecipeButton);
        mFindRecipeButton.setOnClickListener(this);
    }

        @Override
        public void onClick (View v){
            if (v == mFindRecipeButton) {
                String recipe = mRecipeEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, RecipeDetailActivity.class);
                intent.putExtra("recipe", recipe);
//                Log.d(TAG,recipe);

                startActivity(intent);
//                Toast.makeText(MainActivity.this,recipe,Toast.LENGTH_LONG).show();

            }
        }
    }

