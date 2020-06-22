package com.gasbuddy.imageapp.imagefetchersdk.sources.unsplash.parsers;

import com.gasbuddy.imageapp.imagefetchersdk.sources.baseparsers.BaseAlbumParserCallback;
import com.gasbuddy.imageapp.imagefetchersdk.sources.unsplash.models.UnSplashImage;
import com.gasbuddy.imageapp.imagefetchersdk.sources.unsplash.models.UnSplashSearchResponse;
import com.gasbuddy.imageapp.models.ImageMetadata;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UnSplashSearchResponseParserCallback extends BaseAlbumParserCallback implements Callback<UnSplashSearchResponse> {

    @Override
    public void onResponse(Call<UnSplashSearchResponse> call, Response<UnSplashSearchResponse> response) {
        if (response.body() != null){
            getImageListLiveData().postValue(getImageListFromUnSplashImageSearch(response.body()));
        }
    }

    @Override
    public void onFailure(Call<UnSplashSearchResponse> call, Throwable t) {
        getImageListLiveData().postValue(null);
    }

    private List<ImageMetadata> getImageListFromUnSplashImageSearch(UnSplashSearchResponse unSplashSearchResponse){
        List<UnSplashImage> unSplashImageList = unSplashSearchResponse.getResults();
        List<ImageMetadata> imageMetadataList = new ArrayList<>();
        for (UnSplashImage unSplashImage: unSplashImageList){
            ImageMetadata imageMetadata = new ImageMetadata(unSplashImage.getId(),unSplashImage.getUrls().getSmall());
            imageMetadataList.add(imageMetadata);
        }
        return imageMetadataList;
    }
}
