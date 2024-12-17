package com.example.mywishlistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0L,
    @ColumnInfo(name = "wish-title")
    val title : String = "",
    @ColumnInfo(name = "wish-desc")
    val description: String = ""
)

object DummyWish{
    var wishlist = listOf(
        Wish(title = "phone", description = "Samsung galaxy s23 fe maybe"),
        Wish(title = "manali trip", description = "shayad nhi ho payega is baar"),
        Wish(title = "kapde", description = "bohot saare kapde"),

    )

}