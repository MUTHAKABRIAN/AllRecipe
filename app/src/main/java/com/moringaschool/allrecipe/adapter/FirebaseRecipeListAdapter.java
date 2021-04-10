package com.moringaschool.allrecipe.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.moringaschool.allrecipe.R;
import com.moringaschool.allrecipe.models.Hit;
import com.moringaschool.allrecipe.util.ItemTouchHelperAdapter;
import com.moringaschool.allrecipe.util.OnStartDragListener;

public class FirebaseRecipeListAdapter extends FirebaseRecyclerAdapter<Hit,FirebaseRecipeViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    public FirebaseRecipeListAdapter(@NonNull FirebaseRecyclerOptions<Hit> options, DatabaseReference ref, OnStartDragListener mOnStartDragListener, Context context) {
        super(options);
        this.mRef = ref.getRef();
        this.mOnStartDragListener = mOnStartDragListener;
        this.mContext = context;
    }
    @Override
    protected void onBindViewHolder(@NonNull FirebaseRecipeViewHolder firebaseRecipeViewHolder, int position, @NonNull Hit recipe) {
        firebaseRecipeViewHolder.bindRecipe(recipe);
        firebaseRecipeViewHolder.mRecipeImageView.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v , MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(firebaseRecipeViewHolder);

                }
                return false;
            }
        });
    }

    @Override
    public FirebaseRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list_item_drag,parent,false);
        return new FirebaseRecipeViewHolder(view);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }

    @Override
    public void onItemDismiss(int position) {

    }
}

