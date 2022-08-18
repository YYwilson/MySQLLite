package com.example.mysqllite

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class MainActivity : AppCompatActivity() {

            private val mTag = MainActivity::class.java.name
            val myList = ArrayList<PostalCode>()
            //val myList2 = ArrayList<CityDetail>()
            private var adapter : ArrayAdapter<String>?= null
            private var myAdapter : ItemAdapter ?= null
            private var context: Context?=null
            var db: SupplierDataDB?=null
            var supplierDataList = ArrayList<SupplierData>()
            companion object{
            @JvmStatic var myDetailList = ArrayList<CityDetail>()
        }

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_main)

                context = applicationContext

                val migration12 = object : Migration(1,2){
                    override fun migrate(database: SupportSQLiteDatabase) {
                        TODO("Not yet implemented")
                    }
                }

                //load db
                db = Room.databaseBuilder(context as Context,SupplierDataDB::class.java, SupplierDataDB.DATABASE_NAME)
                    .allowMainThreadQueries()
                    .addMigrations()
                    .build()

                db!!.supplierDataDao().clearTable()
                
                supplierDataList = db!!.supplierDataDao().getAll() as ArrayList<SupplierData> /* = java.util.ArrayList<com.example.mysqllite.SupplierData> */
                Log.e(mTag,"Supplierlist=${supplierDataList.size}")



                if(supplierDataList.size==0){
                    val supplierData = SupplierData("key1","Taipei","100")
                    db!!.supplierDataDao().insert(supplierData)
                    val supplierData2 = SupplierData("key2","Kaohsiung","700")
                    db!!.supplierDataDao().insert(supplierData2)
                }

                supplierDataList = db!!.supplierDataDao().getAll() as ArrayList<SupplierData> /* = java.util.ArrayList<com.example.mysqllite.SupplierData> */
                Log.e(mTag,"Supplierlist=${supplierDataList.size}")


                myList.clear()
                for (data in supplierDataList){
                    val item = PostalCode(data.getName(),data.getNumber(),200000,2000)
                    myList.add(item)
                }

                val listview = findViewById<ListView>(R.id.liseview1)
                val btnadd = findViewById<Button>(R.id.btnAdd)

                //val item1 = PostalCode("Taipei","100",  200000, 2000)
                //myList.add(item1)
                //val item2 = PostalCode("Taichung","400",  100000, 1000)
                //myList.add(item2)
                //val item3 = PostalCode("Tainan","700", 300000, 3000)
                //myList.add(item3)




                //adapter = ArrayAdapter( this, android.R.layout.simple_list_item_1, myList)
                myAdapter = ItemAdapter(this,R.layout.listview_item, myList)
                //listview.adapter = adapter
                listview.adapter = myAdapter

                listview.setOnItemClickListener { adapterView, view, i, l ->
                    Log.e(mTag,"click $i")

                    myDetailList.clear()

                    val detailItem1 = CityDetail("城市", myList[i].city)
                    myDetailList.add(detailItem1)
                    val detailItem2 = CityDetail("區號", myList[i].code)
                    myDetailList.add(detailItem2)
                    val detailItem3 = CityDetail("人口", myList[i].people.toString())
                    myDetailList.add(detailItem3)
                    val detailItem4 = CityDetail("面績", myList[i].area.toString())
                    myDetailList.add(detailItem4)

                    val intent = Intent(context, DetailActivity::class.java)
                    startActivity(intent)
                }


            }
}