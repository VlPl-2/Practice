package com.example.practice.domain.models


//внутренняя модель
data class UserModel(
    val id: Long,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String
)
