package com.example.mysqllite

import androidx.room.*

@Dao
interface SupplierDataDao {

    @Query("SELECT * FROM " + SupplierData.TABLE_NAME)
    fun getAll(): List<SupplierData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(SupplierData: SupplierData)

    @Update
    fun update(supplierData: SupplierData)

    @Query("DELETE FROM " + SupplierData.TABLE_NAME +" WHERE 1")
    fun clearTable()

}