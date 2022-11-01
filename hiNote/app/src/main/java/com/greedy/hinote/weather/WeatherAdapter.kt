package com.greedy.hinote.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.greedy.hinote.R
import com.greedy.hinote.databinding.ListItemWeatherBinding

class WeatherAdapter (var items : Array<ModelWeather>) : RecyclerView.Adapter<WeatherAdapter.ViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherAdapter.ViewHolder {
      val binding = ListItemWeatherBinding.inflate(LayoutInflater.from(parent.context),  parent, false)
      return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: WeatherAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.setItem(item)
    }

    inner class ViewHolder(val binding: ListItemWeatherBinding) : RecyclerView.ViewHolder(binding.root){
        fun setItem(item : ModelWeather){
            binding.tvTime.text = item.fcstTime
            binding.tvRainType.text = getRainType(item.rainType)
            binding.tvHumidity.text = item.humidity
            binding.tvSky.text = getSky(item.sky)
            binding.tvTemp.text = item.temp + "°"
        }
    }

    fun getRainType(rainType: String) : String {
        return when(rainType) {
            "0" -> "없음"
            "1" -> "비"
            "2" -> "비/눈"
            "3" -> "눈"
            "5" -> "빗방울"
            "6" -> "빗방울눈날림"
            "7" -> "눈날림"
            else -> "오류 rainType : " + rainType
        }
    }

    fun getSky(sky : String) : String {
        return when(sky) {
            "1" -> "맑음"
            "3" -> "구름 많음"
            "4" -> "흐림"
            else -> "오류 rainType : " + sky
        }
    }

}


