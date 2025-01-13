package com.example.create_account

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.accountcreate.R

class MainActivity : AppCompatActivity() {

    private val viewModel: RecipesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val searchView = findViewById<androidx.appcompat.widget.SearchView>(R.id.searchView)

        val adapter = ListAdapter(
            listOf(),
            itemClickListener = { recipe ->
                Toast.makeText(this, "Clicked ID: ${recipe.id}", Toast.LENGTH_SHORT).show()
            },
            actionClickListener = { recipe, action ->
                Toast.makeText(this, "$action clicked, ID: ${recipe.id}", Toast.LENGTH_SHORT).show()
            }
        )
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        lifecycleScope.launchWhenStarted {
            viewModel.filteredRecipes.collect { recipes ->
                adapter.updateList(recipes)
            }
        }

        searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchRecipes(newText.orEmpty())
                return true
            }
        })
    }
}
