package com.lumitest.data.network

import com.lumitest.data.network.model.SampleModel
import retrofit2.http.GET
import retrofit2.http.Query

interface CommonApi {
    @GET("/sample/api")
    suspend fun getSampleData(
        @Query("parameter") parameter: Double
    ): SampleModel
}