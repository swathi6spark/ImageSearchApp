package com.gasbuddy.imageapp.imagefetchersdk.repository;

import com.gasbuddy.imageapp.apppreferences.AppPreferences;
import com.gasbuddy.imageapp.apppreferences.IMAGE_SOURCE;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofitImgurClient = null;
    private static Retrofit retrofitUnSplashClient = null;
    private static final String BASE_URL_IMGUR = "https://api.imgur.com/3/";
    private static final String BASE_URL_UNSPLASH = "https://api.unsplash.com/";


    static Retrofit getClient() {

        if (retrofitImgurClient == null) {
            retrofitImgurClient = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL_IMGUR)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        if (retrofitUnSplashClient == null) {
            retrofitUnSplashClient = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL_UNSPLASH)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return AppPreferences.INSTANCE.getCurrentImageSrc() == IMAGE_SOURCE.IMG_SRC_UNSPLASH ? retrofitUnSplashClient : retrofitImgurClient;
    }

}
