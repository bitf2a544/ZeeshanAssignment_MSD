package com.example.zeeshanassignmentmsd.data.remote.api

import com.example.zeeshanassignmentmsd.data.model.DeckOfCards
import retrofit2.Response

interface ApiHelper {
    suspend fun getLatestDecOfCardsFromAPI(count:Int?): Response<DeckOfCards>
}