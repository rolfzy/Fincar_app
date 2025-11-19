package com.example.bookes.ui.Data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VehicleData(
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("type")
    val category : String,
    val type: String,
    @SerialName("price")
    val price: Double,
    @SerialName("image")
    val image: String
)
