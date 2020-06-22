package com.gasbuddy.imageapp.imagefetchersdk.sources.imgur.models

import com.google.gson.annotations.SerializedName

data class ImgurAlbum(
        @SerializedName("images") val images: List<ImgurImage>,
        @SerializedName("images_count") val images_count: Int,
        @SerializedName("is_album") val is_album: Boolean,
        @SerializedName("link") val link: String
)