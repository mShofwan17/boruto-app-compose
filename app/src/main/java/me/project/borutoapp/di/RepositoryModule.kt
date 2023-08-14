package me.project.borutoapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.project.borutoapp.data.repository.DataStoreOperationImpl
import me.project.borutoapp.data.repository.Repository
import me.project.borutoapp.domain.repository.DataStoreOperations
import me.project.borutoapp.domain.usecase.GetAllHeroesUseCase
import me.project.borutoapp.domain.usecase.GetUsecases
import me.project.borutoapp.domain.usecase.ReadOnBoardingUseCase
import me.project.borutoapp.domain.usecase.SaveOnBoardingUseCase
import me.project.borutoapp.domain.usecase.SearchHeroesUseCase
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

    @Provides
    @Singleton
    fun provideUseCases(
        repository: Repository
    ) : GetUsecases {
        return GetUsecases(
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository),
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository),
            getAllHeroesUseCase = GetAllHeroesUseCase(repository),
            searchHeroesUseCase = SearchHeroesUseCase((repository))
        )
    }

}