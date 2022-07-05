package com.example.practice.domain.usecase

import com.example.practice.domain.repository.DataBase

class ShowCurrentUserDataUseCase(val dataBase: DataBase) {
    suspend fun showCurrent(id: Int) = dataBase.showCurrent(id)
}