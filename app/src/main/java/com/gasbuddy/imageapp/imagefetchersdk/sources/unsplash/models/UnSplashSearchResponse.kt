package com.gasbuddy.imageapp.imagefetchersdk.sources.unsplash.models

import com.gasbuddy.imageapp.imagefetchersdk.sources.unsplash.models.UnSplashImage
import com.google.gson.annotations.SerializedName

data class UnSplashSearchResponse(
        @SerializedName("results") val results: List<UnSplashImage>,
        @SerializedName("total") val total: Int
)