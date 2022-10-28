package com.greedy.hinote

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
/* 사용할 메소드 인터페이스 목록 */
interface BooksApi {

    /* Post 전체 목록 조회 */
    /* json 요청 시 skip과 limit 파라미터를 보내 그 사이에 있는 목록을 가져옴 */
    /* 받아올 클래스의 형식을 convert 해줄 수 있음 */
    @GET("/api/bestSeller.api?&output=json&categoryId=100")
    suspend fun books(@Query("key") apiKey: String = "A4891F219B3415D8B9249513CA6E0B20A07563EA953E2944017A5324BA8E5607", ) : Response<Books>

//    @GET("/api/search.api?&output=json&queryType=isbn")
//    suspend fun getBooksByName(
//        @Query("key") apiKey: String = "A4891F219B3415D8B9249513CA6E0B20A07563EA953E2944017A5324BA8E5607",
//        @Query("query") kweyWord: String
//    ): Call<Books>

    /* Post 1개 상세 조회 */
    @GET("/api/search.api?key=A4891F219B3415D8B9249513CA6E0B20A07563EA953E2944017A5324BA8E5607&query={itemId}&output=json&queryType=isbn")
    suspend fun post(
                     @Path("itemId") itemId: Int
    ) : Response<Book> //API에 id 요청





}