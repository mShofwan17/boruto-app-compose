package me.project.borutoapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.project.borutoapp.domain.models.Hero
import me.project.borutoapp.utils.Constant.DatabaseConst.TB_HERO

@Dao
interface HeroDao {

    @Query("SELECT * FROM $TB_HERO ORDER BY id ASC ")
    fun getAllHeroes(): PagingSource<Int, Hero>

    @Query("SELECT * FROM $TB_HERO WHERE id=:heroId")
    fun getSelectedHero(heroId: Int): Hero

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHero(heroes: List<Hero>)

    @Query("DELETE FROM $TB_HERO")
    suspend fun deleteAllHeroes()
}