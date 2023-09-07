package me.project.borutoapp.domain.repository

import me.project.borutoapp.domain.models.Hero

interface LocalDataSource {
    suspend fun getSelectedHero(id: Int): Hero
}