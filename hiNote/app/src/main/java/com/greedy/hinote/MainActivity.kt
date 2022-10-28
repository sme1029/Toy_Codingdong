package com.greedy.hinote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.greedy.hinote.databinding.ActivityMainBinding
import com.greedy.hinote.diary.DiaryActivity
class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnDiary.setOnClickListener {
            startActivity(Intent(this, DiaryActivity::class.java))
        }



    }


}