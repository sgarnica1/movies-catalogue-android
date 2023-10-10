package com.example.kotlin.tmbd.framework.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.tmbd.utils.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashscreenViewModel: ViewModel() {
    val finishedLoading = MutableLiveData<Boolean>()

    /**
     * @brief Este método se encarga de simular el tiempo de carga de la aplicación
     * @details Este método se encarga de simular el tiempo de carga de la aplicación
     * @param
     * @return
     */
    fun onCreate() {
        finishedLoading.postValue(false)
        viewModelScope.launch {
            delay(Constants.SPLASHCREEN_DURATION)
            finishedLoading.postValue(true)
        }
    }

}