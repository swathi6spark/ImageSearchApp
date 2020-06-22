package com.gasbuddy.imageapp.imagefetchersdk.repository;

import androidx.lifecycle.MutableLiveData;

import com.gasbuddy.imageapp.apppreferences.AppPreferences;
import com.gasbuddy.imageapp.imagefetchersdk.sources.imgur.network.ImgurWebService;
import com.gasbuddy.imageapp.imagefetchersdk.sources.imgur.parsers.ImgurAlbumResponseParserCallback;
import com.gasbuddy.imageapp.imagefetchersdk.sources.imgur.parsers.ImgurImageDetailsResponseParserCallback;
import com.gasbuddy.imageapp.imagefetchersdk.sources.unsplash.network.UnSplashWebService;
import com.gasbuddy.imageapp.imagefetchersdk.sources.unsplash.parsers.UnSplashImageDetailsResponseParserCallback;
import com.gasbuddy.imageapp.imagefetchersdk.sources.unsplash.parsers.UnSplashSearchResponseParserCallback;
import com.gasbuddy.imageapp.models.ImageDetails;
import com.gasbuddy.imageapp.models.ImageMetadata;

import java.util.List;

public class ImageRepository {

    public void getImageDetails(String imageId,MutableLiveData<ImageDetails> imageDetailsLiveData){

        switch (AppPreferences.INSTANCE.getCurrentImageSrc()){
            case IMG_SRC_IMGUR:
                ImgurImageDetailsResponseParserCallback imgurImageDetailsCallback = new ImgurImageDetailsResponseParserCallback();
                imgurImageDetailsCallback.setImageDetailsLiveData(imageDetailsLiveData);
                getImgurWebService().getImageDetails(getAuthHeader(),imageId).enqueue(imgurImageDetailsCallback);
                break;
            case IMG_SRC_UNSPLASH:
            default:
                UnSplashImageDetailsResponseParserCallback unsplashImageDetailsCallback = new UnSplashImageDetailsResponseParserCallback();
                unsplashImageDetailsCallback.setImageDetailsLiveData(imageDetailsLiveData);
                getUnSplashWebService().getImageDetails(getAuthHeader(),imageId).enqueue(unsplashImageDetailsCallback);
                break;
        }

    }

    public void getImageList(String albumId, MutableLiveData<List<ImageMetadata>> imageListLiveData) {
        switch (AppPreferences.INSTANCE.getCurrentImageSrc()){
            case IMG_SRC_IMGUR:
                ImgurAlbumResponseParserCallback imgurImageDetailsCallback = new ImgurAlbumResponseParserCallback();
                imgurImageDetailsCallback.setImageListLiveData(imageListLiveData);
                getImgurWebService().getImageList(getAuthHeader(),albumId).enqueue(imgurImageDetailsCallback);
                break;
            case IMG_SRC_UNSPLASH:
            default:
                UnSplashSearchResponseParserCallback unsplashImageDetailsCallback = new UnSplashSearchResponseParserCallback();
                unsplashImageDetailsCallback.setImageListLiveData(imageListLiveData);
                getUnSplashWebService().getImageList(getAuthHeader(),albumId).enqueue(unsplashImageDetailsCallback);
                break;
        }
    }

    private static ImageRepository single_instance = null;
    private static ImgurWebService imgurWebService = null;
    private static UnSplashWebService unSplashWebService = null;

    private ImageRepository() {
    }

    public static ImageRepository getInstance() {
        if (single_instance == null){
            single_instance = new ImageRepository();
        }

        return single_instance;
    }

    private static ImgurWebService getImgurWebService(){
        if (imgurWebService == null){
            imgurWebService = ApiClient.getClient().create(ImgurWebService.class);
        }
        return imgurWebService;
    }

    private static UnSplashWebService getUnSplashWebService(){
        if (unSplashWebService == null){
            unSplashWebService = ApiClient.getClient().create(UnSplashWebService.class);
        }
        return unSplashWebService;
    }

    private String getAuthHeader(){
        switch (AppPreferences.INSTANCE.getCurrentImageSrc()){
            case IMG_SRC_IMGUR:
                return ImgurWebService.AUTH_HEADER;
            case IMG_SRC_UNSPLASH:
                return UnSplashWebService.AUTH_HEADER;
        }
        return "";
    }

}
