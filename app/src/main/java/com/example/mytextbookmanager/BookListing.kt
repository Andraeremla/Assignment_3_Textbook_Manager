package com.example.mytextbookmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class BookListing : AppCompatActivity() {
    private var helper = DatabaseHandler(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_listing)


        var listData: ArrayList<BookInfo> = helper.listOfBooks()

        var listView: ListView = findViewById(R.id.lvBooks)
        var listAdapter = ULAdapter(this, listData)
        listView.adapter = listAdapter

        listView.setOnItemClickListener { _, _, position, _ ->
            var intent = Intent(this, ADD::class.java)
            intent.putExtra("ADD", false)
            intent.putExtra("ID",listData[position].id)
            startActivity(intent)
        }
    }
}