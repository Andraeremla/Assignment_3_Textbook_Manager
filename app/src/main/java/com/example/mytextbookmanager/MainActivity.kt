package com.example.mytextbookmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    internal var helper = DatabaseHandler(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnAdd: Button = findViewById(R.id.btnAdd)
        btnAdd.setOnClickListener {
            var intent = Intent(this, ADD::class.java)
            intent.putExtra("ADD", true)
            startActivity(intent)
        }

        var btnView: Button = findViewById(R.id.btnView)
        btnView.setOnClickListener {
            var intent = Intent(this, BookListing::class.java)
            startActivity(intent)
        }
    }
}