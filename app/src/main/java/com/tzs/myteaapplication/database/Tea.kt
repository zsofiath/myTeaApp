package com.tzs.myteaapplication.database
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tea_table")
data class Tea (

    @PrimaryKey(autoGenerate = true)
    var teaId: Int = 0,

    @ColumnInfo(name = "tea_name")
    var name: String = "",

    @ColumnInfo(name = "tea_temperature")
    var temperature: Int = 0,

    @ColumnInfo(name = "tea_amount_of_leaf")
    var amount: Int = 0


)