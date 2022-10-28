package com.greedy.hinote.diary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.greedy.hinote.databinding.ActivityDiaryRegistBinding

class DiaryRegistActivity : AppCompatActivity() {

    val binding by lazy { ActivityDiaryRegistBinding.inflate(layoutInflater) }
    val helper = SqliteHelper(this, "diary", 1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val adapter = DiaryAdapter()
        binding.saveButton.setOnClickListener {

            if(binding.diaryContent.text.toString().isNotEmpty()){

                val diary = Diary(null, binding.diaryContent.text.toString(), binding.diaryDate.text.toString())
                helper.insertDiary(diary)
                adapter.listData.clear()
                adapter.listData.addAll(helper.selectDiary())
                adapter.notifyDataSetChanged()

            }

            startActivity(Intent(this, DiaryActivity::class.java))
        }
    }
}