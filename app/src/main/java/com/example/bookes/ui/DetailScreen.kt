package com.example.bookes.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Map
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.bookes.ui.Data.VehicleData
import com.example.bookes.ui.ViewModel.DetailUiState
import com.example.bookes.ui.ViewModel.DetailViewModel
import com.example.bookes.ui.ViewModel.DetailViewModelFactory


val greenNokia = Color(0xFF94BF6F)
val greenlight = Color(0xFFEEEEEE)
val CardBackground = Color.White.copy(alpha = 0.1f)


@Composable
fun DetailScreen(
    itemId: Int,
    onItemClick: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val viewModel: DetailViewModel = viewModel(
        factory = DetailViewModelFactory(itemId)
    )

    val uiState by viewModel.uiState.collectAsState()

    when (val state = uiState) {
        is DetailUiState.Loading -> {
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is DetailUiState.Error -> {
            Text(text = "Kesalahan : ${state.message}", color = Color.Red)
            println(state.message)
        }

        is DetailUiState.Succes -> {
            carDetailContent(
                item = state.items,
                onItemClick = onItemClick,
                onNavigateBack = onNavigateBack
            )


        }
    }

}

@Composable
fun carDetailContent(
    item: VehicleData,
    onItemClick: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val backInteractionSource = remember { MutableInteractionSource() }
    val goInteractionSource = remember { MutableInteractionSource() }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .clip(
                    RoundedCornerShape(
                        topEnd = 0.dp,
                        topStart = 0.dp,
                        bottomEnd = 35.dp,
                        bottomStart = 35.dp
                    )
                )
                .background(greenlight)
                .clickable(
                    interactionSource = backInteractionSource,
                    indication = LocalIndication.current,
                    onClick = onNavigateBack
                ),
            contentAlignment = Alignment.TopCenter
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .clip(CircleShape)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Icon",
                        tint = Color.Black
                    )
                }
                Text(
                    text = item.name,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier.padding(horizontal = 50.dp),
                    color = Color.Black,

                    )

            }

            Image(
                painter = rememberAsyncImagePainter(model = item.image),
                contentDescription = item.type,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp)
                    .offset(y = 60.dp)
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Box(
            modifier =
            Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(top = 15.dp, bottom = 5.dp, start = 15.dp, end = 15.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .background(greenlight)
            ) {

                Column(modifier = Modifier.padding(start = 15.dp, bottom = 5.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = item.type,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )

                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Color.White)
                                .padding(5.dp)
                                .clickable(
                                    interactionSource = goInteractionSource,
                                    indication = LocalIndication.current,
                                    onClick =  onItemClick
                                ),
                            contentAlignment = Alignment.Center

                        ) {
                            Icon(
                                imageVector = Icons.Default.Map,
                                contentDescription = "Maps",
                                tint = Color.Black
                            )
                        }
                    }
                    Text(
                        text = item.price,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 5.dp),
                        color = Color.Black


                    )
                    Text(
                        "The Maybach Exelero is a one-of-a-kind luxury sports car created in 2004, blending the elegance of Maybach’s design language with the raw power of a hypercar. Commissioned by Fulda, a subsidiary of Goodyear, the Exelero was built to test high-performance tires under extreme conditions, and it succeeded in doing so with style. Powered by a massive 6.0-liter twin‑turbo V12 engine that produces around 700 horsepower, the car can reach speeds of more than 350 km/h (217 mph) ",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black

                    )
                }

            }
        }

        MenuBar()
    }

}

@Preview(showBackground = true)
@Composable
fun detailScreenPreview(){
    carDetailContent(item = VehicleData(id = 1, name = "cobba", type = "Sedan", year = "2024", price = "50000", image = ""), onItemClick = { /*TODO*/ }, onNavigateBack = { /*TODO*/ })
}