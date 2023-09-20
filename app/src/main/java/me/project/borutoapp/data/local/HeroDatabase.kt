package me.project.borutoapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import me.project.borutoapp.data.local.dao.HeroDao
import me.project.borutoapp.data.local.dao.HeroRemoteKeyDao
import me.project.borutoapp.domain.models.Hero
import me.project.borutoapp.domain.models.HeroRemoteKey

@Database(
    entities = [
        Hero::class,
        HeroRemoteKey::class
    ], version = 1
)
@TypeConverters(DatabaseConverter::class)
abstract class HeroDatabase : RoomDatabase() {

    companion object {
        fun create(context: Context, useInMemory: Boolean): HeroDatabase {
            val dbBuilder =
                if (useInMemory) Room.inMemoryDatabaseBuilder(context, HeroDatabase::class.java)
                else Room.databaseBuilder(context, HeroDatabase::class.java, "test_database.db")
            return dbBuilder.fallbackToDestructiveMigration().build()
        }
    }

    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeyDao
}