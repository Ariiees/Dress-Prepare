package com.example.dresscode

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.widget.TextView
import android.widget.ImageView
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class Weather : AppCompatActivity() {

    private lateinit var titleTextView: TextView
    private lateinit var temperatureTextView: TextView
    private lateinit var tempFeelsTextView: TextView
    private lateinit var tempHLTextView: TextView
    private lateinit var uvIndexTextView: TextView
    private lateinit var precipitationTextView: TextView
    private lateinit var humidityTextView: TextView
    private lateinit var windspeedTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather)

        // Initialize Bottom Navigation Buttons
        val homeButton = findViewById<ImageView>(R.id.homeIcon)
        val chatButton = findViewById<ImageView>(R.id.aiChatIcon)
        val weatherButton = findViewById<ImageView>(R.id.weatherIcon)
        val eventButton = findViewById<ImageView>(R.id.eventAttireIcon)
        val settingButton = findViewById<ImageView>(R.id.profileIcon)
        Util.bottomBarJump(
            this,
            homeButton,
            chatButton,
            weatherButton,
            eventButton,
            settingButton,
            3
        )

        // Info card elements
        titleTextView = findViewById(R.id.titleText)
        temperatureTextView = findViewById(R.id.temperature)
        tempFeelsTextView = findViewById(R.id.temp_feels)
        tempHLTextView = findViewById(R.id.temp_highlow)
        uvIndexTextView = findViewById(R.id.uvIndex)
        precipitationTextView = findViewById(R.id.precip_text)
        humidityTextView = findViewById(R.id.humid_text)
        windspeedTextView = findViewById(R.id.wind_text)

        // Set initial weather data
        fetchWeatherData("Newark", 39.6837, -75.7497)

        // Set listener to update location
        titleTextView.setOnClickListener {
            showLocationInputDialog()
        }
    }

    @Suppress("SameParameterValue")
    private fun fetchWeatherData(location: String, latitude: Double, longitude: Double) {
        // Note: latitude & longitude never changes, might add real geocoding who knows.
        // Technically dynamic latitude and longitude. Defaults to Newark, DE, USA.
        val url = "https://api.open-meteo.com/v1/forecast?latitude=$latitude&longitude=$longitude&hourly=temperature_2m,relative_humidity_2m,apparent_temperature,precipitation_probability,weather_code,wind_speed_10m,wind_direction_10m&daily=weather_code,temperature_2m_max,temperature_2m_min,uv_index_max,precipitation_probability_max,wind_speed_10m_max&timezone=America%2FNew_York&models=gfs_seamless"

        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@Weather, "Failed to fetch weather data", Toast.LENGTH_SHORT).show()
                }
            }

            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call, response: Response) {
                response.body?.let { responseBody ->
                    val json = JSONObject(responseBody.string())
                    val currentWeather = json.getJSONObject("hourly") // Access hourly weather data
                    val dailyWeather = json.getJSONObject("daily") // Access daily weather data

                    // Extract hourly values (e.h., temperature, apparent temperature)
                    val temperature = currentWeather.getJSONArray("temperature_2m").getDouble(0)
                    val relHumidity = currentWeather.getJSONArray("relative_humidity_2m").getDouble(0)
                    val apparentTemperature = currentWeather.getJSONArray("apparent_temperature").getDouble(0)
                    val windSpeed = currentWeather.getJSONArray("wind_speed_10m").getDouble(0)

                    // Extract daily values (e.g., max/min temperature, UV index)
                    val maxTemp = dailyWeather.getJSONArray("temperature_2m_max").getDouble(0)
                    val minTemp = dailyWeather.getJSONArray("temperature_2m_min").getDouble(0)
                    val uvIndexMax = dailyWeather.getJSONArray("uv_index_max").getDouble(0)
                    val precipitationMax = dailyWeather.getJSONArray("precipitation_probability_max").getInt(0)
                    //val windSpeedMax = dailyWeather.getJSONArray("wind_speed_10m_max").getDouble(0)

                    runOnUiThread {
                        titleTextView.text = location
                        temperatureTextView.text = "Temperature: $temperature°C"
                        tempFeelsTextView.text = "Feels Like: $apparentTemperature°C"
                        tempHLTextView.text = "H: $maxTemp°C L: $minTemp°C"
                        uvIndexTextView.text = "UV Index: $uvIndexMax"
                        precipitationTextView.text = "Precip.: $precipitationMax%"
                        humidityTextView.text = "Humidity: $relHumidity%"
                        windspeedTextView.text = "Wind: $windSpeed km/h"
                    }
                }
            }
        })
    }

    private fun showLocationInputDialog() {
        val editText = EditText(this)
        editText.hint = "Enter location"

        AlertDialog.Builder(this)
            .setTitle("Change Location")
            .setView(editText)
            .setPositiveButton("OK") { _, _ ->
                val newLocation = editText.text.toString()
                if (newLocation.isNotBlank()) {
                    // Replace with real geocoding to get latitude and longitude
                    val latitude = 39.6837 // Placeholder
                    val longitude = -75.7497 // Placeholder
                    fetchWeatherData(newLocation, latitude, longitude)
                } else {
                    Toast.makeText(this, "Location cannot be empty", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
