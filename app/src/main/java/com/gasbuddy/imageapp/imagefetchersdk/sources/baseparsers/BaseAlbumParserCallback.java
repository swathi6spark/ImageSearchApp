package com.gasbuddy.imageapp.imagefetchersdk.sources.baseparsers;

import androidx.lifecycle.MutableLiveData;
import com.gasbuddy.imageapp.models.ImageMetadata;
import java.util.List;

public abstract class BaseAlbumParserCallback {

    public MutableLiveData<List<ImageMetadata>> getImageListLiveData() {
        return imageListLiveData;
    }

     private MutableLiveData<List<ImageMetadata>> imageListLiveData;

    public void setImageListLiveData(MutableLiveData<List<ImageMetadata>> imageListLiveData){
        this.imageListLiveData = imageListLiveData;
    }


}
