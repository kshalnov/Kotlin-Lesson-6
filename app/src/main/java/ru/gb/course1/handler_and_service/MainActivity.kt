package ru.gb.course1.handler_and_service

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.gb.course1.handler_and_service.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}