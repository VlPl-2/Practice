package com.example.practice.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import com.example.practice.R
//замена на внутреннюю модель
import com.example.practice.domain.models.UserModel
import com.example.practice.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    //объект ViewModel
    private val viewModel: MainViewModel by viewModel()

    //переписать на viewBinding
    private lateinit var getButton: Button
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.loadUserDataBD()

        getButton = findViewById(R.id.getUser)
        saveButton = findViewById(R.id.saveUser)


        lateinit var  a: List<UserModel>
        getButton.setOnClickListener {
            lifecycleScope.launch {
                viewModel.usersResultStateFlow.collect {
                    a = it
                    var c = a.size
                }
            }
        }

        saveButton.setOnClickListener {
            viewModel.insertItem(1, "name", "Nameer")
        }
    }
}