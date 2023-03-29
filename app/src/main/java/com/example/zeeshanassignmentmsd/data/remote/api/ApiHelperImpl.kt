package com.example.zeeshanassignmentmsd.data.remote.api

import com.example.zeeshanassignmentmsd.data.model.DeckOfCards
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getLatestDecOfCardsFromAPI(count: Int?): Response<DeckOfCards> =
        apiService.getLatestDecOfCards(count)
}