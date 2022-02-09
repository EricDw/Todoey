package dev.ericd.todoey.controllers

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import dev.ericd.todoey.R

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity_layout)

    }

}

