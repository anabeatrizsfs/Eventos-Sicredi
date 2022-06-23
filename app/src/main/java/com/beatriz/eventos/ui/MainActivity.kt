package com.beatriz.eventos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beatriz.eventos.R
import com.beatriz.eventos.utils.ResourceStatus
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewmodel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(viewmodel) {
            getAllEvents().observe(this@MainActivity) {
                when (it.status) {
                    ResourceStatus.SUCCESS -> {
                        Log.d("Event", "Successo! ${it.data?.forEach(System.out::print)}}")
                    }
                    ResourceStatus.LOADING -> {
                        Log.d("Event", "Carregando!")
                    }
                    ResourceStatus.ERROR -> {
                        Log.d("Event", "Erro! ${it.message}")
                    }
                }
            }
        }
    }
}