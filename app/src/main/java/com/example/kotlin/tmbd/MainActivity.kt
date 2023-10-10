package com.example.kotlin.tmbd

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.tmbd.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : Activity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter : MovieAdapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeBinding()
        setUpRecyclerView(testData())
        getPopularMoviesList()
    }

    private fun initializeBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun testData(): ArrayList<MovieBase> {
        val result = ArrayList<MovieBase>()

        result.add(
            MovieBase(
                false,
                "/6MKr3KgOLmzOP6MSuZERO41Lpkt.jpg",
                arrayListOf(28, 12, 14, 878),
                460465,
                "en",
                "Mortal Kombat?",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                4512.0,
                "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                "2021-04-07",
                "Mortal Kombat",
                false,
                7.8,
                1198
            )
        )
        result.add(
            MovieBase(
                false,
                "/6MKr3KgOLmzOP6MSuZERO41Lpkt.jpg",
                arrayListOf(28, 12, 14, 878),
                460465,
                "en",
                "Mortal Kombat 2",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                4512.0,
                "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                "2021-04-07",
                "Mortal Kombat 2",
                false,
                7.8,
                1198
            )
        )
        result.add(
            MovieBase(
                false,
                "/6MKr3KgOLmzOP6MSuZERO41Lpkt.jpg",
                arrayListOf(28, 12, 14, 878),
                460465,
                "en",
                "Mortal Kombat 3",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                4512.0,
                "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                "2021-04-07",
                "Mortal Kombat 3",
                false,
                7.8,
                1198
            )
        )
        return result
    }

    private fun getPopularMoviesList() {
        CoroutineScope(Dispatchers.IO).launch {
            val repository = MovieRepository()
            val result: MovieObject? =
                repository.getPopularMovies(1)
            Log.d("Salida", result?.count.toString())
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
        adapter.MovieAdapter(dataForList)
        binding.MoviesList.adapter = adapter
    }


}