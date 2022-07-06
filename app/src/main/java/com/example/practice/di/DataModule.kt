package com.example.practice.di

import com.example.practice.utils.mapping.UserMapping
import com.example.practice.data.repository.API.RetrofitFactory
import com.example.practice.data.repository.DBrepository.DataBaseImpl
import com.example.practice.data.repository.DBrepository.UserItemRoomDatabase
import com.example.practice.domain.repository.DataBase
import org.koin.dsl.module

val dataModule = module {
    factory <DataBase>{
        DataBaseImpl(userItemRoomDatabase = get(),
            userMapping = get(),
            retrofitFactory = get()
        )
    }
    single <UserItemRoomDatabase>{
        UserItemRoomDatabase.getDatabase(context =  get())
    }
    single <UserMapping>{
        UserMapping()
    }
    single <RetrofitFactory>{
        RetrofitFactory()
    }


}
