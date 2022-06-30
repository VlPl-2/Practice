package com.example.practice.data.repository.API

import com.google.gson.annotations.SerializedName

data class GetAllData (
    val page: Long,

    @SerializedName("per_page")
    val perPage: Long,

    val total: Long,

    @SerializedName("total_pages")
    val totalPages: Long,

    val data: List<Datum>,
)

data class Datum (
    val id: Long,
    val email: String,

    @SerializedName("first_name")
    val firstName: String,

    @SerializedName("last_name")
    val lastName: String,

    val avatar: String
)
