package me.project.borutoapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.project.borutoapp.domain.models.HeroRemoteKey
import me.project.borutoapp.utils.Constant.DatabaseConst.TB_HERO_REMOTE_KEY

@Dao
interface HeroRemoteKeyDao {

    @Query("SELECT * FROM $TB_HERO_REMOTE_KEY WHERE id = :id")
    suspend fun getRemoteKey(id: Int): HeroRemoteKey?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(items : List<HeroRemoteKey>)

    @Query("DELETE FROM $TB_HERO_REMOTE_KEY")
    suspend fun deleteAllRemoteKey()
}