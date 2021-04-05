package com.moringaschool.allrecipe.network;

import com.moringaschool.allrecipe.models.RecipesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipeApi {
    @GET ("search?")
    Call<RecipesResponse> getRecipe(
            @Query("q") String q,
            @Query("app_id") String app_id,
            @Query("app_key") String app_key

    );

}
//https://api.edamam.com/search?q=boilled eggs&app_id=048b63e1&app_key=28c7682c4af597714b4790cbd28ee328