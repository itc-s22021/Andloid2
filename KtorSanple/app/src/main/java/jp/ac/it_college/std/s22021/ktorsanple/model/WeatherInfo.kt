package jp.ac.it_college.std.s22021.ktorsanple.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherInfo(
    @SerialName("coord")
    val coordinates: Coodinates,

    val ewather: List<Weather>,

    @SerialName("name")
    val cityName: String,
)

@Serializable
data class Coodinates(
    @SerialName("lon")
    val longitude: Double,

    @SerialName("lat")
    val latitude: Double,
)

@Serializable
data class Weather(
    val id: Int,

    @SerialName("main")
    val froupName: String,

    val description: String,

    val icon: String,
)