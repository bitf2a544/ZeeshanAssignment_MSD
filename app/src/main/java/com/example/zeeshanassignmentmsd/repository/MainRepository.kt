package com.example.zeeshanassignmentmsd.repository

import com.example.zeeshanassignmentmsd.data.remote.api.ApiHelper
import com.example.zeeshanassignmentmsd.utils.NetworkHelper
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiHelper: ApiHelper,
    private val networkHelper: NetworkHelper
) {

    suspend fun getLatestDecOfCardsFromNetwork(count: Int?) =
        apiHelper.getLatestDecOfCardsFromAPI(count)

    fun isNetworkAvailable(): Boolean {
       return networkHelper.isNetworkConnected()
       // return true
    }

}