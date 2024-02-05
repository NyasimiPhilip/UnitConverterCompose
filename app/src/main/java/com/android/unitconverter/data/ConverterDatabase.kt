// Define the package of the class
package com.android.unitconverter.data

// Import necessary Android and Room library classes
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Declare a RoomDatabase with an entity ConversionResult and version 1
@Database(entities = [ConversionResult::class], version = 1)
abstract class ConverterDatabase : RoomDatabase() {

    // Declare an abstract property representing the DAO (Data Access Object) for the database
    abstract val converterDAO: ConverterDAO

    // Companion object for providing a singleton instance of the database
    companion object {

        // Volatile ensures that INSTANCE is always up-to-date and visible to other threads
        @Volatile
        private var INSTANCE: ConverterDatabase? = null

        // Function to get a singleton instance of the database
        fun getInstance(context: Context): ConverterDatabase {
            synchronized(this) {
                // Retrieve the current instance of the database
                var instance = INSTANCE

                // If the database instance is null, create a new instance using Room's databaseBuilder
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ConverterDatabase::class.java,
                        "converter_data_database" // Name of the database file
                    ).build()

                    // Set the newly created instance to the INSTANCE variable
                    INSTANCE = instance
                }

                // Return the database instance
                return instance
            }
        }
    }
}
