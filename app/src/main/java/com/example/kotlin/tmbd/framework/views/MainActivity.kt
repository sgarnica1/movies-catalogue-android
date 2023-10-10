package com.example.kotlin.tmbd.framework.views

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeBinding()
        // setUpRecyclerView(testData())
        getPopularMoviesList()
    }

    private fun initializeBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun testData(): ArrayList<MovieBase> {
        val result = ArrayList<MovieBase>()

        // Add the first movie
        result.add(
            MovieBase(
                false,
                "/mRGmNnh6pBAGGp6fMBMwI8iTBUO.jpg",
                arrayListOf(27, 9648, 53),
                968051,
                "en",
                "The Nun II",
                "In 1956 France, a priest is violently murdered, and Sister Irene begins to investigate. She once again comes face-to-face with a powerful evil.",
                4160.929,
                "/5gzzkR7y3hnY8AD1wXjCnVlHba5.jpg",
                "2023-09-06",
                "The Nun II",
                false,
                7.0,
                776
            )
        )

        // Add the second movie
        result.add(
            MovieBase(
                false,
                "/cHNqobjzfLj88lpIYqkZpecwQEC.jpg",
                arrayListOf(28, 53, 80),
                926393,
                "en",
                "The Equalizer 3",
                "Robert McCall finds himself at home in Southern Italy but he discovers his friends are under the control of local crime bosses. As events turn deadly, McCall knows what he has to do: become his friends' protector by taking on the mafia.",
                3761.779,
                "/b0Ej6fnXAP8fK75hlyi2jKqdhHz.jpg",
                "2023-08-30",
                "The Equalizer 3",
                false,
                7.4,
                612
            )
        )

        // Add the third movie
        result.add(
            MovieBase(
                false,
                "/pA3vdhadJPxF5GA1uo8OPTiNQDT.jpg",
                arrayListOf(28, 18),
                678512,
                "en",
                "Sound of Freedom",
                "The story of Tim Ballard, a former US government agent, who quits his job in order to devote his life to rescuing children from global sex traffickers.",
                2724.044,
                "/qA5kPYZA7FkVvqcEfJRoOy4kpHg.jpg",
                "2023-07-03",
                "Sound of Freedom",
                false,
                8.1,
                754
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
            Log.d("Salida", result.toString())
            Log.d("Salida", result?.count.toString())

            if(result == null){
                showErrorView()
                return@launch
            }
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