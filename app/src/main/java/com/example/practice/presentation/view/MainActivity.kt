package com.example.practice.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.practice.R
import com.example.practice.databinding.ActivityMainBinding
//замена на внутреннюю модель
import com.example.practice.domain.models.UserModel
import com.example.practice.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.module.Module

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val adapter = RecycleViewAdapter{position, userModel ->  onListItemClick(position, userModel)}

    //объект ViewModel
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.loadUserDataBD()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            rcView.layoutManager = GridLayoutManager(this@MainActivity, 2)

            getUser.setOnClickListener {
                lifecycleScope.launch {
                    var a = 1
                    viewModel.usersResultStateFlow.collect {
                        rcViewInit(it)
                    }
                }
            }

            //удаление элемента из БД по ID
            deleteUser.setOnClickListener {
                viewModel.deleteItem(1)
            }

            //добавление элемента в БД
            saveUser.setOnClickListener {
                viewModel.insertItem(4, "Imya", "Lastevv")
            }
        }
    }

    private fun rcViewInit( usersList: List<UserModel>){
        binding.apply {
            //rcView.layoutManager = GridLayoutManager(this@MainActivity, 2)
            rcView.adapter = adapter
            adapter.addUsers(usersList)
        }
    }
    //действие при нажатии на элемент RecycleView
    private fun onListItemClick(position: Int, userModel: UserModel) {
        Toast.makeText(this, userModel.firstName, Toast.LENGTH_SHORT).show()
    }

}