package com.example.practice.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice.domain.models.UserModel
import com.example.practice.domain.usecase.SaveItemInDataBaseUseCase
import com.example.practice.domain.usecase.ShowUsersFromDataBaseUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class UserListFragmentViewModel(
    val saveCollectionInDataBaseUseCase: SaveItemInDataBaseUseCase,
    val showUsersFromDataBaseUseCase: ShowUsersFromDataBaseUseCase,
) : ViewModel() {

    //замена на внутреннюю модель
    private val _usersResultStateFlow = MutableStateFlow<List<UserModel>>(emptyList())
    val usersResultStateFlow: StateFlow<List<UserModel>>
        get() = _usersResultStateFlow

    //
    //работа с БД
    //добавление созданного элемента в БД
    fun insertItem(id: Long, firstName: String, lastNameUser: String, email:String, avatar:String) {
        viewModelScope.launch {
            saveCollectionInDataBaseUseCase.saveData(
                UserModel(
                    id = id,
                    firstName = firstName,
                    lastName = lastNameUser,
                    email = email,
                    avatar = avatar
                )
            )
        }
    }

    //загрузка базы данных
    fun loadUserDataBD() {
        viewModelScope.launch {
            showUsersFromDataBaseUseCase.getData().collect {
                try {
                    _usersResultStateFlow.emit(it)
                }
                catch (e: Exception){
                    Log.d("TAG",e.toString())
                }
            }
        }
    }

    //загрузка локальной базы данных
    fun loadLocalUserDataBD() {
        viewModelScope.launch {
            showUsersFromDataBaseUseCase.getLocalData().collect {
                _usersResultStateFlow.emit(it)
            }
        }
    }
}
