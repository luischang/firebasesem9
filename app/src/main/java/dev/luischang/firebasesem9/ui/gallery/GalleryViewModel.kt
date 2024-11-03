package dev.luischang.firebasesem9.ui.gallery

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.luischang.firebasesem9.database.CustomerEntity
import dev.luischang.firebasesem9.database.CustomerRepository

class GalleryViewModel (application: Application) : AndroidViewModel(application) {

    private var repository = CustomerRepository(application)
    val customers = repository.getCustomers()

    fun saveCustomer(customerEntity: CustomerEntity){
        repository.insert(customerEntity)
    }

}