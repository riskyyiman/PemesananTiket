package risky.pemesanantiket.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import risky.pemesanantiket.model.ModelDatabase
import risky.pemesanantiket.database.dao.DatabaseDao

/**
 * Created by Azhar Rivaldi on 19-11-2021
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 */

@Database(entities = [ModelDatabase::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun databaseDao(): DatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "pemesanan_tiket_db"
                )
                    .fallbackToDestructiveMigration() // ini akan hapus DB lama jika schema berubah
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
