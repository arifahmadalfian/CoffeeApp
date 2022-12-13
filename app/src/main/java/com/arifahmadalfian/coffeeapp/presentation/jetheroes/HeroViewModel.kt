package com.arifahmadalfian.coffeeapp.presentation.jetheroes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow

class JetHeroesViewModel(
    private val repository: HeroRepository
): ViewModel() {

    private val _groupHeroes = MutableStateFlow(
        repository.getHeroes()
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    )
    val groupHeroes get() = _groupHeroes

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
        _groupHeroes.value = repository.searchHeroes(_query.value)
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    }

}

class ViewModelFactory(private val repository: HeroRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JetHeroesViewModel::class.java)) {
            return JetHeroesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}