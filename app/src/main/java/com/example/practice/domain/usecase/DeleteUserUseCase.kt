package com.example.practice.domain.usecase

import com.example.practice.domain.models.UserModel
import com.example.practice.domain.repository.DataBase

class DeleteUserUseCase(val dataBase: DataBase) {

    suspend fun deleteItem(id: Long) = dataBase.deleteItem(id)

}
