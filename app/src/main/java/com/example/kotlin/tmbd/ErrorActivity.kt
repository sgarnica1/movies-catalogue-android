package com.example.kotlin.tmbd

import android.app.Activity
import android.os.Bundle
import com.example.kotlin.tmbd.databinding.ActivityErrorBinding

class ErrorActivity: Activity() {
    private lateinit var binding: ActivityErrorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeBinding()
    }

    private fun initializeBinding() {
        binding = ActivityErrorBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}