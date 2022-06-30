package com.example.practice.di

import com.example.practice.data.repository.DBrepository.DataBaseImpl
import com.example.practice.domain.repository.DataBase
import org.koin.dsl.module

val dataModule = module {
    single {
        DataBaseImpl(context = get())

    }


}