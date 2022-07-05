package com.example.practice.domain.repository

import com.example.practice.domain.models.UserModel
import kotlinx.coroutines.flow.Flow

interface DataBase {
    suspend fun getData(): Flow<List<UserModel>>

    suspend fun saveData(userModel: UserModel)

    suspend fun deleteItem(id: Int)

    //есть ли смысл передавать в потоке 1 элемент?
    suspend fun showCurrent(id: Int): Flow<UserModel>
}
