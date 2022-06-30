package com.example.practice.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.practice.R
//замена на внутреннюю модель
import com.example.practice.data.repository.API.Datum
import com.example.practice.data.repository.DBrepository.UserItem
import com.example.practice.data.repository.DBrepository.UserItemDAO
import com.example.practice.data.repository.DBrepository.UserItemRoomDatabase
import com.example.practice.databinding.ActivityMainBinding
import com.example.practice.presentation.entities.ProjectApplication
import com.example.practice.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    //объект ViewModel
    //private val viewModel : MainViewModel by viewModels()
    private val viewModel by viewModel<MainViewModel>()

    //private var binding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind, R.id.actMain)

    //замена на внутреннюю модель
    val a = mutableListOf<Datum>()

    private lateinit var getButton : Button
    private lateinit var saveButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getButton = findViewById(R.id.getUser)
        saveButton = findViewById(R.id.saveUser)
/*
        viewModel.loadUserData()


        lifecycleScope.launch{
            viewModel.usersResultStateFlow.collect{
                a.addAll(it)
                a
            }
        }
        */

        getButton.setOnClickListener{
            viewModel.loadUserDataBD()
            lifecycleScope.launch{
                viewModel.usersResultStateFlow.collect{
                    var a = it
                    a
                }
            }
        }

        saveButton.setOnClickListener{
            viewModel.insertItem(1,"name","Nameer")

        }
    }



}
