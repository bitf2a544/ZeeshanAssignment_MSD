package com.example.zeeshanassignmentmsd.repository

import com.example.zeeshanassignmentmsd.data.remote.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getLatestDecOfCardsFromNetwork(count: Int?) =
        apiHelper.getLatestDecOfCardsFromAPI(count)
}