package com.greedy.hinote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.greedy.hinote.databinding.ActivityJoinBinding

class JoinActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val binding by lazy { ActivityJoinBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = Firebase.auth

        /* 파이어베이스 등록 요청 */
        binding.btnjoin.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            /* 유효성 검사 */
            if(email.isNotEmpty() && password.isNotEmpty()){
                createAccount(email, password)
            }else{
                Toast.makeText(this, "이메일과 비밀번호를 전부 입력하세요.", Toast.LENGTH_SHORT).show()
            }
        }

        /* 메인 액티비티 이동 */
        binding.btnCancle.setOnClickListener { finish() }
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "회원 가입이 완료 되었습니다.", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "회원 가입이 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    public override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if(currentUser != null){
            finish()
        }
    }
}