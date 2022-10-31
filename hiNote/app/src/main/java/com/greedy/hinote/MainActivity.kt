package com.greedy.hinote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.greedy.hinote.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = Firebase.auth

        // 전체 합치고 나면 주석 풀을 예정.
//        /* 다이어리 이동 */
//        binding.diary.setOnClickListener {
//            startActivity(Intent(this, DiaryActivity::class.java))
//        }
//
        /* 도서리뷰 이동 */
        binding.book.setOnClickListener {
            startActivity(Intent(this, BookActivity::class.java))
        }

        binding.currentUser.text = "${auth.currentUser?.email}"

        binding.btnLogout.setOnClickListener {
            val intent = Intent(this, LogActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)

            /* 파이어베이스 로그아웃*/
            auth.signOut()
        }
    }
}