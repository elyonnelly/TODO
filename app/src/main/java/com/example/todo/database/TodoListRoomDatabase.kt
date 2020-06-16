package com.example.todo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [ListItemEntity::class], version = 2)
@TypeConverters(DateTypeConverter::class)
abstract class TodoListRoomDatabase : RoomDatabase() {
    //наверное это @Provide??
    abstract fun todoListDao() : TodoDao

    companion object {
        @Volatile
        private var INSTANCE: TodoListRoomDatabase? = null

        fun getDatabase(context: Context): TodoListRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoListRoomDatabase::class.java,
                    "todo_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}