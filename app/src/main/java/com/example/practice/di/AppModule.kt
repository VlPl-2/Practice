package com.example.practice.di

import com.example.practice.presentation.view.RecycleViewAdapter
import com.example.practice.presentation.viewmodel.UserDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module{
    viewModel<com.example.practice.presentation.viewmodel.UserListFragmentViewModel>{
        com.example.practice.presentation.viewmodel.UserListFragmentViewModel(
            saveCollectionInDataBaseUseCase = get(),
            showUsersFromDataBaseUseCase = get(),
        )
    }
    viewModel<UserDetailsViewModel>{
        UserDetailsViewModel(
            deleteUserUseCase = get()
        )
    }
    factory <RecycleViewAdapter>{
        RecycleViewAdapter(
            userMapping = get()
        )
    }
    /*
    factory <UserListFragment>{
        UserListFragment(
        get()
        )
    }
    */
}
