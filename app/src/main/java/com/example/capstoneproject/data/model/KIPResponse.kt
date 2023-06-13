package com.example.capstoneproject.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class KIPResponse(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("data")
    val data: Any? = null,

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("status")
    val status: Int? = null
)