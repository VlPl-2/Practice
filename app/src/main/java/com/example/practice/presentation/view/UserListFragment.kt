package com.example.practice.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.practice.databinding.FragmentUserListBinding
import com.example.practice.domain.models.UserModel
import com.example.practice.presentation.viewmodel.UserListFragmentViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserListFragment(
) : Fragment() {
    private var binding: FragmentUserListBinding? = null
    val adapter: RecycleViewAdapter by inject()

    //объект ViewModel
    private val viewModel: UserListFragmentViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserListBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadLocalUserDataBD()
        rcViewLocalInit()

        binding?.apply {
            rcView.layoutManager = GridLayoutManager(requireContext(), 2)
            //загрузка БД из URL
            loadUsers.setOnClickListener {
                rcViewInit()
            }
        }
    }

    //заполнение/обновление RecyclerView вместе c URL
    private fun rcViewInit(){
        viewModel.loadUserDataBD()
        lifecycleScope.launch {
            viewModel.usersResultStateFlow.collect { usersList ->
                binding?.apply {
                    //rcView.layoutManager = GridLayoutManager(this@MainActivity, 2)
                    rcView.adapter = adapter
                    adapter.refreshUsers(usersList)
                }
            }
        }
    }

    //локальное заполнение/обновление RecyclerView
    private fun rcViewLocalInit(){
        lifecycleScope.launch {
            viewModel.usersResultStateFlow.collect { usersList ->
                binding?.apply {
                    rcView.adapter = adapter
                    adapter.refreshUsers(usersList)
                }
            }
        }
    }

    //действие при нажатии на элемент RecycleView - раскрытие фрагмента с подробностями
    private fun onListItemClick(position: Int, userModel: UserModel) {

    }

}