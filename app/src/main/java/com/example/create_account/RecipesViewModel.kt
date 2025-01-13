package com.example.create_account

import androidx.lifecycle.ViewModel
import com.example.accountcreate.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RecipesViewModel : ViewModel() {

    private val recipes = listOf(
        ListItem(1, R.drawable.item1, "Black Karaage with Curry Bento"),
        ListItem(2, R.drawable.item2, "Seafood Udon"),
        ListItem(3, R.drawable.item3, "Tonkotsu Ramen")
    )

    private val _filteredRecipes = MutableStateFlow(recipes)
    val filteredRecipes: StateFlow<List<ListItem>> = _filteredRecipes.asStateFlow()

    fun searchRecipes(query: String) {
        if (query.length < 3) {
            _filteredRecipes.update { recipes }
        } else {
            val filtered = recipes.filter { recipe ->
                recipe.title.contains(query, ignoreCase = true)
            }
            _filteredRecipes.update { filtered }
        }
    }
}
