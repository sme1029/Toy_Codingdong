package com.greedy.hinote.diary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.greedy.hinote.databinding.ActivityDiaryDetailBinding

class DiaryDetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDiaryDetailBinding.inflate(layoutInflater) }
    private lateinit var  adapter: DiaryAdapter
    private lateinit var diary: Diary
    val helper = SqliteHelper(this, "diary", 1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val diaryNo =intent.getLongExtra("diaryNo", 0)
        loadData(diaryNo)

    }

    private fun loadData(diaryNo: Long) {

        if(intent.hasExtra("diaryNo")){
            binding.btnDate2.text = intent.getStringExtra("diaryDate")
            binding.btnContent2.text = intent.getStringExtra("diaryContent")
        }

    }

}