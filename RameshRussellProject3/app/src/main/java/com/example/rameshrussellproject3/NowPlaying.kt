package com.example.rameshrussellproject3;
import com.google.gson.annotations.SerializedName;

import kotlin.jvm.JvmField;

public class NowPlaying {
    @JvmField
    @SerializedName("original_title")
    var title: String? = null

    @JvmField
    @SerializedName("overview")
    var description: String? = null

    @JvmField
    @SerializedName("poster_path")
    var image: String? = null
}
