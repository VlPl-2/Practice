package com.example.practice.di

import com.example.practice.data.repository.DBrepository.DataBaseImpl
import com.example.practice.domain.repository.DataBase
import com.example.practice.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val dataModule = module {
/*
    single {
        DataBaseImpl(context = get())
    }*/
    factory <DataBase>{
        DataBaseImpl(context = get())
    }

}
val appModule = module{
    viewModel<MainViewModel>{ MainViewModel(dataBaseImpl = get()) }
}
