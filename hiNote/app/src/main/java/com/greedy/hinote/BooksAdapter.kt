package com.greedy.hinote

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.greedy.hinote.databinding.BooksRecyclerBinding

class BooksAdapter(var postList: List<Book?>) : RecyclerView.Adapter<PostHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        return PostHolder(BooksRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return if (postList == null) 0 else postList!!.size
    }

    override fun onBindViewHolder(viewHolder: PostHolder, position: Int) {
        val item = postList!![position]
        viewHolder.setItem(item)
    }



}
class PostHolder(val binding: BooksRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {

    lateinit var post: Book

    /* */
    init {
        binding.root.setOnClickListener {
            val intent = Intent(it.context, BookDetailActivity::class.java)
            intent.putExtra("title", post.title) // title 담아 PostDetailActivity로
            intent.putExtra("description", post.description)
            intent.putExtra("coverLargeUrl", post.coverLargeUrl)
            it.context.startActivity(intent)
        }
    }

    /* 태그들을 뿌린거 화면에 입력 */
    fun setItem(post: Book?) {

        binding.title.text = "${post?.title}"
        binding.description.text = "${post?.description}"

        Glide
            .with(binding.coverImageView.context)
            .load(post?.coverSmallUrl)
            .into(binding.coverImageView)


        this.post = post!!
    }
}

