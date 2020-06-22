package com.gasbuddy.imageapp.imagelist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gasbuddy.imageapp.apppreferences.AppPreferences;
import com.gasbuddy.imageapp.models.ImageMetadata;
import com.gasbuddy.imageapp.imagefetchersdk.repository.ImageRepository;

import java.util.List;

public class ImageListViewModel extends ViewModel {

    LiveData<List<ImageMetadata>> imageListLiveData = new MutableLiveData<>();

    void init(){
        ImageRepository.getInstance().getImageList(AppPreferences.INSTANCE.getListID(),(MutableLiveData<List<ImageMetadata>>) imageListLiveData);
    }


}
