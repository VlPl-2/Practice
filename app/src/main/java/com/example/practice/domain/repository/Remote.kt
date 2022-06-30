package com.example.practice.domain.repository

//замена на внутреннюю модель
import com.example.practice.data.repository.API.Datum

interface Remote {
    suspend fun getAllData(): List<Datum>
}
