package com.example.todo.database

import android.content.Context
import androidx.room.*

@Database(entities = [ListItemEntity::class], version = 1)
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
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}