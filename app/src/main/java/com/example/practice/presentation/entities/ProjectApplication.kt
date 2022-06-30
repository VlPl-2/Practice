package com.example.practice.presentation.entities

import android.app.Application
import com.example.practice.data.repository.DBrepository.UserItemRoomDatabase

class ProjectApplication: Application() {

    val database: UserItemRoomDatabase by lazy { UserItemRoomDatabase.getDatabase(
        applicationContext
    ) }

}
