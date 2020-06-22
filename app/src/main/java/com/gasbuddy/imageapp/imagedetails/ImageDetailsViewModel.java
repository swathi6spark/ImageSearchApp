package com.gasbuddy.imageapp.imagedetails;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gasbuddy.imageapp.imagefetchersdk.repository.ImageRepository;
import com.gasbuddy.imageapp.models.ImageDetails;

public class ImageDetailsViewModel extends ViewModel {

    LiveData<ImageDetails> imageDetailsLiveData = new MutableLiveData<>();

    void init(String id){
        ImageRepository.getInstance().getImageDetails(id,(MutableLiveData<ImageDetails>) imageDetailsLiveData);
    }

}
