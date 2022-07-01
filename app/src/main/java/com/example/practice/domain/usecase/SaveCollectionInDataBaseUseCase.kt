package com.example.practice.domain.usecase

import com.example.practice.domain.models.UserModel
import com.example.practice.domain.repository.DataBase

class SaveCollectionInDataBaseUseCase(val dataBase: DataBase) {

    suspend fun saveData(userModel: UserModel) = dataBase.saveData(userModel)

}