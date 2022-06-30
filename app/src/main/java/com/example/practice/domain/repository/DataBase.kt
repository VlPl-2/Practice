package com.example.practice.domain.repository

import com.example.practice.data.repository.API.Datum

interface DataBase {
    suspend fun getData(): List<Datum>

    suspend fun saveData(): List<Datum>
}