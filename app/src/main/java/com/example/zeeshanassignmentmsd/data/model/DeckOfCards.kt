package com.example.zeeshanassignmentmsd.data.model

import com.google.gson.annotations.SerializedName


data class DeckOfCards (

  @SerializedName("success"   ) var success   : Boolean?         = null,
  @SerializedName("deck_id"   ) var deckId    : String?          = null,
  @SerializedName("cards"     ) var cards     : List<DeckCard> = mutableListOf<DeckCard>(),
  @SerializedName("remaining" ) var remaining : Int?             = null
)