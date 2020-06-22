package com.gasbuddy.imageapp.imagefetchersdk.sources.imgur.network;



import com.gasbuddy.imageapp.imagefetchersdk.sources.imgur.models.ImgurAlbumResponse;
import com.gasbuddy.imageapp.imagefetchersdk.sources.imgur.models.ImgurImageDetailResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ImgurWebService {

    String AUTH_HEADER = "Client-ID d36ee23fad2ae45";

    @GET("album/{album_id}")
    Call<ImgurAlbumResponse> getImageList(@Header("Authorization") String authHeader,
                                          @Path(value = "album_id", encoded = true) String albumId);

    @GET("image/{image_id}")
    Call<ImgurImageDetailResponse> getImageDetails(@Header("Authorization") String authHeader,
                                                   @Path(value = "image_id", encoded = true) String imageId);

}
