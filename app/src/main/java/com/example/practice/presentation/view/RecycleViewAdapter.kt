package com.example.practice.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.practice.R
import com.example.practice.databinding.RecycleViewItemBinding
import com.example.practice.domain.models.UserModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch


class RecycleViewAdapter(
    private val onItemClicked: (position: Int, userModel: UserModel) -> Unit
) : RecyclerView.Adapter<RecycleViewAdapter.UsersHolder>() {

    val usersList = ArrayList<UserModel>()

    class UsersHolder(
        view: View,
        private val onItemClicked: (position: Int, userModel: UserModel) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        private val binding = RecycleViewItemBinding.bind(view)

        fun saveParams(position: Int, userModel: UserModel){
            itemView.setOnClickListener{
                onItemClicked(position, userModel)
            }
        }

        fun bind(user: UserModel, position: Int? = null) {
            binding.firstLastName.text = user.firstName + " " + user.lastName
            binding.imageView.load(user.avatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycle_view_item, parent, false)
        return UsersHolder(view, onItemClicked)
    }


    override fun onBindViewHolder(holder: UsersHolder, position: Int) {
        holder.bind(usersList[position], position)
        holder.saveParams(position, usersList[position])
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    fun addUser(user: UserModel) {
        usersList.add(user)
        notifyDataSetChanged()
    }

    //добавление списком
    fun addUsers(list: List<UserModel>) {
        list.forEach { user -> addUser(user) }
    }

}
