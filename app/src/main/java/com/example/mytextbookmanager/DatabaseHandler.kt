package com.example.mytextbookmanager

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    companion object {
        val DATABASE_NAME = "textbooks.db"
        val TABLE_NAME = "text_books_table"
        val ID = "ID"
        val Book = "Book"
        val Author = "Author"
        val Course = "Course"
        val Isbn = "Isbn"    }


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table $TABLE_NAME (ID INTEGER PRIMARY KEY AUTOINCREMENT,Book TEXT,Author TEXT,Course TEXT, Isbn TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    //Insert data into text_books_table
    fun insertData(book: String, author: String, course: String, isbn: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(Book, book)
        contentValues.put(Author, author)
        contentValues.put(Course, course)
        contentValues.put(Isbn,isbn)
        db.insert(TABLE_NAME, null, contentValues)
    }

    //List all books in text_books_table
    fun listOfBooks(): ArrayList<BookInfo>  {
        val db = this.readableDatabase
        val res = db.rawQuery("select * from " + TABLE_NAME, null)
        val bookList = ArrayList<BookInfo>()
        while (res.moveToNext()) {
            var bookInfo = BookInfo()
            bookInfo.id = Integer.valueOf(res.getString(0))
            bookInfo.book = res.getString(1)
            bookInfo.author = res.getString(2)
            bookInfo.course = res.getString(3)
            bookInfo.isbn = res.getString(4)
            bookList.add(bookInfo)
        }
        return bookList
    }


    //Getting all book list
    @SuppressLint("Range")
    fun getAllBookData(): ArrayList<BookInfo> {
        val stuList: ArrayList<BookInfo> = arrayListOf<BookInfo>()
        val cursor: Cursor = getReadableDatabase().query(TABLE_NAME, arrayOf(ID, Book, Author, Course, Isbn), null, null, null, null, null)
        cursor.use { cursor ->
            if (cursor.getCount() != 0) {
                cursor.moveToFirst()
                if (cursor.getCount() > 0) {
                    do {
                        val id : Int = cursor.getInt(cursor.getColumnIndex(ID))
                        val book: String = cursor.getString(cursor.getColumnIndex(Book))
                        val author: String = cursor.getString(cursor.getColumnIndex(Author))
                        val course: String = cursor.getString(cursor.getColumnIndex(Course))
                        val isbn: String =  cursor.getString(cursor.getColumnIndex(Isbn))
                        var bookInfo = BookInfo()
                        bookInfo.id = id
                        bookInfo.book = book
                        bookInfo.author = author
                        bookInfo.course = course
                        bookInfo.isbn = isbn
                        stuList.add(bookInfo)
                    } while ((cursor.moveToNext()))
                }
            }
        }

        return stuList
    }

    @SuppressLint("Range")
    fun getParticularBookData(id: String): BookInfo {
        var bookInfo  = BookInfo()
        val db = this.readableDatabase
        val selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + ID + " = '" + id + "'"
        val cursor = db.rawQuery(selectQuery, null)
        try {
            if (cursor.getCount() != 0) {
                cursor.moveToFirst();
                if (cursor.getCount() > 0) {
                    do {
                        bookInfo.id = cursor.getInt(cursor.getColumnIndex(ID))
                        bookInfo.book = cursor.getString(cursor.getColumnIndex(Book))
                        bookInfo.author = cursor.getString(cursor.getColumnIndex(Author))
                        bookInfo.course = cursor.getString(cursor.getColumnIndex(Course))
                        bookInfo.isbn = cursor.getString(cursor.getColumnIndex(Isbn))
                    } while ((cursor.moveToNext()));
                }
            }
        } finally {
            cursor.close();
        }
        return bookInfo
    }

    //Update book record
    fun updateData(id: String, book: String, author: String, course: String, isbn: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, id)
        contentValues.put(Book, book)
        contentValues.put(Author, author)
        contentValues.put(Course, course)
        contentValues.put(Isbn,isbn)
        db.update(TABLE_NAME, contentValues, "ID = ?", arrayOf(id))
        return true
    }

    //Delete table entry
    fun deleteData(id : String) : Int {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME,"ID = ?", arrayOf(id))

    }


}