package com.gasbuddy.imageapp.apppreferences

object AppPreferences {
    var currentImageSrc = IMAGE_SOURCE.IMG_SRC_IMGUR
    var currentLayoutType = LAYOUT_TYPE.LYT_VERTICAL_LIST

    // Hardcoded Search Term
    // Hardcoded Album ID
    private var IMGUR_ALBUMID = "663TV2P"
    private const val UNSPLASH_QUERY = "office"

    fun  getListID():String{
        if (currentImageSrc == IMAGE_SOURCE.IMG_SRC_UNSPLASH){
            return UNSPLASH_QUERY
        }else{
            return IMGUR_ALBUMID
        }
    }
}