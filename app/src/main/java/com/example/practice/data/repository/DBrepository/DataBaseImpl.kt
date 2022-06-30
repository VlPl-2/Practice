package com.example.practice.data.repository.DBrepository

import android.content.Context
import com.example.practice.data.mapping.UserMapping
import com.example.practice.data.repository.API.Datum
import com.example.practice.domain.models.UserModel
import com.example.practice.domain.repository.DataBase
import java.util.concurrent.Flow

class DataBaseImpl(context: Context): DataBase {
    private var a: UserItemRoomDatabase? = null
    var b = context

    override suspend fun getData(): List<UserModel> {
        val userMapping: UserMapping = UserMapping()
        userMapping.mappingDataList()
        return UserItemRoomDatabase.getDatabase(b).userItemDao().getAllItems()
    }

    override suspend fun saveData(): List<UserItem> {
        TODO("Not yet implemented")
    }
}
