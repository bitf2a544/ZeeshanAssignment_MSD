package com.example.zeeshanassignmentmsd.data.remote.api

import com.example.zeeshanassignmentmsd.data.model.DeckOfCards
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("api/deck/new/draw/")
    suspend fun getLatestDecOfCards(
        @Query(
            "count",
            encoded = true
        ) count: Int?
    ): Response<DeckOfCards>
}