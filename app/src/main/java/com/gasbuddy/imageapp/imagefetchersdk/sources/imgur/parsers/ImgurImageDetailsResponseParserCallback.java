package com.gasbuddy.imageapp.imagefetchersdk.sources.imgur.parsers;

import com.gasbuddy.imageapp.imagefetchersdk.sources.baseparsers.BaseImageDetailsParserCallback;
import com.gasbuddy.imageapp.imagefetchersdk.sources.imgur.models.ImgurImage;
import com.gasbuddy.imageapp.imagefetchersdk.sources.imgur.models.ImgurImageDetailResponse;
import com.gasbuddy.imageapp.models.ImageDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImgurImageDetailsResponseParserCallback extends BaseImageDetailsParserCallback implements Callback<ImgurImageDetailResponse> {

    @Override
    public void onResponse(Call<ImgurImageDetailResponse> call, Response<ImgurImageDetailResponse> response) {
        //Error handling
        if (response.body() != null) {
            getImageDetailsLiveData().postValue(getImageDetailsFromImgurImage(response.body().getImage()));
        }
    }

    @Override
    public void onFailure(Call<ImgurImageDetailResponse> call, Throwable t) {
        getImageDetailsLiveData().postValue(null);
    }

    private ImageDetails getImageDetailsFromImgurImage(ImgurImage imgurImage) {
        return new ImageDetails(imgurImage.getId(), imgurImage.getLink(), imgurImage.getTitle(), imgurImage.getDescription()
                , imgurImage.getViews());
    }

}
