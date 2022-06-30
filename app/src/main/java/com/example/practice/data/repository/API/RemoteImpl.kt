package com.example.practice.data.repository.API

import com.example.practice.data.repository.API.Datum
import com.example.practice.domain.repository.Remote

class RemoteImpl: Remote {
    override suspend fun getAllData(): List<Datum> {
        return RetrofitFactory.userApiInterface.getUsers().body()?.data.orEmpty()
    }
}
