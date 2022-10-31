package com.greedy.hinote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.greedy.hinote.databinding.ActivityBookBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class BookActivity : AppCompatActivity() {

    private val binding by lazy { ActivityBookBinding.inflate(layoutInflater) }
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BooksAdapter
    private lateinit var posts: Books
    private lateinit var postList: MutableList<Book?>
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        recyclerView = binding.booksView //리사이클뷰, 로드데이터, 스크롤 뷰
        loadData()
        initScrollListener()


    }

    private fun loadData() {

        CoroutineScope(Dispatchers.Main).launch {

            /*api 요청*/
            withContext(Dispatchers.IO) {
                val response = BooksService.getPostsService().books() //postservice의 메소드 호출
                if (response.isSuccessful) {
                    posts = response.body()!! //body 내용을 posts로 선언
                    postList = posts.item.toMutableList()
                } else {
                    Log.d("Error", "${response.message()}")
                }
            }

            adapter = BooksAdapter(postList)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(baseContext)


        }
    }

    private fun initScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                /* 식별을 위한 범위 */
                if (!isLoading) {
                    /* 리스트의 끝일 때 position 0-19, size 20 -1*/
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == postList.size - 1) {
                        //bottom of list!

                        isLoading = true //json 요청을 계속 되는걸 막기 위해
                    }
                }
            }
        })
    }



}

