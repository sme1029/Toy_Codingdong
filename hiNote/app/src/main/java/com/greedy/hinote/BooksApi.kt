package com.greedy.hinote

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
/* 사용할 메소드 인터페이스 목록 */
interface BooksApi {

    /* Post 전체 목록 조회 */
    /* 받아올 클래스의 형식을 convert 해줄 수 있음 */
    @GET("/api/bestSeller.api?&output=json&categoryId=100")
    suspend fun books(@Query("key") apiKey: String = "A4891F219B3415D8B9249513CA6E0B20A07563EA953E2944017A5324BA8E5607", ) : Response<Books>

}