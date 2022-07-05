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
import com.example.practice.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserListFragment : Fragment() {

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding

    private val adapter = RecycleViewAdapter()
    //объект ViewModel
    private val viewModel: MainViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadUserDataBD()

        rcViewInit()

        binding?.apply {
            rcView.layoutManager = GridLayoutManager(requireContext(), 2)

            //добавление элемента в БД
            saveUser.setOnClickListener {
                viewModel.insertItem(4, "First", "Lastevv","e@mail.com", "https://cdn.onlinewebfonts.com/svg/download_568657.png")
                viewModel.insertItem(5, "Second", "Lastevv","e@mail.com", "https://cdn.onlinewebfonts.com/svg/download_568657.png")
                viewModel.insertItem(6, "Third", "Lastevv","e@mail.com", "https://cdn.onlinewebfonts.com/svg/download_568657.png")

            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = UserDetailsFragment()
    }

    //заполнение/обновление RecyclerView
    private fun rcViewInit(){
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


    //действие при нажатии на элемент RecycleView - раскрытие фрагмента с подробностями
    private fun onListItemClick(position: Int, userModel: UserModel) {

    }

}