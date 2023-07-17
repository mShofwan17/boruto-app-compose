package me.project.borutoapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.project.borutoapp.data.local.HeroDatabase
import me.project.borutoapp.utils.Constant
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) : HeroDatabase = Room.databaseBuilder(
        context = context,
        HeroDatabase::class.java,
        Constant.DatabaseConst.DB_HERO
    ).build()
}