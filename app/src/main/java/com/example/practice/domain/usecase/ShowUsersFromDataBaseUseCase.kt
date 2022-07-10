package com.example.practice.domain.usecase

import com.example.practice.domain.models.UserModel
import com.example.practice.domain.repository.DataBase
import kotlinx.coroutines.flow.Flow

class ShowUsersFromDataBaseUseCase(val dataBase: DataBase) {

    suspend fun getData() = dataBase.getData()

    suspend fun getLocalData() = dataBase.getLocalData()

}
