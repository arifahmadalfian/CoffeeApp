package com.arifahmadalfian.coffeeapp.presentation.jetheroes

class HeroRepository {
    fun getHeroes(): List<Hero> {
        return HeroesData.heroes
    }

    fun searchHeroes(query: String): List<Hero>{
        return HeroesData.heroes.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }
}