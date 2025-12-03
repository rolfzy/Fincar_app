package com.example.bookes.ui.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.bookes.ui.Api.RetrofitClient
import com.example.bookes.ui.Data.VehicleData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface DetailUiState {
    object Loading : DetailUiState
    data class Succes(val items: VehicleData) : DetailUiState
    data class Error(val message: String) : DetailUiState
}

class DetailViewModel(itemId: Int) : ViewModel() {
    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState: StateFlow<DetailUiState> = _uiState

    init {
        fetchDetailScreen(itemId)
    }

    private fun fetchDetailScreen(id: Int) {
        _uiState.value = DetailUiState.Loading
        viewModelScope.launch {
            try {
                val item = RetrofitClient.instance.getVehicleById(id)
                _uiState.value = DetailUiState.Succes(item)
            } catch (e: Exception) {
                _uiState.value = DetailUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

class DetailViewModelFactory(private val itemId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailViewModel(itemId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}