package com.example.practice.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.practice.R
//замена на внутреннюю модель
import com.example.practice.data.repository.API.Datum
import com.example.practice.data.repository.DBrepository.UserItem
import com.example.practice.data.repository.DBrepository.UserItemDAO
import com.example.practice.data.repository.DBrepository.UserItemRoomDatabase
import com.example.practice.presentation.entities.ProjectApplication
import com.example.practice.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    //объект ViewModel
    private val viewModel : MainViewModel by viewModels()

    //замена на внутреннюю модель
    val a = mutableListOf<Datum>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.loadUserData()

        lifecycleScope.launch{
            viewModel.usersResultStateFlow.collect{
                a.addAll(it)
                a
            }
        }

    }
}
