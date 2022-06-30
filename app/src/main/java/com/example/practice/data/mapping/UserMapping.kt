package com.example.practice.data.mapping

import androidx.room.ColumnInfo
import com.example.practice.data.repository.API.Datum
import com.example.practice.data.repository.DBrepository.UserItem
import com.example.practice.domain.models.UserModel

class UserMapping() {
    fun mappingItemToModel(userItem: UserItem): UserModel {
        return UserModel(
            id = userItem.id,
            email = userItem.email,
            firstName = userItem.firstName,
            lastName = userItem.lastName,
            avatar = userItem.avatar
        )
    }

    fun mappingModelToItem(userModel: UserModel): UserItem {
        return UserItem(
            id = userModel.id,
            email = userModel.email,
            firstName = userModel.firstName,
            lastName = userModel.lastName,
            avatar = userModel.avatar
        )
    }
}
