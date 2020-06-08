package com.example.todo.database

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DateTypeConverter {
    companion object {
        @JvmStatic
        @TypeConverter
        fun dateToString(date : LocalDate) : String {
            return date.format(DateTimeFormatter.ofPattern("dd LLLL yyyy"))
        }
        @JvmStatic
        @TypeConverter
        fun stringToDate(stringValue : String) : LocalDate {
            return LocalDate.parse(stringValue, DateTimeFormatter.ofPattern("dd LLLL yyyy"))
        }
    }
}