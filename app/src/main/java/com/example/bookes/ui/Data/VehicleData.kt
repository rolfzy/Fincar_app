package com.example.bookes.ui.Data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VehicleData(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("type")
    val type: String,
    @SerialName("year")
    val year: String,
    @SerialName("price")
    val price: String,
    @SerialName("image")
    val image: String

)
