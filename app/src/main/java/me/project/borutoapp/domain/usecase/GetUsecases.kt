package me.project.borutoapp.domain.usecase

data class GetUsecases(
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val getAllHeroesUseCase: GetAllHeroesUseCase,
    val searchHeroesUseCase: SearchHeroesUseCase,
    val getSelectedHeroUseCase: GetSelectedHeroUseCase
)
