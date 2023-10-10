package com.example.kotlin.tmbd.framework.views

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.kotlin.tmbd.databinding.ActivitySplashscreenBinding
import com.example.kotlin.tmbd.framework.viewmodels.SplashscreenViewModel

class SplashscreenActivity: AppCompatActivity() {
    private lateinit var binding: ActivitySplashscreenBinding
    private val viewModel: SplashscreenViewModel by viewModels()

    override fun onCreate(savedInstances: Bundle?) {
        super.onCreate(savedInstances)
        initializeBinding()
        viewModel.onCreate()
        initializeObservers()
    }

    private fun initializeObservers() {
        viewModel.finishedLoading.observe(this, Observer {finishedLoading ->
            if(finishedLoading) {
                passViewGoToMain()
            }
        })
    }

    private fun initializeBinding() {
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * @brief Pasa a la vista de MainActivity
     * @details Se crea un intent para pasar a la vista de MainActivity y se finaliza la actividad
     * actual
     */
    private fun passViewGoToMain() {
        var intent: Intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
        finish()
    }
}