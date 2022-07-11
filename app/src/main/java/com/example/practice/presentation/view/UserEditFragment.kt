package com.example.practice.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import coil.load
import com.example.practice.R
import com.example.practice.databinding.FragmentUserEditBinding
import com.example.practice.domain.models.UserModel
import com.example.practice.presentation.viewmodel.UserEditFragmentViewModel
import com.example.practice.utils.mapping.UserMapping
import com.example.practice.utils.orZero
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserEditFragment() : Fragment() {
    private var binding: FragmentUserEditBinding? = null
    private val viewModel: UserEditFragmentViewModel by viewModel()
    val userMapping: UserMapping by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserEditBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userView =
            arguments?.let { bundle -> UserEditFragmentArgs.fromBundle(bundle).userView }

        (activity as AppCompatActivity).supportActionBar?.setTitle(R.string.fragmentUserEditTitle)

        binding?.apply {
            editTextName.setText(userView?.firstName)
            editTextSurname.setText(userView?.lastName)
            editTextEmail.setText(userView?.email)
            imgUser.load(userView?.avatar)

            buttonConfirm.setOnClickListener {
                //составляем модель по введённым в полях данным
                val editedUser = UserModel(
                    userView?.id.orZero(),
                    editTextEmail.text.toString(),
                    editTextName.text.toString(),
                    editTextSurname.text.toString(),
                    userView?.avatar.orEmpty()
                )
                //редактируем её в БД
                viewModel.editItem(editedUser)
                //отправляем toast об успехе редактирования
                Toast.makeText(requireContext(), R.string.toastTextEditSuccess, Toast.LENGTH_LONG)
                    .show()
                //переходим на UserDetailFragment, будут отображаться уже изменённые данные
                val action =
                    UserEditFragmentDirections.actionUserEditFragmentToUserDetailsFragment(
                        userMapping.mappingUserModelToUserView(editedUser)
                    )
                view.findNavController().navigate(action)
            }

            //подняться по стеку вверх
            buttonClose.setOnClickListener {
                view.findNavController().navigateUp()
            }
        }
    }
}