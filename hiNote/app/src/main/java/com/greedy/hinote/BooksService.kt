package com.greedy.hinote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BooksService {
    //ratrofit 사용 //라이브러리 의존성 추가 필
    /* 구현체가 반환된다 */
    fun getPostsService() : BooksApi {

        return Retrofit.Builder()
            .baseUrl("https://book.interpark.com")
            .addConverterFactory(GsonConverterFactory.create()) //json응답 convert
            .build()
            .create(BooksApi::class.java) //사용할 메소드가 담긴 인터페이스 전달
    }
}