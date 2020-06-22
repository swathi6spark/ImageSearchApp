package com.gasbuddy.imageapp.imagedetails;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gasbuddy.imageapp.BaseActivity;
import com.gasbuddy.imageapp.R;
import com.gasbuddy.imageapp.models.ImageDetails;
import com.gasbuddy.imageapp.models.ImageMetadata;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;


public class ImageDetailsActivity extends BaseActivity {

    private ImageDetailsViewModel imageDetailsViewModel;
    private ImageView detailImage;
    private TextView imageTitle, imageDesc, imageViews;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);
        initViews();

        if (!getIntent().hasExtra("image_details")){
            // Error Handling
        }

        ImageMetadata imageMetadata = getIntent().getParcelableExtra("image_details");
        String imageId = imageMetadata.getImage_id();

        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        imageDetailsViewModel = new ViewModelProvider(this, factory).get(ImageDetailsViewModel.class);

        imageDetailsViewModel.init(imageId);
        imageDetailsViewModel.imageDetailsLiveData.observe(this, imageDetails -> {
            imageDetailsLoaded(imageDetails);
        });

    }

    private void imageDetailsLoaded(ImageDetails imageDetails){


        progressBar.setVisibility(View.GONE);

        if (imageDetails == null){
            // Error loading image details
            toast("Failed Loading Image Details");
            return;
        }

        // Load image and other details , make a network call for getting image details from id
        Picasso.get().load(imageDetails.getLink()).into(detailImage, new Callback() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onError(Exception e) {
                toast("Failed Loading Image");
            }
        });

        // For title, desc , date, views check if they are nonnull then only set the data.
        if (!TextUtils.isEmpty(imageDetails.getTitle())){
            imageTitle.setText(imageDetails.getTitle());
        }

        if (!TextUtils.isEmpty(imageDetails.getDescription())){
            imageDesc.setText(imageDetails.getDescription());
        }

        imageViews.setText(String.valueOf(imageDetails.getViews()));

    }

    private void initViews() {
        detailImage = findViewById(R.id.image);
        imageTitle = findViewById(R.id.imageTitleTV);
        imageDesc = findViewById(R.id.imageDescTV);
        imageViews = findViewById(R.id.imageViewsTV);
        progressBar = findViewById(R.id.progressbar);
    }

}
