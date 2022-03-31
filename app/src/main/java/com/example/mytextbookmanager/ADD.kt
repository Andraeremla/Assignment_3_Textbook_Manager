package com.example.mytextbookmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ADD : AppCompatActivity() {

    private var helper = DatabaseHandler(this)
    var isAdd: Boolean = false;
    var id: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val etBookName: EditText = findViewById(R.id.etBookName)
        val etBookAuthor: EditText = findViewById(R.id.etBookAuthor)
        val etCourse: EditText = findViewById(R.id.etCourse)
        val etISBN: EditText = findViewById(R.id.etISBN)
        val btnDelete: Button = findViewById(R.id.btnDelete)

        isAdd = intent.getBooleanExtra("ADD", true);
        id = intent.getIntExtra("ID", 0)

        if (!isAdd) {
            var book = helper.getParticularBookData("" + id)
            etBookName.setText(book.book)
            etBookAuthor.setText("" + book.author)
            etCourse.setText("" + book.course)
            etISBN.setText(book.isbn)
            btnDelete.visibility = View.VISIBLE
        }

        addData()
    }

    private fun validate(): Boolean {

        val etBookName: EditText = findViewById(R.id.etBookName)
        val etCourse: EditText = findViewById(R.id.etCourse)
        val etBookAuthor: EditText = findViewById(R.id.etBookAuthor)
        val etISBN: EditText = findViewById(R.id.etISBN)

        when {
            etBookName.text.isEmpty() -> {
                Toast.makeText(this@ADD, "Enter Name", Toast.LENGTH_SHORT).show()
                return false
            }
            etBookAuthor.text.isEmpty() -> {
                Toast.makeText(this@ADD, "Enter Age", Toast.LENGTH_SHORT).show()
                return false
            }
            etCourse.text.isEmpty() -> {
                Toast.makeText(this@ADD, "Enter Phone", Toast.LENGTH_SHORT).show()
                return false
            }
            etISBN.text.isEmpty() -> {
                Toast.makeText(this@ADD, "Enter Email", Toast.LENGTH_SHORT).show()
                return false
            }
            else -> return true
        }
    }

    private fun addData() {

        val etBookName: EditText = findViewById(R.id.etBookName)
        val etBookAuthor: EditText = findViewById(R.id.etBookAuthor)
        val etCourse: EditText = findViewById(R.id.etCourse)
        val etISBN: EditText = findViewById(R.id.etISBN)
        val btnAdd: Button = findViewById(R.id.btnAdd)
        val btnDelete: Button = findViewById(R.id.btnDelete)

        btnAdd.setOnClickListener {
            if (validate()) {
                if(isAdd) {
                    helper.insertData(etBookName.text.toString(),
                        etBookAuthor.text.toString(),
                        etCourse.text.toString(), etISBN.text.toString())
                }else{
                    helper.updateData(""+id,etBookName.text.toString(),
                        etBookAuthor.text.toString(),
                        etCourse.text.toString(), etISBN.text.toString())
                }
                clearAllFields()
                finish()
            }
        }
        btnDelete.setOnClickListener {
            helper.deleteData(""+id)
        }
    }

    private fun clearAllFields() {

        val etBookName: EditText = findViewById(R.id.etBookName)
        val etBookAuthor: EditText = findViewById(R.id.etBookAuthor)
        val etCourse: EditText = findViewById(R.id.etCourse)
        val etISBN: EditText = findViewById(R.id.etISBN)

        etBookName.text.clear()
        etBookAuthor.text.clear()
        etCourse.text.clear()
        etISBN.text.clear()
    }
}