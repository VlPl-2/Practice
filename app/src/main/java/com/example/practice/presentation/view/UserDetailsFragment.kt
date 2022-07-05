package com.example.practice.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.example.practice.databinding.FragmentUserDetailsBinding
import com.example.practice.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class UserDetailsFragment : Fragment() {
    private var _binding: FragmentUserDetailsBinding? = null
    private val binding get() = _binding
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
        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val params =
            arguments?.let { bundle -> UserDetailsFragmentArgs.fromBundle(bundle).userModel }
        binding?.apply {
            textId.text = params?.get(0)
            textName.text = params?.get(1)
            textSurname.text = params?.get(2)
            textEmail.text = params?.get(3)
            imgUser.load(params?.get(4))

            buttonDelete.setOnClickListener {
                viewModel.deleteItem(textId.text.toString().toInt())

            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = UserDetailsFragment()
    }

}