package com.moringaschool.allrecipe.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.moringaschool.allrecipe.Constants.BASE_URL;

public class RecipeClient {
    private static Retrofit retrofit = null;

    public static RecipeApi getClient(){
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        if (retrofit == null) {
            HttpLoggingInterceptor httpLoggingInterceptor =new HttpLoggingInterceptor();
            httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

            okHttpClient.addInterceptor(httpLoggingInterceptor);
            okHttpClient.build();

        }
        retrofit= new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(RecipeApi.class);

    }


}
