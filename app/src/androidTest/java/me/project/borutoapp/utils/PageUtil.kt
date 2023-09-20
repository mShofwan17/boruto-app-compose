package me.project.borutoapp.utils

import me.project.borutoapp.domain.models.Hero

object PageUtil {
    private var items : List<Hero>?= null
     private fun calculatePage(page: Int): Map<String, Int?> {
        var prev: Int? = page
        var next: Int? = page

         if (items?.isEmpty() == true){
             return mapOf("prev" to null, "next" to null)
         }

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

    fun setItems(items: List<Hero>?) {
         this.items = items
    }

    fun prevPage(page: Int) : Int? {
        return calculatePage(page)["prev"]
    }

    fun nextPage(page: Int) : Int? {
        return calculatePage(page)["next"]
    }
}