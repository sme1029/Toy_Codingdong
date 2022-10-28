package com.greedy.hinote


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.greedy.hinote.databinding.CommentRecyclerBinding



class CommentsAdapter(var commentsList: List<Comment?>) : RecyclerView.Adapter<CommentHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentHolder {
        return CommentHolder(CommentRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return if (commentsList == null) 0 else commentsList!!.size
    }

    override fun onBindViewHolder(viewHolder: CommentHolder, position: Int) {
        val item = commentsList!![position]
        //viewHolder.setItem(item)
    }

}

class CommentHolder(val binding: CommentRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {

    fun setItem(comment: Comment?) {
        binding.commentWriter.text = comment?.username
        binding.commentBody.text = comment?.body
    }

}

