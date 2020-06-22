package com.gasbuddy.imageapp.imagefetchersdk.sources.unsplash.models

import com.google.gson.annotations.SerializedName

data class UnSplashImage(
        @SerializedName("id") val id:String,
        @SerializedName("description") val description:String?,
        @SerializedName("alt_description") val altDescription:String?,
        @SerializedName("urls") val urls: Urls,
        @SerializedName("likes") val likes:Int
)