package com.greedy.hinote.diary

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.greedy.hinote.databinding.DiaryRecyclerBinding

class DiaryAdapter : RecyclerView.Adapter<DiaryAdapter.Holder> () {

    var listData = mutableListOf<Diary>()
    var helper:SqliteHelper? =null

    inner class Holder(val binding: DiaryRecyclerBinding) : RecyclerView.ViewHolder(binding.root){

        var dDiary: Diary? = null

      init{
            binding.root.setOnClickListener {
                val intent = Intent(it.context, DiaryDetailActivity::class.java)
                intent.putExtra("diaryNo", dDiary?.no)
                intent.putExtra("diaryDate", dDiary?.diaryDate.toString())
                intent.putExtra("diaryContent", dDiary?.diaryContent.toString())
                it.context.startActivity(intent)
            }
        }

      init{
          binding.btnDelet.setOnClickListener {
              helper?.deleteDiary(dDiary!!)
              listData.remove(dDiary)
              notifyDataSetChanged()
          }

      }


        fun setDiary(diary: Diary){
            binding.textNo.text ="${diary.no}"
            binding.diaryDate.text = diary.diaryDate
            binding.diaryContent.text = diary.diaryContent

            dDiary = diary
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
       val binding = DiaryRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  Holder(binding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val diary = listData[position]
        holder.setDiary(diary)
    }
}