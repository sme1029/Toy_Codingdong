package com.greedy.hinote.diary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.greedy.hinote.BookActivity
import com.greedy.hinote.MainActivity
import com.greedy.hinote.databinding.ActivityDiaryBinding
import com.greedy.hinote.weather.WeatherActivity

class DiaryActivity : AppCompatActivity() {

    val binding by lazy { ActivityDiaryBinding.inflate(layoutInflater) }
    val helper = SqliteHelper(this, "diary", 1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /* 홈 이동 */
        binding.home.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        val adapter = DiaryAdapter()
        adapter.listData.addAll(helper.selectDiary())

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.registButton.setOnClickListener {
            startActivity(Intent(this, DiaryRegistActivity::class.java))
        }

        binding.btnWeather.setOnClickListener {
            startActivity(Intent(this, WeatherActivity::class.java))
        }

    }
}