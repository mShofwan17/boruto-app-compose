package me.project.borutoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import me.project.borutoapp.data.local.dao.HeroDao
import me.project.borutoapp.data.local.dao.HeroRemoteKeyDao
import me.project.borutoapp.domain.models.Hero
import me.project.borutoapp.domain.models.HeroRemoteKey

@Database(entities = [Hero::class, HeroRemoteKey::class], version = 1)
abstract class HeroDatabase : RoomDatabase() {
    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeyDao
}