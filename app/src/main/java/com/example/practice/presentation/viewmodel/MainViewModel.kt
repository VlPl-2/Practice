package com.example.practice.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
//замена на внутреннюю модель
import com.example.practice.data.repository.API.Datum
import com.example.practice.data.repository.API.RemoteImpl
import com.example.practice.data.repository.DBrepository.DataBaseImpl
import com.example.practice.data.repository.DBrepository.UserItem
import com.example.practice.data.repository.DBrepository.UserItemDAO
import com.example.practice.domain.models.UserModel
import com.example.practice.domain.usecase.GetCollectionFromURLUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(private val dataBaseImpl: DataBaseImpl) : ViewModel() {
    //
    //работа с получением данных по ссылке
    private val remoteUserImpl = RemoteImpl()
    private val getCollectionFromURLUseCase = GetCollectionFromURLUseCase(remoteUserImpl)

    //замена на внутреннюю модель
    private val _usersResultStateFlow = MutableStateFlow<List<UserModel>>(emptyList())
    val usersResultStateFlow: StateFlow<List<UserModel>>
        get() = _usersResultStateFlow

    //private  val dataBaseImpl = DataBaseImpl()


    //
    /* переписать под <UserModel>
    fun loadUserData() {
        viewModelScope.launch {
            _usersResultStateFlow.emit(getCollectionFromURLUseCase.GetAllUserList())
            //userApiInterface.getUsers().body()?.data?.let { _usersResultStateFlow.emit(it) }
        }
    }
*/
    //
    //работа с БД
    //добавление созданного элемента в БД

    public fun insertItem(idUser: Long, firstNameUser: String, lastNameUser: String) {
        viewModelScope.launch {
            dataBaseImpl.saveData(
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

    fun loadUserDataBD() {
        viewModelScope.launch {
            dataBaseImpl.getData().collect{
                _usersResultStateFlow.emit(it)
            }
        }
    }

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