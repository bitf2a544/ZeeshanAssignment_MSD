package com.example.zeeshanassignmentmsd.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DeckCard(
    @SerializedName("code") var code: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("images") var images: CardImage? = CardImage(),
    @SerializedName("value") var value: String? = null,
    @SerializedName("suit") var suit: String? = null

) : Parcelable
