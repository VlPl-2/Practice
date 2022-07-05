package com.example.practice.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.practice.R
import com.example.practice.databinding.RecycleViewItemBinding
import com.example.practice.domain.models.UserModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch


class RecycleViewAdapter() : RecyclerView.Adapter<RecycleViewAdapter.UsersHolder>() {

    val usersList = ArrayList<UserModel>()

    class UsersHolder(
        view: View,
    ) : RecyclerView.ViewHolder(view) {
        private val binding = RecycleViewItemBinding.bind(view)

        fun bind(user: UserModel, position: Int? = null) {
            binding.firstLastName.text = user.firstName + " " + user.lastName
            binding.imageView.load(user.avatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycle_view_item, parent, false)
        return UsersHolder(view)
    }


    override fun onBindViewHolder(holder: UsersHolder, position: Int) {
        holder.bind(usersList[position], position)

        holder.itemView.setOnClickListener() {
            val params = arrayOf(
                usersList[position].id.toString(),
                usersList[position].firstName,
                usersList[position].lastName,
                usersList[position].email,
                usersList[position].avatar
            )
            val action = UserListFragmentDirections
                .actionUserListFragmentToUserDetailsFragment(params)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    fun addUser(user: UserModel) {
        usersList.add(user)
        notifyDataSetChanged()
    }

    //добавление списком
    fun refreshUsers(list: List<UserModel>) {
        usersList.clear()
        list.forEach { user -> addUser(user) }
    }
}
