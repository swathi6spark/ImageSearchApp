package com.gasbuddy.imageapp.models

import android.os.Parcel
import android.os.Parcelable

data class ImageMetadata(
    val image_id: String,
    val image_url: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readString()!!) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(image_id)
        parcel.writeString(image_url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ImageMetadata> {
        override fun createFromParcel(parcel: Parcel): ImageMetadata {
            return ImageMetadata(parcel)
        }

        override fun newArray(size: Int): Array<ImageMetadata?> {
            return arrayOfNulls(size)
        }
    }
}