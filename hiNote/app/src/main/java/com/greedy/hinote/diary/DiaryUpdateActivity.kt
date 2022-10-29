package com.greedy.hinote.diary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.greedy.hinote.R
import com.greedy.hinote.databinding.ActivityDiaryUpdateBinding

class DiaryUpdateActivity : AppCompatActivity() {

    val binding by lazy { ActivityDiaryUpdateBinding.inflate(layoutInflater) }
    val helper = SqliteHelper(this, "diary", 1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val adapter = DiaryAdapter()

        binding.saveButton2.setOnClickListener {

            if(binding.modifyContent.text.toString().isNotEmpty()){

                val diary = Diary(null, binding.modifyContent.text.toString(), binding.modifyDate.text.toString())
                helper.onUpgrade(diary)
                adapter.listData.clear()
                adapter.listData.addAll(helper.selectDiary())
                adapter.notifyDataSetChanged()

            }

            startActivity(Intent(this, DiaryActivity::class.java))
        }
    }
}