package com.moringaschool.allrecipe.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.allrecipe.Constants;
import com.moringaschool.allrecipe.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
    public static final String TAG = MainActivity.class.getSimpleName();
//    private Button mFindRecipeButton;
//    private EditText mRecipeEditText;
//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;

    private DatabaseReference mSearchedRecipeReference;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.findRecipeButton)
    Button mFindRecipeButton;
    @BindView(R.id.RecipeEditText)
    EditText mRecipeEditText;
    @BindView(R.id.AppNameTextView)
    TextView mAppNameTxtView;

private ValueEventListener mSearchedRecipeReferenceListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSearchedRecipeReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_RECIPE);
        mSearchedRecipeReferenceListener=mSearchedRecipeReference.addValueEventListener (new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot recipeSnapshot: dataSnapshot.getChildren()){
                    String recipe =recipeSnapshot.getValue().toString();
                    Log.d("recipe updated ","recipe"+recipe);
                }

            }

            @Override
            public void onCancelled( @NonNull DatabaseError error) {

            }
        });




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();

//        mRecipeEditText=(EditText)findViewById(R.id.RecipeEditText);
//        mFindRecipeButton =(Button)findViewById(R.id.findRecipeButton);
        mFindRecipeButton.setOnClickListener(this);
    }

        @Override
        public void onClick (View v){
            if (v == mFindRecipeButton) {
                String recipe = mRecipeEditText.getText().toString();
                saveRecipeToFirebase(recipe);

//                if (!(recipe).equals("") ){
//                    addToSharedPreferences(recipe);
//                }

                Intent intent = new Intent(MainActivity.this, RecipeListActivity.class);
                intent.putExtra("recipe", recipe);
//                Log.d(TAG,recipe);

                startActivity(intent);
//                Toast.makeText(MainActivity.this,recipe,Toast.LENGTH_LONG).show();

            }
        }

        public void saveRecipeToFirebase(String recipe){
        mSearchedRecipeReference.push().setValue(recipe);
        }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchedRecipeReference.removeEventListener(mSearchedRecipeReferenceListener);
    }
//        private void addToSharedPreferences(String recipe){
//        mEditor.putString(Constants.PREFERENCES_RECIPE_KEY,recipe).apply();
//        }
    }

