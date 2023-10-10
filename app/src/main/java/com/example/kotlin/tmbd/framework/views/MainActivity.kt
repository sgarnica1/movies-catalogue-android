package com.example.kotlin.tmbd.framework.views

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.tmbd.R
import com.example.kotlin.tmbd.data.repository.MovieRepository
import com.example.kotlin.tmbd.databinding.ActivityMainBinding
import com.example.kotlin.tmbd.domain.model.MovieBase
import com.example.kotlin.tmbd.domain.model.MovieObject
import com.example.kotlin.tmbd.framework.ui.adapters.MovieAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : Activity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter: MovieAdapter = MovieAdapter()
    private lateinit var searchView: SearchView
    private val originalMoviesList: ArrayList<MovieBase> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeBinding()
        getPopularMoviesList()
        initializeSearchView()
    }

    private fun initializeBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initializeSearchView() {
        searchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val filteredMovies = filterMovies(query)
                adapter.updateData(filteredMovies)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredMovies = filterMovies(newText)
                adapter.updateData(filteredMovies)
                return true
            }
        })
    }


    private fun filterMovies(query: String?): List<MovieBase> {
        return if (query.isNullOrEmpty()) {
            // Si el query está vacío o nulo, retorna la lista original
            originalMoviesList
        } else {
            // Filtra las películas que coincidan con la búsqueda
            adapter.data.filter { movie ->
                movie.title.contains(query, ignoreCase = true)
            }
        }
    }




    private fun getPopularMoviesList() {
        CoroutineScope(Dispatchers.IO).launch {
            val repository = MovieRepository()
            val result: MovieObject? =
                repository.getPopularMovies(1)
            Log.d("Salida", result.toString())
            Log.d("Salida", result?.count.toString())

            if(result == null){
                showErrorView()
                return@launch
            }

            // Copiar los datos a la lista original
            originalMoviesList.clear()
            originalMoviesList.addAll(result.results)

            CoroutineScope(Dispatchers.Main).launch{
                setUpRecyclerView(result?.results!!)
            }
        }
    }

    private fun setUpRecyclerView(dataForList: ArrayList<MovieBase>) {
        binding.MoviesList.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.MoviesList.layoutManager = linearLayoutManager
        adapter.MovieAdapter(dataForList, this)
        binding.MoviesList.adapter = adapter
    }

    private fun showErrorView() {
        val intent: Intent = Intent(this, ErrorActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

}