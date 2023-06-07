package com.lumitest.data.network.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
class ErrorApiResponse(
    @SerialName("status") val status: String,
    @SerialName("message") val message: String,
    @SerialName("result") val result: String
)
