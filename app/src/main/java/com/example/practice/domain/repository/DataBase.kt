package com.example.practice.domain.repository

import com.example.practice.domain.models.UserModel
import kotlinx.coroutines.flow.Flow

interface DataBase {
    suspend fun getData(): Flow<List<UserModel>>

    suspend fun saveData(userModel: UserModel)
}
