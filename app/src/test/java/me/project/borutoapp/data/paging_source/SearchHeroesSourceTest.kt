package me.project.borutoapp.data.paging_source

import androidx.paging.PagingSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import me.project.borutoapp.data.remote.FakeBorutoApi
import me.project.borutoapp.data.remote.HeroService
import me.project.borutoapp.domain.models.Hero
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

@ExperimentalCoroutinesApi
class SearchHeroesSourceTest {
    private lateinit var borutoApi: HeroService
    private lateinit var heroes: List<Hero>

    @Before
    fun setUp() {
        borutoApi = FakeBorutoApi()
        heroes = listOf(
            Hero(
                id = 1,
                name = "Sasuke",
                image = "/images/sasuke.jpg",
                about = "Sasuke Uchiha (うちはサスケ, Uchiha Sasuke) is one of the last surviving members of Konohagakure's Uchiha clan. After his older brother, Itachi, slaughtered their clan, Sasuke made it his mission in life to avenge them by killing Itachi. He is added to Team 7 upon becoming a ninja and, through competition with his rival and best friend, Naruto Uzumaki.",
                rating = 5.0,
                power = 98,
                month = "July",
                day = "23rd",
                family = listOf(
                ),
                abilities = listOf(

                ),
                natureTypes = listOf(

                )
            ), Hero(
                id = 2,
                name = "Naruto",
                image = "/images/naruto.jpg",
                about = "Naruto Uzumaki (うずまきナルト, Uzumaki Naruto) is a shinobi of Konohagakure's Uzumaki clan. He became the jinchūriki of the Nine-Tails on the day of his birth — a fate that caused him to be shunned by most of Konoha throughout his childhood. After joining Team Kakashi, Naruto worked hard to gain the village's acknowledgement all the while chasing his dream to become Hokage.",
                rating = 5.0,
                power = 98,
                month = "Oct",
                day = "10th",
                family = listOf(

                ),
                abilities = listOf(

                ),
                natureTypes = listOf(

                )
            ), Hero(
                id = 3,
                name = "Sakura",
                image = "/images/sakura.jpg",
                about = "Sakura Uchiha (うちはサクラ, Uchiha Sakura, née Haruno (春野)) is a kunoichi of Konohagakure. When assigned to Team 7, Sakura quickly finds herself ill-prepared for the duties of a shinobi. However, after training under the Sannin Tsunade, she overcomes this, and becomes recognised as one of the greatest medical-nin in the world.",
                rating = 4.5,
                power = 92,
                month = "Mar",
                day = "28th",
                family = listOf(

                ),
                abilities = listOf(

                ),
                natureTypes = listOf(

                )
            )

        )
    }

    @Test
    fun `Search api with existing hero name, expect single her result, assert LoadResult_Page`() =
        runTest {
            val heroSource = SearchHeroSource(service = borutoApi, query = "Sasu")
            assertEquals(
                PagingSource.LoadResult.Page(
                    data = listOf(heroes.first()),
                    prevKey = null,
                    nextKey = null
                ),
                heroSource.load(
                    PagingSource.LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            )
        }

    @Test
    fun `Search api with existing hero name, expect multiple hero result, assert LoadResult_Page`() =
        runTest {
            val heroSource = SearchHeroSource(service = borutoApi, query = "Sa")
            assertEquals(
                PagingSource.LoadResult.Page(
                    data = listOf(heroes.first(), heroes[2]),
                    prevKey = null,
                    nextKey = null
                ),
                heroSource.load(
                    PagingSource.LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            )
        }

    @Test
    fun `Search api with empty hero name, assert empty heroes list LoadResult_Page`() =
        runTest {
            val heroSource = SearchHeroSource(service = borutoApi, query = "")
            val loadResult = heroSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 3,
                    placeholdersEnabled = false
                )
            )

            val result = borutoApi.searchHeroes("").heroes
            assertTrue(result.isEmpty())
            assertTrue(loadResult is PagingSource.LoadResult.Page)
        }

    @Test
    fun `Search api with non-existing hero name, assert empty heroes list LoadResult_Page`() =
        runTest {
            val heroSource = SearchHeroSource(service = borutoApi, query = "unkonwn")
            val loadResult = heroSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 3,
                    placeholdersEnabled = false
                )
            )

            val result = borutoApi.searchHeroes("unk").heroes
            assertTrue(result.isEmpty())
            assertTrue(loadResult is PagingSource.LoadResult.Page)
        }
}