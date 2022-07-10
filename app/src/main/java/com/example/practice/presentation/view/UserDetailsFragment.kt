package com.example.practice.presentation.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import coil.load
import com.example.practice.R
import com.example.practice.databinding.FragmentUserDetailsBinding
import com.example.practice.utils.orZero
import com.example.practice.presentation.viewmodel.UserDetailsFragmentViewModel
import com.example.practice.utils.orEmpty
import org.koin.androidx.viewmodel.ext.android.viewModel


class UserDetailsFragment : Fragment() {
    private var binding: FragmentUserDetailsBinding? = null
    //объект ViewModel
    private val viewModel: UserDetailsFragmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userView =
            arguments?.let { bundle -> UserDetailsFragmentArgs.fromBundle(bundle).userView }

        (activity as AppCompatActivity).supportActionBar?.setTitle(R.string.fragmentUserDetailsTitle)
        binding?.apply {
            textName.text = userView?.firstName
            textSurname.text = userView?.lastName
            textEmail.text = userView?.email
            imgUser.load(userView?.avatar)

            buttonDelete.setOnClickListener {
                AlertDialog.Builder(requireContext()).setTitle(R.string.alertDialogueTextTitleDelete)
                    .setMessage(R.string.alertDialogueTextMessage)
                    .setNegativeButton(R.string.alertDialogueTextNegative) { dialog, i ->
                    }
                    .setPositiveButton(R.string.alertDialogueTextPositive) { dialog, i ->
                        viewModel.deleteItem(userView?.id.orZero())
                        val action =
                            UserDetailsFragmentDirections.actionUserDetailsFragmentToUserListFragment()
                        view.findNavController().navigate(action)
                    }
                    .show()
            }

            buttonEdit.setOnClickListener {
                val action = UserDetailsFragmentDirections
                    .actionUserDetailsFragmentToUserEditFragment(userView.orEmpty())
                view.findNavController().navigate(action)
            }
        }


    }
}
