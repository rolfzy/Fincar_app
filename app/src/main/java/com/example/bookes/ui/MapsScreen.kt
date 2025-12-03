package com.example.bookes.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DisplaySettings
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookes.ui.Data.VehicleData
import com.example.bookes.ui.ViewModel.DetailUiState
import com.example.bookes.ui.ViewModel.DetailViewModel
import com.example.bookes.ui.ViewModel.DetailViewModelFactory
import com.example.bookes.ui.ViewModel.HomeViewModel
import com.example.bookes.ui.ViewModel.VehicleUiState

@Composable
fun MapsScreen(itemId: Int, onItemClick: () -> Unit, onNavigateBack: () -> Unit,
               viewModel: HomeViewModel = viewModel()) {


    val uiState by viewModel.uiState.collectAsState()


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(greenlight),
        contentAlignment = Alignment.TopCenter
    ) {
        when (val state = uiState) {
            is VehicleUiState.Loading -> CircularProgressIndicator()
            is VehicleUiState.Error -> Text(text = "Kesalahan : ${state.message}", color = Color.Red)
            is VehicleUiState.Succes ->
                mapDetailContent(
                    item =  ,
                    onItemClick = onItemClick,
                    onNavigateBack = onNavigateBack
                )


            }
        }

    }


}

@Composable
fun mapDetailContent(item: VehicleData, onItemClick: () -> Unit, onNavigateBack: () -> Unit) {
    val backInteractionSource = remember { MutableInteractionSource() }
    val goInteractionSource = remember { MutableInteractionSource() }
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .width(335.dp)
                        .height(35.dp)
                        .clip(CircleShape)
                        .padding(start = 5.dp, end = 5.dp)
                        .background(Color.White),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Icon",
                        tint = Color.Black
                    )
                }
                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .clip(CircleShape)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.DisplaySettings,
                        contentDescription = "Icon",
                        tint = Color.Black,
                        modifier = Modifier.size(17.dp)
                    )
                }

            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp))
                .background(Color.White)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                item() {
                    Box(
                        modifier = Modifier
                            .border(
                                width = 2.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .clip(RoundedCornerShape(10.dp))
                    ) {
                        VehicleSection(item = item, onItemClick = {_->onItemClick})
                    }

                }
            }
        }


    }
}