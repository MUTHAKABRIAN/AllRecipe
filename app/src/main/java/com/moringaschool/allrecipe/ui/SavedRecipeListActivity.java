package com.moringaschool.allrecipe.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.allrecipe.Constants;
import com.moringaschool.allrecipe.R;
import com.moringaschool.allrecipe.adapter.FirebaseRecipeViewHolder;
import com.moringaschool.allrecipe.models.Hit;
import com.moringaschool.allrecipe.models.Recipe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedRecipeListActivity extends AppCompatActivity {
     private DatabaseReference mRecipeReference;
     private FirebaseRecyclerAdapter<Hit, FirebaseRecipeViewHolder> mFirebaseAdapter;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        ButterKnife.bind(this);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mRecipeReference= FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_RECIPES).child(uid);
        setUpFireBaseAdapter();
        hideProgressBar();
        showRecipes();
    }
    private void setUpFireBaseAdapter(){
        FirebaseRecyclerOptions<Hit> options = new FirebaseRecyclerOptions.Builder<Hit>()
                .setQuery(mRecipeReference, Hit.class)
                .build();

        mFirebaseAdapter=new FirebaseRecyclerAdapter<Hit, FirebaseRecipeViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseRecipeViewHolder firebaseRecipeViewHolder, int position, @NonNull Hit recipe) {

                firebaseRecipeViewHolder.bindRecipe(recipe);

            }
            @Override
            public FirebaseRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
               View view = LayoutInflater.from (parent.getContext()).inflate(R.layout.recipe_list_item_drag,parent,false);
               return new FirebaseRecipeViewHolder(view);

            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        mFirebaseAdapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mFirebaseAdapter!=null){
            mFirebaseAdapter.startListening();
        }

    }
    private void showRecipes(){
        mRecyclerView.setVisibility(View.VISIBLE);
    }
    private void hideProgressBar(){
        mRecyclerView.setVisibility(View.GONE);
    }
}