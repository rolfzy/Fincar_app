package com.example.bookes.ui.Data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VehicleData(
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("type")
    val type: String,
    @SerialName("year")
    val year: String,
    @SerialName("price")
    val price: Double,
    @SerialName("image")
    val image: String

)
