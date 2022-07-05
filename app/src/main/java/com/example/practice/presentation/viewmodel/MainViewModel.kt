package com.example.practice.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//замена на внутреннюю модель
import com.example.practice.domain.models.UserModel
import com.example.practice.domain.usecase.DeleteUserUseCase
import com.example.practice.domain.usecase.SaveItemInDataBaseUseCase
import com.example.practice.domain.usecase.ShowCurrentUserDataUseCase
import com.example.practice.domain.usecase.ShowUsersFromDataBaseUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(
    val saveCollectionInDataBaseUseCase: SaveItemInDataBaseUseCase,
    val showUsersFromDataBaseUseCase: ShowUsersFromDataBaseUseCase,
    val deleteUserUseCase: DeleteUserUseCase,
    val showCurrentUserDataUseCase: ShowCurrentUserDataUseCase
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


    //
    //переделать под id
    //удаление элемента из базы данных по ID
    fun deleteItem(id: Int){
        viewModelScope.launch {
            deleteUserUseCase.deleteItem(showCurrentUserDataUseCase.showCurrent(id).first())
        }
    }
    //получение элемента из БД по ID
    fun getItemByID(id: Int){
        viewModelScope.launch {
            showCurrentUserDataUseCase.showCurrent(id)
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
