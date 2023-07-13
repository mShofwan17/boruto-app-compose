package me.project.borutoapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.project.borutoapp.data.repository.DataStoreOperationImpl
import me.project.borutoapp.domain.repository.DataStoreOperations
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(
        @ApplicationContext context: Context
    ) : DataStoreOperations{
        return DataStoreOperationImpl(
            context = context
        )
    }

}