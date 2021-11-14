package ru.gb.course1.handler_and_service

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import androidx.appcompat.app.AppCompatActivity
import ru.gb.course1.handler_and_service.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val uiHandler: Handler by lazy { Handler(mainLooper) }
    private val workerHandler: Handler by lazy { Handler(handlerThread.looper) }

    private val handlerThread: HandlerThread = HandlerThread("math_worker").apply {
        start()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goButton.setOnClickListener {
            val input = binding.inputEditText.text.toString().toInt()
            binding.inputEditText.text.clear()

            workerHandler.post {
                val res = input * input
                val sleepSec = Random.nextInt() % 2 + 1
                Thread.sleep(sleepSec * 1_000L)
                uiHandler.post {
                    binding.resultTextView.text = "${binding.resultTextView.text}\n$res"
                }
            }
        }
    }

    override fun onDestroy() {
        handlerThread.quit()
        super.onDestroy()
    }

}