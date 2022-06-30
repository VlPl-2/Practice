package com.example.practice.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
//замена на внутреннюю модель
import com.example.practice.data.repository.API.Datum
import com.example.practice.data.repository.API.RemoteImpl
import com.example.practice.data.repository.DBrepository.UserItem
import com.example.practice.data.repository.DBrepository.UserItemDAO
import com.example.practice.domain.usecase.GetCollectionFromURLUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

//private val userItemDAO: UserItemDAO
class MainViewModel(): ViewModel() {
    //
    //работа с получением данных по ссылке
    private val remoteUserImpl = RemoteImpl()
    private val getCollectionFromURLUseCase = GetCollectionFromURLUseCase(remoteUserImpl)

    //замена на внутреннюю модель
    private val _usersResultStateFlow = MutableStateFlow<List<Datum>>(emptyList())
    val usersResultStateFlow: StateFlow<List<Datum>>
    get() = _usersResultStateFlow

    //
    fun loadUserData(){
        viewModelScope.launch {
            _usersResultStateFlow.emit(getCollectionFromURLUseCase.GetAllUserList())
            //userApiInterface.getUsers().body()?.data?.let { _usersResultStateFlow.emit(it) }
        }
    }

    //
    //работа с БД
    //добавление созданного элемента в БД
    /*
    private fun insertItem(item: UserItem) {
        viewModelScope.launch {
            userItemDAO.insert(item)
        }
    }

    private fun getNewItemEntry(
        email: String,
        firstName: String,
        lastName: String,
        avatar: String): UserItem {
        return UserItem(
            email = email,
            firstName = firstName,
            lastName = lastName,
            avatar = avatar
        )
    }
    */
}

/*
class MainViewModelFactory(private val userItemDAO: UserItemDAO) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(userItemDAO) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
*/