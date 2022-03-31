package com.example.mytextbookmanager


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ULAdapter(context: Context, arrayOfData: ArrayList<BookInfo>)  : BaseAdapter() {
    var arrayOfData : ArrayList<BookInfo>
    private val mInflator: LayoutInflater

    init {
        this.arrayOfData = arrayOfData
        this.mInflator = LayoutInflater.from(context)
    }

    override fun getItem(position: Int): Any {
        return arrayOfData[position];
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return arrayOfData.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

        val view: View?
        val vh: ListRowHolder
        if (convertView == null) {
            view = this.mInflator.inflate(R.layout.row_item, parent, false)
            vh = ListRowHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ListRowHolder
        }
        vh.etBookName.text = arrayOfData[position].book
        vh.etBookAuthor.text = ""+arrayOfData[position].author
        return view
    }

    private class ListRowHolder(row: View?) {
        public val etBookName: TextView
        public val etBookAuthor : TextView

        init {
            this.etBookName = row?.findViewById(R.id.etBookName) as TextView
            this.etBookAuthor = row?.findViewById(R.id.etBookAuthor) as TextView
        }
    }
}