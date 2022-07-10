package com.example.practice.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice.domain.usecase.DeleteUserUseCase
import kotlinx.coroutines.launch

class UserDetailsFragmentViewModel(
    val deleteUserUseCase: DeleteUserUseCase
    ): ViewModel() {

    //удаление элемента из базы данных по ID
    fun deleteItem(id: Long) {
        viewModelScope.launch {
            deleteUserUseCase.deleteItem(id)
        }
    }
}