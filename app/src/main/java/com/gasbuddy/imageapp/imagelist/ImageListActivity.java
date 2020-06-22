package com.gasbuddy.imageapp.imagelist;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

import com.gasbuddy.imageapp.BaseActivity;
import com.gasbuddy.imageapp.R;
import com.gasbuddy.imageapp.apppreferences.AppPreferences;
import com.gasbuddy.imageapp.apppreferences.IMAGE_SOURCE;
import com.gasbuddy.imageapp.imagedetails.ImageDetailsActivity;
import com.gasbuddy.imageapp.models.ImageMetadata;
import com.gasbuddy.imageapp.recyclerview.ImageAdapter;
import com.gasbuddy.imageapp.recyclerview.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class ImageListActivity extends BaseActivity {

    private ImageAdapter imageAdapter;
    private RecyclerView recyclerView;
    private ImageListViewModel imageListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

        initializeRecyclerView();
        initUI();

        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        imageListViewModel = new ViewModelProvider(this, factory).get(ImageListViewModel.class);

        imageListViewModel.init();
        imageListViewModel.imageListLiveData.observe(this, new Observer<List<ImageMetadata>>() {
            @Override
            public void onChanged(List<ImageMetadata> imageMetadata) {
                imageAdapter.updateList(imageMetadata);
            }
        });

    }


    private void initializeRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        imageAdapter = new ImageAdapter(new ArrayList<>(),clickListener);
        recyclerView.setAdapter(imageAdapter);
    }

    private RecyclerItemClickListener clickListener = new RecyclerItemClickListener() {
        @Override
        public void onItemClick(ImageMetadata image) {
            openImageDetailsPage(image);
        }
    };


    private void openImageDetailsPage(ImageMetadata imageMetadata){
        Intent detailsIntent = new Intent(this, ImageDetailsActivity.class);
        // Pass image id , url and retrieve it in details activity
        detailsIntent.putExtra("image_details",imageMetadata);
        startActivity(detailsIntent);
    }



    private void modifyListOrientation(boolean shouldShowGrid) {
        RecyclerView.LayoutManager layoutManager;
        if (shouldShowGrid) {
            layoutManager =  new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        }else{
            layoutManager = new LinearLayoutManager(this);
        }
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.invalidate();
    }


    private void initUI(){

        ((Switch)findViewById(R.id.lytSwitch)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                modifyListOrientation(isChecked);
            }
        });

        ((Spinner)findViewById(R.id.srcSpinner)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedSrc = parent.getItemAtPosition(position).toString();
                selectImgSource(selectedSrc);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectImgSource("");
            }
        });
    }

    private void selectImgSource(String selectedSrc) {
        if (selectedSrc.contains("UnSplash")){
            AppPreferences.INSTANCE.setCurrentImageSrc(IMAGE_SOURCE.IMG_SRC_UNSPLASH);
        }else{
            AppPreferences.INSTANCE.setCurrentImageSrc(IMAGE_SOURCE.IMG_SRC_IMGUR);
        }
        imageListViewModel.init();
    }
}
