package com.greedy.hinote

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.greedy.hinote.databinding.ActivityBookDetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookDetailActivity : AppCompatActivity(){

    private val binding by lazy { ActivityBookDetailBinding.inflate(layoutInflater) }
    //private lateinit var adapter: CommentsAdapter
    private  var post: Book? = null
   // private lateinit var comments: Comments

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val coverLargeUrl = intent.getStringExtra("coverLargeUrl")

        binding.title.text = title
        binding.description.text = description

        Glide.with(binding.coverImageView.context)
            .load(coverLargeUrl.orEmpty())
            .into(binding.coverImageView)

    }

    private fun loadData(title: Int, ) {

//        CoroutineScope(Dispatchers.Main).launch {
//            /* 통신 부분 */
//            withContext(Dispatchers.IO) {
//                val postResponse = BooksService.getPostsService().post(isbn)
//                if (postResponse.isSuccessful) {
//                    post = postResponse.body()!!
//                } else {
//                    Log.d("Error", "${postResponse.message()}")
//                    //Log.d("Error", "${commentsResponse.message()}")
//                }
//            }
            /* 화면 처리 부분 */
            binding.title.text = post?.title
            binding.description.text = post?.description

//           binding.content.text = post.body
//           binding.commentCount.text = "comments(${comments.total})"
//
//           adapter = CommentsAdapter(comments.comments) // 어댑터 이용해서 리사이클뷰에 뿌려줌
//            binding.commentRecyclerView.adapter = adapter
//            binding.commentRecyclerView.layoutManager = LinearLayoutManager(baseContext)
//
//            binding.layout.visibility = View.VISIBLE //레이아웃 보여줌
//            binding.progressBar.visibility = View.GONE  //프로그래스바 숨김

            Glide.with(binding.coverImageView.context)
                .load(post?.coverLargeUrl.orEmpty())
                .into(binding.coverImageView)


        }

    }



