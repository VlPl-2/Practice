package com.example.practice.domain.usecase

import com.example.practice.domain.models.UserModel
import com.example.practice.domain.repository.DataBase

class EditCurrentUserDataUseCase(val dataBase: DataBase) {

    suspend fun editItem(userModel: UserModel) = dataBase.editItem(userModel)

}