package com.example.practice.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//замена на внутреннюю модель
import com.example.practice.domain.models.UserModel
import com.example.practice.domain.usecase.SaveCollectionInDataBaseUseCase
import com.example.practice.domain.usecase.ShowUsersFromDataBaseUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    val saveCollectionInDataBaseUseCase: SaveCollectionInDataBaseUseCase,
    val showUsersFromDataBaseUseCase: ShowUsersFromDataBaseUseCase
) : ViewModel() {

    //замена на внутреннюю модель
    private val _usersResultStateFlow = MutableStateFlow<List<UserModel>>(emptyList())
    val usersResultStateFlow: StateFlow<List<UserModel>>
        get() = _usersResultStateFlow

    //
    //работа с БД
    //добавление созданного элемента в БД
    fun insertItem(idUser: Long, firstNameUser: String, lastNameUser: String) {
        viewModelScope.launch {
            saveCollectionInDataBaseUseCase.saveData(
                UserModel(
                    id = idUser,
                    firstName = firstNameUser,
                    lastName = lastNameUser,
                    email = "",
                    avatar = ""
                )
            )
        }
    }

    //загрузка базы данных
    fun loadUserDataBD() {
        viewModelScope.launch {
            showUsersFromDataBaseUseCase.getData().collect {
                _usersResultStateFlow.emit(it)
            }
        }
    }
}
