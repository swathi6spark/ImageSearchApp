package com.gasbuddy.imageapp.imagefetchersdk.sources.imgur.models

import com.google.gson.annotations.SerializedName

data class ImgurImage(
        @SerializedName("description") val description: String,
        @SerializedName("id") val id: String,
        @SerializedName("link") val link: String,
        @SerializedName("title") val title: String,
        @SerializedName("views") val views: Int
)