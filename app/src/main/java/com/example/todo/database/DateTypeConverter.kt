package com.example.todo.database

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

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
        @JvmStatic
        @TypeConverter
        fun timeToString(time : LocalTime) : String {
            return time.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))
        }
        @JvmStatic
        @TypeConverter
        fun stringToTime(stringValue : String) : LocalTime {
            return LocalTime.parse(stringValue,  DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))
        }
    }
}