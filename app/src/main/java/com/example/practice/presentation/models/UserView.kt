package com.example.practice.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserView (
    val id: Long,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String
): Parcelable