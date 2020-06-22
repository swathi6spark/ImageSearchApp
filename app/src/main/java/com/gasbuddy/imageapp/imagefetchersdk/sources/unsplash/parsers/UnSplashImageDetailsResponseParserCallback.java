package com.gasbuddy.imageapp.imagefetchersdk.sources.unsplash.parsers;

import com.gasbuddy.imageapp.imagefetchersdk.sources.baseparsers.BaseImageDetailsParserCallback;
import com.gasbuddy.imageapp.imagefetchersdk.sources.unsplash.models.UnSplashImage;
import com.gasbuddy.imageapp.models.ImageDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UnSplashImageDetailsResponseParserCallback extends BaseImageDetailsParserCallback implements Callback<UnSplashImage> {

    @Override
    public void onResponse(Call<UnSplashImage> call, Response<UnSplashImage> response) {
        if (response.body() != null){
            //Error handling
            getImageDetailsLiveData().postValue(getImageDetailsFromUnSplashImageResponse(response.body()));
        }
    }

    @Override
    public void onFailure(Call<UnSplashImage> call, Throwable t) {
        getImageDetailsLiveData().postValue(null);
    }

    private static ImageDetails getImageDetailsFromUnSplashImageResponse(UnSplashImage unSplashImage){
        // handle many null checks
        return new ImageDetails(unSplashImage.getId(),unSplashImage.getUrls().getSmall(),unSplashImage.getDescription(),unSplashImage.getAltDescription(),unSplashImage.getLikes());
    }
}
