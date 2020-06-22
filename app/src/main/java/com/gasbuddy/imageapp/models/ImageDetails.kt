package com.gasbuddy.imageapp.models

/*
This class is a generalised representation of Image Details
Any source Image Data should be mapped to this as this is the model effectively used by the UI
having this abstraction remove hard dependencies on Image Source and helps scale the app for different sources
 */
data class ImageDetails(var id: String,
                        var link: String,
                        var title: String?,
                        var description: String?,
                        var views: Int)
