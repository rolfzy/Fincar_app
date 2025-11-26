package com.example.bookes.ui.Data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VehicleData(
    @SerialName("id")
    val id: String? = null,
    @SerialName("name")
    val name: String? = null ,
    @SerialName("type")
    val type: String? = null,
    @SerialName("year")
    val year: String? = null,
    @SerialName("price")
    val price: String? = null,
    @SerialName("image")
    val image: String? = null

)
