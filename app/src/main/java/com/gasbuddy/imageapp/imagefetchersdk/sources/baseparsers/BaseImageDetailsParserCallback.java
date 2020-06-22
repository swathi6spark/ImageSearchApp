package com.gasbuddy.imageapp.imagefetchersdk.sources.baseparsers;

import androidx.lifecycle.MutableLiveData;

import com.gasbuddy.imageapp.models.ImageDetails;

abstract public class BaseImageDetailsParserCallback {

    private MutableLiveData<ImageDetails> imageDetailsLiveData;

    public void setImageDetailsLiveData(MutableLiveData<ImageDetails> imageDetailsLiveData) {
        this.imageDetailsLiveData = imageDetailsLiveData;
    }

    public MutableLiveData<ImageDetails> getImageDetailsLiveData(){
        return imageDetailsLiveData;
    }

}
