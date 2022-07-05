package com.example.practice.di

import com.example.practice.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module{
    viewModel<MainViewModel>{
        MainViewModel(
            saveCollectionInDataBaseUseCase = get(),
            showUsersFromDataBaseUseCase = get(),
            deleteUserUseCase = get(),
            showCurrentUserDataUseCase = get()
        ) }
}
