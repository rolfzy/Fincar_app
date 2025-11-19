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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookes.R
import com.example.bookes.ui.Data.VehicleData


val CardBackground=Color.White.copy(alpha = 0.1f)
val blue = Color(0xFFADD8E)
val black = Color(0xFFAADD)


@Composable
fun HomeScreen(
    onItemClick: (VehicleData) -> Unit,
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        when (val state = uiState) {
            is VehicleUiState.Loading -> {
                CircularProgressIndicator()
            }

            is VehicleUiState.Error -> {
                Text(text = "Kesalahan : ${state.message}", color = Color.Red)
            }

            is VehicleUiState.Succes -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 24.dp)

                ) {
                    val type = state.items.filter { it.category == "Type" }
                    val price = state.items.filter { it.category == "Price" }
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
                    item {
                        VehicleSection()
                        Spacer(Modifier.height(20.dp))
                    }
                    item {
                        VehicleSection()
                        Spacer(Modifier.height(20.dp))
                    }
                    item {
                        VehicleSection()
                        Spacer(Modifier.height(20.dp))
                    }
                    item {
                        MenuBar()
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
            .padding(horizontal = 24.dp, vertical = 16.dp),
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
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
    }

}

@Composable
fun HomeTitle() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Easiest way To ",
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Finding Car",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
        Surface(
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(16.dp))
                .clickable { },
            color = Color.White,
            shadowElevation = 4.dp
        ) {
            Box(contentAlignment = Alignment.Center)
            {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.Black,
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

    LazyRow(horizontalArrangement =  Arrangement.spacedBy(20.dp)) {
        items(tabs.size) { index ->
            val isSelected = selectedIndexTab ==index
            val backgroundColor = if (isSelected) blue else CardBackground
            val textColor =if (isSelected) Color.White else Color.Black
            val interactionSource = remember { MutableInteractionSource()  }

            Box(modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(backgroundColor)
                .clickable (
                    interactionSource = interactionSource,
                    indication = LocalIndication.current,
                    onClick =  {
                        selectedIndexTab = index
                    }
                )
                .border(width = 1.dp, color = blue, shape = RoundedCornerShape(12.dp))
                .padding(vertical = 10.dp , horizontal = 20.dp)
            ){
                Text(text = tabs[index], color = textColor, fontWeight = FontWeight.Medium)

            }

        }
    }
}

@Composable
fun VehicleSection() {
//next Time
    // like Card
}

@Composable
fun MenuBar() {

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(onItemClick = {})
}

