package com.gasbuddy.imageapp.imagefetchersdk.sources.unsplash.network;

import com.gasbuddy.imageapp.imagefetchersdk.sources.unsplash.models.UnSplashImage;
import com.gasbuddy.imageapp.imagefetchersdk.sources.unsplash.models.UnSplashSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UnSplashWebService {

    String AUTH_HEADER = "Client-ID KmjwXsVgONX5iWT_piv3UTmQRlgoFcYlk1M_xVqmTjI";

    @GET("search/photos?page=1")
    Call<UnSplashSearchResponse> getImageList(@Header("Authorization") String authHeader,
                                              @Query(value = "query", encoded = true) String query);

    @GET("photos/{photo_id}")
    Call<UnSplashImage> getImageDetails(@Header("Authorization") String authHeader,
                                        @Path(value = "photo_id", encoded = true) String photoId);

}
