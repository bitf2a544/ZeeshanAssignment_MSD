package com.example.zeeshanassignmentmsd.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CardImage(

    @SerializedName("svg") var svg: String? = null,
    @SerializedName("png") var png: String? = null

) : Parcelable