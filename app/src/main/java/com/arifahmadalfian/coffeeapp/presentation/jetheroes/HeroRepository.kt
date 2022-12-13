package com.arifahmadalfian.coffeeapp.presentation.jetheroes

class HeroRepository {
    fun getHeroes(): List<Hero> {
        return HeroesData.heroes
    }
}