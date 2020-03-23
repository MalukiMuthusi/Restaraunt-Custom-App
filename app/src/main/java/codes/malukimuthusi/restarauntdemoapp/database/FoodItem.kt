package codes.malukimuthusi.restarauntdemoapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FoodItem(
    @PrimaryKey(autoGenerate = true)
    val foodId: Long = 0L,

    @ColumnInfo
    val foodImge: String,

    @ColumnInfo
    val foodTitle: String,

    @ColumnInfo()
    val foodDescription: String
) {
}