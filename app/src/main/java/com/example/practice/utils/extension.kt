package com.example.practice.utils

import com.example.practice.presentation.models.UserView

fun Long?.orZero() = this ?: 0

/*fun UserView?.orEmpty() = this ?: UserView(
    id = 0,
    email = "EMPTY",
    firstName = "EMPTY",
    lastName = "EMPTY",
    avatar = "EMPTY"
)*/