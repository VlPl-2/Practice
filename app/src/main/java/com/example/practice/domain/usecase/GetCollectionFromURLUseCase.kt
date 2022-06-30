package com.example.practice.domain.usecase

//замена на внутреннюю модель
import com.example.practice.data.repository.API.Datum
import com.example.practice.domain.models.UserModel
import com.example.practice.domain.repository.Remote

class GetCollectionFromURLUseCase(val remote: Remote) {

    suspend fun GetAllUserList():List<Datum> {
        return remote.getAllData()
    }
}
