package codes.malukimuthusi.restarauntdemoapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FoodItemDatabaseDao {

    // Insert New food item.
    @Insert
    fun insert(foodItem: FoodItem)


    // update existing food Item.
    @Update
    fun update(foodItem: FoodItem)

    // Delete food Item.
    @Delete
    fun delete(foodItem: FoodItem)

    // get list of all food Items.
    //returns a LiveData object.
    @Query("SELECT * FROM FoodItem ORDER BY foodId DESC")
    fun listOfFoodItems(): LiveData<List<FoodItem>>

    // return a single food Item
    @Query("SELECT * FROM FoodItem ORDER BY foodId LIMIT 1")
    fun getFoodItem(): FoodItem?

    // get this food Item
    @Query("SELECT * FROM FoodItem WHERE foodId = :id")
    fun getFoodItemWithID(id: Long = 1L): LiveData<FoodItem>

}