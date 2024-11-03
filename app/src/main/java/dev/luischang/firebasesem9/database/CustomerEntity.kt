package dev.luischang.firebasesem9.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer")
data class CustomerEntity(
    @ColumnInfo("first_name") var firstName: String?,
    @ColumnInfo("last_name") var lastName: String?,
    @ColumnInfo("phone_number")var phoneNumber: String?
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("customer_id")var customerId: Int = 0
}
