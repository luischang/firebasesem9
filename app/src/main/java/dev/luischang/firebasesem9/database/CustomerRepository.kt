package dev.luischang.firebasesem9.database

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CustomerRepository(application: Application) {

    private val customerDao: CustomerDao ?=
        CustomerDatabase.getInstance(application)?.customerDao()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun insert(customerEntity: CustomerEntity){
        coroutineScope.launch {
            customerDao?.insert(customerEntity)
        }
    }

    fun getCustomers(): LiveData<List<CustomerEntity>> {
        return customerDao?.getAll() ?: MutableLiveData<List<CustomerEntity>>().apply {
        value = emptyList()
        }
    }




}