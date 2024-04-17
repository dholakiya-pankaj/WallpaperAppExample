package com.example.wallpaperapp.domain.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Urls(
    @SerializedName("regular")
    var regularImage : String? = null,
    @SerializedName("small")
    var smallImage: String? = null
)