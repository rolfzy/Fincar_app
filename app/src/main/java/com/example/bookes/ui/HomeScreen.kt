package com.example.bookes.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Grade
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.bookes.R
import com.example.bookes.ui.Data.VehicleData
import com.example.bookes.ui.ViewModel.HomeViewModel
import com.example.bookes.ui.ViewModel.VehicleUiState


val CardBackgrounds = Color.White.copy(alpha = 0.1f)
val blue = Color(0xFFADD8E)
val gray = Color(0xFFF8FAFC)
val black = Color(0xFF373A40)

@Composable
fun HomeScreen(
    navController: NavController,
    onItemClick: (VehicleData) -> Unit,
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(greenlight),
        contentAlignment = Alignment.Center
    ) {
        when (val state = uiState) {
            is VehicleUiState.Loading -> {
                CircularProgressIndicator()
            }

            is VehicleUiState.Error -> {
                Text(text = "Kesalahan : ${state.message}", color = Color.Red)
                println(state.message)
            }

            is VehicleUiState.Succes -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 24.dp)

                ) {
                    item {
                        Spacer(Modifier.height(20.dp))
                        HomeTopBar()
                        Spacer(Modifier.height(20.dp))
                    }
                    item {
                        HomeTitle()
                        Spacer(Modifier.height(20.dp))
                    }
                    item {
                        HomeFilterTabs()
                        Spacer(Modifier.height(20.dp))
                    }
                    items(state.items) { vehicle ->
                        VehicleSection(item = vehicle, onItemClick = onItemClick)
                        Spacer(Modifier.height(20.dp))
                    }

                }
            }

        }
    }


}


@Composable
fun HomeTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularIconButton(
            icon = Icons.Default.Menu,
            onClick = {}
        )
        Spacer(modifier = Modifier.weight(1f))


        CircularIconButton(
            icon = Icons.Outlined.Notifications,
            onClick = {}
        )

        Spacer(modifier = Modifier.width(12.dp))

        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )

    }


}

@Composable
fun CircularIconButton(icon: ImageVector, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(Color.White)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = black,
            modifier = Modifier.size(24.dp)
        )
    }

}

@Composable
fun HomeTitle() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Easiest Way To",
                fontSize = 28.sp,
                color = black,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Finding Your Car",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = black
            )
        }
        Surface(
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(16.dp))
                .clickable { }
                .border(1.dp, black, RoundedCornerShape(16.dp)),
            color = Color.White,
            shadowElevation = 4.dp
        ) {
            Box(contentAlignment = Alignment.Center)
            {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = black,
                    modifier = Modifier.size(24.dp)
                )
            }
        }


    }
}

@Composable
fun HomeFilterTabs() {
    var selectedIndexTab by remember { mutableStateOf(0) }
    val tabs = listOf("Type", "Price", "Brand")

    LazyRow(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
        items(tabs.size) { index ->
            val isSelected = selectedIndexTab == index
            val backgroundColor = if (isSelected) black else CardBackground
            val textColor = if (isSelected) Color.White else black
            val interactionSource = remember { MutableInteractionSource() }

            Box(modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(backgroundColor)
                .clickable(
                    interactionSource = interactionSource,
                    indication = LocalIndication.current,
                    onClick = {
                        selectedIndexTab = index
                    }
                )
                .border(width = 1.dp, color = black, shape = RoundedCornerShape(12.dp))
                .padding(vertical = 10.dp, horizontal = 20.dp)
            ) {
                Text(text = tabs[index], color = textColor, fontWeight = FontWeight.Medium)

            }

        }
    }

}

@Composable
fun VehicleSection(item: VehicleData, onItemClick: (VehicleData) -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .size(250.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .border(2.dp, Color.White, RoundedCornerShape(10.dp))
            .clickable { onItemClick(item) }

    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(150.dp)
                    .clip(RoundedCornerShape(15.dp))

            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = item.image),
                    contentDescription = "Car",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    Box(
                        modifier =
                        Modifier
                            .width(80.dp)
                            .height(30.dp)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Best Deal",
                            color = black,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
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
                            imageVector = Icons.Default.Grade,
                            contentDescription = "Save",
                            tint = black,
                            modifier = Modifier.size(24.dp)
                        )
                    }


                }


            }
            Spacer(Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                item.name?.let {
                    Text(
                        text = it,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = black
                    )
                }
                item.price?.let {
                    Text(
                        text = it,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = black
                    )
                }

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 5.dp)
                ) {
                    Text(
                        text = item.type.toString(),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = black
                    )
                    Text(
                        text = item.year.toString(),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = black
                    )

                }
                Column(Modifier.padding(end = 5.dp)) {
                    Box(
                        modifier = Modifier
                            .width(50.dp)
                            .height(30.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .background(black),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Buy",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }


        }


    }

}


@Composable
fun HomeScreenPreview() {

}

