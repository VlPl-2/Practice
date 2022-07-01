package com.example.practice.data.models

import com.google.gson.annotations.SerializedName

data class UserApi (
    val id: Long,
    val email: String,

    @SerializedName("first_name")
    val firstName: String,

    @SerializedName("last_name")
    val lastName: String,

    val avatar: String
)