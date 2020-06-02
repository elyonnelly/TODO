package com.example.todo.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todo.R

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listFragment = ListFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, listFragment)

        transaction.commit()
    }
}
