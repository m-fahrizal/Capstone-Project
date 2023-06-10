package com.example.capstoneproject.data.model

import com.google.gson.annotations.SerializedName

data class MLResponse(
    @field:SerializedName("confidence")
    val confidence: Any? = null,

    @field:SerializedName("prediction")
    val prediction: String? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("error")
    val error: Boolean
)
