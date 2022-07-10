package com.example.practice.di

import com.example.practice.presentation.view.RecycleViewAdapter
import com.example.practice.presentation.view.UserEditFragment
import com.example.practice.presentation.viewmodel.UserDetailsFragmentViewModel
import com.example.practice.presentation.viewmodel.UserEditFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<com.example.practice.presentation.viewmodel.UserListFragmentViewModel> {
        com.example.practice.presentation.viewmodel.UserListFragmentViewModel(
            saveCollectionInDataBaseUseCase = get(),
            showUsersFromDataBaseUseCase = get(),
        )
    }
    viewModel<UserDetailsFragmentViewModel> {
        UserDetailsFragmentViewModel(
            deleteUserUseCase = get()
        )
    }
    viewModel<UserEditFragmentViewModel>{
        UserEditFragmentViewModel(
            editCurrentUserDataUseCase = get()
        )
    }
    factory<RecycleViewAdapter> {
        RecycleViewAdapter(
            userMapping = get()
        )
    }
}
