package com.greedy.hinote.diary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.greedy.hinote.databinding.ActivityDiaryBinding

class DiaryActivity : AppCompatActivity() {

    val binding by lazy { ActivityDiaryBinding.inflate(layoutInflater) }
    val helper = SqliteHelper(this, "diary", 1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = DiaryAdapter()
        adapter.listData.addAll(helper.selectDiary())

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.registButton3.setOnClickListener {
            startActivity(Intent(this, DiaryRegistActivity::class.java))
        }

    }
}