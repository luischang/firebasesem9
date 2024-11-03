package dev.luischang.firebasesem9.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
@Dao
interface CustomerDao {
    @Insert
    fun insert(customerEntity: CustomerEntity)
    //Update
    @Update
    fun update(customerEntity: CustomerEntity)
    //Delete
    @Delete
    fun delete(customerEntity: CustomerEntity)

    @Query("SELECT * FROM customer")
    fun getAll(): LiveData<List<CustomerEntity>>

    @Query("SELECT * FROM customer WHERE customer_id = :customerId")
    fun getById(customerId: Int): CustomerEntity


}