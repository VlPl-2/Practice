package com.example.practice.di

import com.example.practice.domain.usecase.SaveCollectionInDataBaseUseCase
import com.example.practice.domain.usecase.ShowUsersFromDataBaseUseCase
import org.koin.dsl.module

val domainModule = module {
    factory <SaveCollectionInDataBaseUseCase>{
        SaveCollectionInDataBaseUseCase(
            dataBase = get()
        )
    }
    factory <ShowUsersFromDataBaseUseCase>{
        ShowUsersFromDataBaseUseCase(
            dataBase = get()
        )
    }
}
