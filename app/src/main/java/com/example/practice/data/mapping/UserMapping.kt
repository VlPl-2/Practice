package com.example.practice.data.mapping

import androidx.room.ColumnInfo
import com.example.practice.data.repository.API.Datum
import com.example.practice.data.repository.DBrepository.UserItem
import com.example.practice.domain.models.UserModel

class UserMapping(
) {
    fun mappingDataList(list: List<UserItem>):List<UserModel>{
        var exitlist = mutableListOf<UserModel>()

        list.forEach {
            val elem = mappingDataUserModel(it.id, it.email, it.firstName, it.lastName, it.avatar)
            exitlist.add(elem)
        }
        return  exitlist
    }

    fun mappingDataUserModel(id: Long,
                             email: String,
                             firstName: String,
                             lastName: String,
                             avatar: String) :UserModel {
        return UserModel(id, email, firstName, lastName, avatar)
    }
}