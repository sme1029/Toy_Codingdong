package com.greedy.hinote.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.greedy.hinote.R

import com.greedy.hinote.databinding.ActivityWeatherBinding
import retrofit2.*
import retrofit2.Response

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.text.SimpleDateFormat

import java.util.*

class WeatherActivity : AppCompatActivity() {

    private val binding by lazy { ActivityWeatherBinding.inflate(layoutInflater) }

    private var base_date = "20210510"
    private var base_time = "1400"
    private var nx = "55"
    private var ny = "127"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.weatherRecyclerView.layoutManager = LinearLayoutManager(this)

        binding.tvDate.text =
            SimpleDateFormat("MM월 dd일", Locale.getDefault()).format(Calendar.getInstance().time) + "날씨"
            setWeather(nx, ny)


        val retrofit = Retrofit.Builder()
            .baseUrl("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val weatherService = retrofit.create(WeatherService::class.java)

        val call = ApiObject.retrofitService.getWeather(60, 1, "JSON", base_date, base_time, nx, ny)


        call.enqueue(object : retrofit2.Callback<WeatherResponse> {

            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {

                    val it: List<Item> = response.body()!!.response.body.items.item

                    val weatherArr = arrayOf(ModelWeather(), ModelWeather(), ModelWeather(), ModelWeather(), ModelWeather(), ModelWeather())

                    var index = 0
                    val totalCount = response.body()!!.response.body.totalCount - 1
                    for (i in 0..totalCount) {
                        index %= 6
                        when(it[i].category) {
                            "PTY" -> weatherArr[index].rainType = it[i].fcstValue
                            "REH" -> weatherArr[index].humidity = it[i].fcstValue
                            "SKY" -> weatherArr[index].sky = it[i].fcstValue
                            "T1H" -> weatherArr[index].temp = it[i].fcstValue
                            else -> continue
                        }
                        index++
                    }

                    for (i in 0..5) weatherArr[i].fcstTime = it[i].fcstTime

                   binding.weatherRecyclerView.adapter = WeatherAdapter(weatherArr)

                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                val tvError = findViewById<TextView>(R.id.tvError)
                tvError.text = "api fail : " +  t.message.toString() + "\n 다시 시도해주세요."
                tvError.visibility = View.VISIBLE
                Log.d("api fail", t.message.toString())
            }
        })
    }

    private fun getBaseTime(h : String, m : String) : String {
        var result = ""


        if (m.toInt() < 45) {
            if (h == "00") result = "2330"
            else {
                var resultH = h.toInt() - 1
                if (resultH < 10) result = "0" + resultH + "30"
                else result = resultH.toString() + "30"
            }
        }
        else result = h + "30"

        return result
    }

    fun setWeather(nx: String, ny: String) {
        val cal = Calendar.getInstance()
        base_date = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(cal.time)
        val timeH = SimpleDateFormat("HH", Locale.getDefault()).format(cal.time)
        val timeM = SimpleDateFormat("HH", Locale.getDefault()).format(cal.time)
        base_time = getBaseTime(timeH, timeM)
        if (timeH == "00" && base_time == "2330") {
            cal.add(Calendar.DATE, -1).toString()
            base_date = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(cal.time)

        }
    }

}































