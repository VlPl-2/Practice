package com.example.practice.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice.domain.models.UserModel
import com.example.practice.domain.usecase.EditCurrentUserDataUseCase
import kotlinx.coroutines.launch

class UserEditFragmentViewModel(val editCurrentUserDataUseCase: EditCurrentUserDataUseCase) :
    ViewModel() {


    //редактирование элемента
    fun editItem(editedUser: UserModel) {
        viewModelScope.launch {
            editCurrentUserDataUseCase.editItem(editedUser)
        }
    }

}