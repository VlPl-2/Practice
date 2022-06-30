package com.example.practice.data.repository.DBrepository

import android.content.Context
import com.example.practice.data.mapping.UserMapping
import com.example.practice.data.repository.API.Datum
import com.example.practice.domain.models.UserModel
import com.example.practice.domain.repository.DataBase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataBaseImpl(context: Context): DataBase {
    private var a: UserItemRoomDatabase? = null
    var b = context
    val userMapping: UserMapping = UserMapping()

    override suspend fun getData(): Flow<List<UserModel>> {
        return UserItemRoomDatabase.getDatabase(b).userItemDao().getAllItems().map { listUserItem ->
            listUserItem.map { userItem ->
                userMapping.mappingItemToModel(userItem)
            }
        }
    }

    override suspend fun saveData(userModel: UserModel) {
        UserItemRoomDatabase.getDatabase(b).userItemDao()
            .insert(userMapping.mappingModelToItem(userModel))
    }
}
