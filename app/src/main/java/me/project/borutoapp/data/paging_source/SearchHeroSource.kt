package me.project.borutoapp.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import me.project.borutoapp.data.remote.HeroService
import me.project.borutoapp.domain.models.Hero
import javax.inject.Inject

class SearchHeroSource @Inject constructor(
    private val service: HeroService,
    private val query: String
) : PagingSource<Int, Hero>() {
    override fun getRefreshKey(state: PagingState<Int, Hero>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hero> {
        return try {
            val response = service.searchHeroes(name = query)
            val heroes = response.heroes
            if (heroes.isNotEmpty()) {
                LoadResult.Page(
                    data = heroes,
                    prevKey = response.prevPage,
                    nextKey = response.nextPage
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}