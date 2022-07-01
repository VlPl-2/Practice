package com.example.practice.data.repository.DBrepository

import com.example.practice.data.mapping.UserMapping
import com.example.practice.data.repository.API.RetrofitFactory
import com.example.practice.domain.models.UserModel
import com.example.practice.domain.repository.DataBase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataBaseImpl(
    val userItemRoomDatabase: UserItemRoomDatabase,
    val userMapping: UserMapping,
    val retrofitFactory: RetrofitFactory
) : DataBase {

     suspend fun getDataURL() {
         retrofitFactory.userApiInterface.getUsers().body()?.data?.map {
             userApi ->  userMapping.mappingDatumToItem(userApi)
         }
             ?.let { listUserItem ->
                 userItemRoomDatabase.userItemDao().insertAll(listUserItem)
             }
     }

    override suspend fun getData(): Flow<List<UserModel>> {
        getDataURL()
        return userItemRoomDatabase.userItemDao().getAllItems().map { listUserItem ->
            listUserItem.map { userItem ->
                userMapping.mappingItemToModel(userItem)
            }
        }
    }

    override suspend fun saveData(userModel: UserModel) {
        userItemRoomDatabase.userItemDao()
            .insert(userMapping.mappingModelToItem(userModel))
    }

}
