package com.greedy.hinote

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.greedy.hinote.databinding.ActivityBookDetailBinding


class BookDetailActivity : AppCompatActivity(){

    private val binding by lazy { ActivityBookDetailBinding.inflate(layoutInflater) }
    //private lateinit var adapter: CommentsAdapter
    private  var post: Book? = null
    // private lateinit var comments: Comments
    val helper = sqliteHelper(this, "memo", 1)



    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val isbn = intent.getStringExtra("isbn")
        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val coverLargeUrl = intent.getStringExtra("coverLargeUrl")
        Log.d("isbn",isbn!!)

        binding.title.text = title
        binding.description.text = description

        Glide.with(binding.coverImageView.context)
            .load(coverLargeUrl.orEmpty())
            .into(binding.coverImageView)

        val adapter = RecyclerAdapter()
        adapter.helper = helper

        /* selectMemo를 통해 DB에 있는 메모를 모두 조회해서 List 반환 받고
        * 해당 데이터를 Adapter의 listData에 담는다. */
        adapter.listData.addAll(helper.selectMemo(isbn))

        /* main activity의 recyclerView에 생성한 어댑터 연결하고 레이아웃 설정 */
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        /* 메모 저장 버튼 이벤트 */
        binding.saveButton.setOnClickListener {
            /* 메모 내용이 입력 된 경우만 동작 */
            if(binding.editMemo.text.toString().isNotEmpty()) {
                val memo = Memo(null,
                    binding.editMemo.text.toString(),
                    System.currentTimeMillis(),
                    isbn!!
                )

                helper.insertMemo(memo)
                adapter.listData.clear()
                adapter.listData.addAll(helper.selectMemo(isbn))
                /* DB가 변동 되었을 때 화면도 변동될 수 있도록 adapter의 data를 수정하고
                * 데이터가 변화 했음을 알린다. */
                adapter.notifyDataSetChanged()


                /* 입력란 비우기 */
                binding.editMemo.setText("")
            }
        }
    }




}