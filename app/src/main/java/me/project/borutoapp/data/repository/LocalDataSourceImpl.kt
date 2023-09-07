package me.project.borutoapp.data.repository

import me.project.borutoapp.data.local.HeroDatabase
import me.project.borutoapp.domain.models.Hero
import me.project.borutoapp.domain.repository.LocalDataSource

class LocalDataSourceImpl(database: HeroDatabase) : LocalDataSource {

    private val heroDao = database.heroDao()
    override suspend fun getSelectedHero(id: Int): Hero {
        return heroDao.getSelectedHero(id)
    }
}