package com.greedy.hinote


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.greedy.hinote.databinding.CommentRecyclerBinding
import java.text.SimpleDateFormat

/*# 리사이클러뷰 쪽에다가 데이터를 연결하기 위한 클래스 */
class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.Holder> () {

    var listData = mutableListOf<Memo>()
    var helper:sqliteHelper? = null

    @SuppressLint("NotifyDataSetChanged")
    inner class Holder(val binding: CommentRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {

        /* 하나의 Recycler View Holder에는 하나의 Memo Data가 존재 */
        var mMemo: Memo? = null

        /* 삭제 버튼 클릭 시 발생 이벤트 */
        init {
            binding.deleteButton.setOnClickListener{
                /* !!사용으로 널러블하지않게 */
                helper?.deleteMemo(mMemo!!)
                listData.remove(mMemo)
                /* 변화를 화면에 반영시켜 달라는것 */
                notifyDataSetChanged()
            }
        }


        @SuppressLint("SimpleDateFormat")
        fun setMemo(memo: Memo) {
            binding.textNo.text = "${memo.no}"
            binding.textContent.text = memo.content
            val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm")
            binding.textDatetime.text = sdf.format(memo.datetime)

            mMemo = memo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = CommentRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val memo = listData[position]
        holder.setMemo(memo)
    }
}