package com.example.todo.view

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.example.todo.R
import kotlinx.android.synthetic.main.layout_with_toolbar.*

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            val listFragment = ListFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.container, listFragment)

            transaction.commit()
        }
        setSupportActionBar(toolbar_actionbar);
    }

}
