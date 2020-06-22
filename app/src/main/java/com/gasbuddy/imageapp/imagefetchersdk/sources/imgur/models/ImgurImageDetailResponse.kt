package com.gasbuddy.imageapp.imagefetchersdk.sources.imgur.models

import com.google.gson.annotations.SerializedName

class ImgurImageDetailResponse(@SerializedName("data") val image: ImgurImage,
                               @SerializedName("status") val status: Int,
                               @SerializedName("success") val success: Boolean)