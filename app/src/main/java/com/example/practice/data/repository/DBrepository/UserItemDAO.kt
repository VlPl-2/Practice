package com.example.practice.data.repository.DBrepository

import androidx.room.*
import com.example.practice.data.models.UserItem
import kotlinx.coroutines.flow.Flow

//команды взаимодействия с базой данных
@Dao
interface UserItemDAO {
    //вывод всей БД
    @Query("SELECT * from userItem ORDER BY id ASC")
    fun getAllItems(): Flow<List<UserItem>>

    //вывод конкретного элемента по ID
    @Query("SELECT * from userItem WHERE id = :id")
    fun getCurrentItem(id: Int): Flow<UserItem>

    //добавить элемент
    // Если пользователь пытается добавить существующий элемент, запрос игнорируется
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: UserItem)

    //добавить все элементы
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(list: List<UserItem>)

    //обновить элемент
    @Update
    suspend fun update(item: UserItem)

    //удалить элемент
    @Delete
    suspend fun delete(item: UserItem)

    @Query("DELETE FROM userItem WHERE id = :id")
    suspend fun deleteFromId(id: Long)

    /*
    //изменить элемент
    //вот тут придумать что-нибудь
    @Query("UPDATE userItem " +
            "SET ")
    suspend fun editItem(item: UserItem)
     */
}