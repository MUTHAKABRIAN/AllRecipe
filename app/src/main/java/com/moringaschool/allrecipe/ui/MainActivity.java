package com.moringaschool.allrecipe.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.moringaschool.allrecipe.Constants;
import com.moringaschool.allrecipe.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
    public static final String TAG = MainActivity.class.getSimpleName();
//    private Button mFindRecipeButton;
//    private EditText mRecipeEditText;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

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

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

//        mRecipeEditText=(EditText)findViewById(R.id.RecipeEditText);
//        mFindRecipeButton =(Button)findViewById(R.id.findRecipeButton);
        mFindRecipeButton.setOnClickListener(this);
    }

        @Override
        public void onClick (View v){
            if (v == mFindRecipeButton) {
                String recipe = mRecipeEditText.getText().toString();
                if (!(recipe).equals("") ){
                    addToSharedPreferences(recipe);
                }

                Intent intent = new Intent(MainActivity.this, RecipeListActivity.class);
                intent.putExtra("recipe", recipe);
//                Log.d(TAG,recipe);

                startActivity(intent);
//                Toast.makeText(MainActivity.this,recipe,Toast.LENGTH_LONG).show();

            }
        }
        private void addToSharedPreferences(String recipe){
        mEditor.putString(Constants.PREFERENCES_RECIPE_KEY,recipe).apply();
        }
    }

