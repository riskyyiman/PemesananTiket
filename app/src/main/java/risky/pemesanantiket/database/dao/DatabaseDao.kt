package risky.pemesanantiket.database.dao

import androidx.room.Dao
import androidx.lifecycle.LiveData
import androidx.room.Insert
import risky.pemesanantiket.model.ModelDatabase
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface DatabaseDao {
    @Query("SELECT * FROM tbl_travel")
    fun getAllData(): LiveData<List<ModelDatabase>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(vararg modelDatabases: ModelDatabase)

    @Query("DELETE FROM tbl_travel WHERE uid= :uid")
    fun deleteDataById(uid: Int)
}