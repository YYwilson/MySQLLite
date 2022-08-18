package com.example.mysqllite

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.mysqllite.MainActivity.Companion.myDetailList

class DetailActivity : AppCompatActivity() {
    private val mTag = DetailActivity::class.java.name
    private  var cityDetailAdapter: CityDetailAdapter?= null
    private  var listViewDetail: ListView?= null
    private  var context: Context?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        context=applicationContext

        listViewDetail = findViewById(R.id.liseviewDetail)

        cityDetailAdapter = CityDetailAdapter(context, R.layout.listview_item, myDetailList)
        listViewDetail!!.adapter = cityDetailAdapter


    }

    override fun onDestroy() {
        Log.e(mTag, "onDestroy")
        super.onDestroy()

    }
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}