package com.gasbuddy.imageapp.imagefetchersdk.sources.imgur.models

import com.google.gson.annotations.SerializedName

data class ImgurAlbumResponse(
        @SerializedName("data") val album: ImgurAlbum,
        @SerializedName("status") val status: Int,
        @SerializedName("success") val success: Boolean
)