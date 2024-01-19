package com.example.sequenceexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sequenceexample.databinding.ActivityMainBinding
import java.util.stream.Collectors
import kotlin.system.measureTimeMillis

private const val TAG = "topkek"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val exampleList = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sequence.setOnClickListener {
            startExample(Example.SEQUENCE)
        }

        binding.stream.setOnClickListener {
            startExample(Example.STREAM)
        }

        binding.collections.setOnClickListener {
            startExample(Example.COLLECTIONS)
        }
    }

    private fun startExample(example: Example) {
        fillList()
        var time = 0L
        Log.d(
            TAG,
            "example: ${example}, elements: ${getChosenRadioButton()}"
        )
        when (example) {

            Example.SEQUENCE -> {
                time = measureTimeMillis {
                    val result =
                        exampleList.asSequence().map { it * 2 }.map { it - 1 }
                            .filter { it % 2 == 0 }.map { it.toString() }
                            .toList()
                    println(result)
                }

            }

            Example.STREAM -> {
                time = measureTimeMillis {
                    val result = exampleList.stream().sorted().map { it * 2 }.map { it - 1 }
                        .filter { it % 2 == 0 }.map { it.toString() }
                        .collect(Collectors.toList())
                    println(result)
                }

            }

            Example.COLLECTIONS -> {
                time = measureTimeMillis {
                    val result =
                        exampleList.sorted().map { it * 2 }.map { it - 1 }.filter { it % 2 == 0 }
                            .map { it.toString() }
                    println(result)
                }
            }
        }
        Log.d(TAG, "time in millis: $time")
        exampleList.clear()
    }

    private fun fillList() {
        when (binding.group.checkedRadioButtonId) {
            binding.ten.id -> {
                repeat(10) {
                    exampleList.add(it)
                }
            }

            binding.hundred.id -> {
                repeat(100) {
                    exampleList.add(it)
                }
            }

            binding.thousand.id -> {
                repeat(1000) {
                    exampleList.add(it)
                }
            }

            binding.tenThousand.id -> {
                repeat(10000) {
                    exampleList.add(it)
                }
            }

            binding.oneHundredThousand.id -> {
                repeat(100000) {
                    exampleList.add(it)
                }
            }

            binding.oneMillion.id -> {
                repeat(1000000) {
                    exampleList.add(it)
                }
            }
        }
        exampleList.shuffle()
    }

    private fun getChosenRadioButton(): Int {
        when (binding.group.checkedRadioButtonId) {
            binding.ten.id -> {
                return 10
            }

            binding.hundred.id -> {
                return 100
            }

            binding.thousand.id -> {
                return 1000
            }

            binding.tenThousand.id -> {
                return 10000
            }

            binding.oneHundredThousand.id -> {
                return 100000
            }

            binding.oneMillion.id -> {
                return 1000000
            }

            else -> {
                return 0
            }
        }
    }

    enum class Example {
        SEQUENCE, STREAM, COLLECTIONS
    }
}