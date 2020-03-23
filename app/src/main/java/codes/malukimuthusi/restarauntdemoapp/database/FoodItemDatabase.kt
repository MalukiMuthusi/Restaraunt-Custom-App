package codes.malukimuthusi.restarauntdemoapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [FoodItem::class], version = 1, exportSchema = false)
abstract class FoodItemDatabase : RoomDatabase() {

    abstract val foodItemDatabaseDao: FoodItemDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: FoodItemDatabase? = null

        fun getInstance(contect: Context): FoodItemDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        contect.applicationContext,
                        FoodItemDatabase::class.java,
                        "food_item_db"

                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }

        }
    }
}