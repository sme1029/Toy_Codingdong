package com.greedy.hinote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.greedy.hinote.databinding.ActivityLogBinding

class LogActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val binding by lazy { ActivityLogBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = Firebase.auth

        /* 회원가입 이동 */
        binding.join.setOnClickListener {
            startActivity(Intent(this, JoinActivity::class.java))
        }

        /* 접속 */
        binding.btnLogin.setOnClickListener {

            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            /* 유효성 검사 */
            if(email.isNotEmpty() && password.isNotEmpty()){
                signIn(email, password)
            }else{
                Toast.makeText(this, "이메일과 비밀번호를 전부 입력하세요.", Toast.LENGTH_SHORT).show()
            }
            
        }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "하이노트에 오신 것을 환영합니다.", Toast.LENGTH_SHORT).show()
                    moveMainPage()
                } else {
                    Toast.makeText(this, "입력하신 정보가 틀렸습니다.", Toast.LENGTH_SHORT).show()

                }
            }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            moveMainPage()
        }
    }

    fun moveMainPage(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}