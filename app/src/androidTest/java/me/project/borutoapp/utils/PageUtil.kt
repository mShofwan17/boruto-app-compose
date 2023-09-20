package me.project.borutoapp.utils

object PageUtil {
     private fun calculatePage(page: Int): Map<String, Int?> {
        var prev: Int? = page
        var next: Int? = page

        if (page in 1..4) {
            next = next?.plus(1)
        }

        if (page in 2..5) {
            prev = prev?.minus(1)
        }
        if (page == 1) prev = null
        if (page == 5) next = null

        return mapOf("prev" to prev, "next" to next)
    }

    fun prevPage(page: Int) : Int? {
        return calculatePage(page)["prev"]
    }

    fun nextPage(page: Int) : Int? {
        return calculatePage(page)["next"]
    }
}