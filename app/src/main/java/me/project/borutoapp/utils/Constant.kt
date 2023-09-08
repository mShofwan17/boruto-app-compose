package me.project.borutoapp.utils

object Constant {
    const val ID = "id"

    object NetworkConst{
        const val BASE_URL = "http://10.0.2.2:8080"
        const val JSON_CONTENT_TYPE = "application/json"
    }

    object DatabaseConst{
        const val DB_HERO = "db_hero"
        const val TB_HERO = "tb_hero"
        const val TB_HERO_REMOTE_KEY = "tb_hero_remote_key"
    }

    object OnBoardingConst {
        const val ON_BOARDING_FINISH = 2
    }

    object DataStore{
        const val PREFERENCE_NAME = "HERO_PREF"
        const val ON_BOARDING_COMPLETE = "ON_BOARDING_COMPLETE"
    }

    object Common{
        const val ABOUT_TEXT_MAX_LINES = 7
    }
}