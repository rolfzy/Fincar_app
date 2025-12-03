package com.example.bookes.ui.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookes.ui.Api.RetrofitClient
import com.example.bookes.ui.Data.VehicleData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface VehicleUiState {
    object Loading : VehicleUiState
    data class Succes(val items: List<VehicleData>) : VehicleUiState
    data class Error(val message: String) : VehicleUiState
}

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<VehicleUiState>(VehicleUiState.Loading)
    val uiState: StateFlow<VehicleUiState> = _uiState

    init {
        fetchVehicleData()
    }

    fun fetchVehicleData() {
        _uiState.value = VehicleUiState.Loading
        viewModelScope.launch {
            try {
                val items = RetrofitClient.instance.getAllVehicle()

                _uiState.value = VehicleUiState.Succes(items)
            }catch (e:Exception){
                _uiState.value = VehicleUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}