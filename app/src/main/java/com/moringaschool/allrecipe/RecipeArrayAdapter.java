package com.moringaschool.allrecipe;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

public class RecipeArrayAdapter  extends ArrayAdapter {
    private Context mContext;
    private String [] mRecipes;
    private String[] mDescriptions;

//    public RecipeArrayAdapter(Context context, int resource, Context mContext, String[] mRecipes, String[] mDescriptions) {
//        super(context, resource);
//        this.mContext = mContext;
//        this.mRecipes = mRecipes;
//        this.mDescriptions = mDescriptions;
//    }

    public RecipeArrayAdapter(Context context, int resource, String[] mRecipes, String[] mDescriptions) {
        super(context, resource);
        this.mContext = mContext;
        this.mRecipes = mRecipes;
        this.mDescriptions = mDescriptions;
    }


    @Override
    public Object getItem(int position){
        String recipe =mRecipes[position];
        String description =mDescriptions[position];
        return String.format("%s \n short description:%s",recipe,description);
    }

    @Override
    public int getCount(){
        return mRecipes.length;
    }
}
