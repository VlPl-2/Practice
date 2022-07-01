package com.example.practice.data.models

import com.google.gson.annotations.SerializedName

data class GetAllData (
    val page: Long,

    @SerializedName("per_page")
    val perPage: Long,

    val total: Long,

    @SerializedName("total_pages")
    val totalPages: Long,

    val data: List<UserApi>,
)