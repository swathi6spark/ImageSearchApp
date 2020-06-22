package com.gasbuddy.imageapp.imagefetchersdk.sources.imgur.parsers;

import androidx.lifecycle.MutableLiveData;

import com.gasbuddy.imageapp.imagefetchersdk.sources.imgur.models.ImgurAlbum;
import com.gasbuddy.imageapp.imagefetchersdk.sources.imgur.models.ImgurAlbumResponse;
import com.gasbuddy.imageapp.imagefetchersdk.sources.imgur.models.ImgurImage;
import com.gasbuddy.imageapp.imagefetchersdk.sources.baseparsers.BaseAlbumParserCallback;
import com.gasbuddy.imageapp.models.ImageMetadata;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImgurAlbumResponseParserCallback extends BaseAlbumParserCallback implements Callback<ImgurAlbumResponse> {

    @Override
    public void onResponse(Call<ImgurAlbumResponse> call, Response<ImgurAlbumResponse> response) {
        //Error Handling
        if (response.body() != null ){
            getImageListLiveData().postValue(getImageListFromImgurResponse(response.body().getAlbum()));
        }
    }

    @Override
    public void onFailure(Call<ImgurAlbumResponse> call, Throwable t) {
        getImageListLiveData().postValue(null);
    }


    /*
    This method takes the ImgurAlbumResponse and returns ImageMetaDataList
     */
    private List<ImageMetadata>  getImageListFromImgurResponse(ImgurAlbum imgurAlbumResponse) throws IllegalArgumentException {
        List<ImgurImage> imgurImagesList = imgurAlbumResponse.getImages();
        List<ImageMetadata> imageMetadataList = new ArrayList<>();
        for (ImgurImage imgurImage : imgurImagesList) {
            imageMetadataList.add(getImageMetadataFromImgurImage(imgurImage));
        }
        return imageMetadataList;
    }

    private ImageMetadata getImageMetadataFromImgurImage(ImgurImage imgurImage) {
        return new ImageMetadata(imgurImage.getId(), imgurImage.getLink());
    }

}
