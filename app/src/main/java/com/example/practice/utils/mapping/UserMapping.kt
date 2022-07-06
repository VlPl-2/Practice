package com.example.practice.utils.mapping

import com.example.practice.data.models.UserApi
import com.example.practice.data.models.UserItem
import com.example.practice.domain.models.UserModel
import com.example.practice.presentation.models.UserView

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

    fun mappingDatumToItem(datum: UserApi): UserItem {
        return UserItem(
            id = datum.id,
            email = datum.email,
            firstName = datum.firstName,
            lastName = datum.lastName,
            avatar = datum.avatar
        )
    }

    fun mappingUserModelToUserView(userModel: UserModel): UserView {
        return UserView(
            id = userModel.id,
            email = userModel.email,
            firstName = userModel.firstName,
            lastName = userModel.lastName,
            avatar = userModel.avatar
        )
    }

}
