package com.example.practice.di

import com.example.practice.domain.usecase.DeleteUserUseCase
import com.example.practice.domain.usecase.SaveItemInDataBaseUseCase
import com.example.practice.domain.usecase.ShowUsersFromDataBaseUseCase
import org.koin.dsl.module

val domainModule = module {
    factory <SaveItemInDataBaseUseCase>{
        SaveItemInDataBaseUseCase(
            dataBase = get()
        )
    }
    factory <ShowUsersFromDataBaseUseCase>{
        ShowUsersFromDataBaseUseCase(
            dataBase = get()
        )
    }
    factory <DeleteUserUseCase>{
        DeleteUserUseCase(
            dataBase = get()
        )
    }

}
