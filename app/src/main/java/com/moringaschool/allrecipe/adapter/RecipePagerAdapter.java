package com.moringaschool.allrecipe.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.moringaschool.allrecipe.models.Hit;
import com.moringaschool.allrecipe.models.Recipe;
import com.moringaschool.allrecipe.ui.RecipeDetailActivity;
import com.moringaschool.allrecipe.ui.RecipeDetailFragment;

import java.util.List;

public class RecipePagerAdapter extends FragmentPagerAdapter {
    private List<Hit> mRecipes;

    public RecipePagerAdapter(@NonNull FragmentManager fm, int behavior, List<Hit> recipes) {
        super(fm, behavior);
        mRecipes = recipes;
    }

    @Override
    public Fragment getItem(int position) {
        return RecipeDetailFragment.newInstance(mRecipes.get(position));
    }

    @Override
    public int getCount() {
        return mRecipes.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mRecipes.get(position).getRecipe().getLabel();
    }
}

