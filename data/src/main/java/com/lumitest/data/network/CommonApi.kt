package com.lumitest.data.network

import com.lumitest.data.BuildConfig
import com.lumitest.data.network.model.SampleModel
import com.lumitest.data.network.model.TransactionsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CommonApi {
    @GET("/sample/api")
    suspend fun getSampleData(
        @Query("parameter") parameter: Double
    ): SampleModel

    @GET("/api")
    suspend fun getAccountTransactions(
        @Query("module") module: String = "account",
        @Query("action") action: String = "txlist",
        @Query("address") address: String,
        @Query("startblock") startBlock: Int = 0,
        @Query("endblock") endBlock: Int = 99999999,
        @Query("page") page: Int = 1,
        @Query("offset") offset: Int = 100,
        @Query("sort") sort: String = "desc",
        @Query("apikey") apiKey: String = BuildConfig.ETH_API_KEY
    ): TransactionsResponse
}