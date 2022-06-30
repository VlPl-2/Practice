package com.example.practice.data.repository.DBrepository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserItem::class], version = 1, exportSchema = false)
abstract class UserItemRoomDatabase: RoomDatabase() {
    abstract fun userItemDao(): UserItemDAO
    companion object {
        @Volatile
        private var INSTANCE: UserItemRoomDatabase? = null

        fun getDatabase(context: Context): UserItemRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserItemRoomDatabase::class.java,
                    "userItem_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
