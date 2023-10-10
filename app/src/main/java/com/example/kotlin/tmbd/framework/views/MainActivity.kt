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

    /**
     * @brief Inicializa el binding
     */
    private fun initializeBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    /**
     * @brief Inicializa el SearchView
     * @details Se inicializa el SearchView y se le asigna un listener para que se actualice la lista
     * de películas cada vez que se realice una búsqueda
     * @see filterMovies
     * @see adapter
     * @see originalMoviesList
     */
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


    /**
     * @brief Filtra las películas que coincidan con el query
     * @param query El query a buscar
     * @return Una lista de películas que coincidan con el query
     * @see adapter
     */
    private fun filterMovies(query: String?): List<MovieBase> {
        return if (query.isNullOrEmpty()) {
            originalMoviesList
        } else {
            adapter.data.filter { movie ->
                movie.title.contains(query, ignoreCase = true)
            }
        }
    }


    /**
     * @brief Obtiene la lista de películas populares
     * @details Se obtiene la lista de películas populares y se actualiza el RecyclerView
     */
    private fun getPopularMoviesList() {
        CoroutineScope(Dispatchers.IO).launch {
            val repository = MovieRepository()
            val result: MovieObject? =
                repository.getPopularMovies(1)
            if(result == null){
                showErrorView()
                return@launch
            }

            originalMoviesList.clear()
            originalMoviesList.addAll(result.results)

            CoroutineScope(Dispatchers.Main).launch{
                setUpRecyclerView(result?.results!!)
            }
        }
    }

    /**
     * @brief Actualiza el RecyclerView
     * @param dataForList La lista de películas a mostrar
     * @see adapter
     */
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