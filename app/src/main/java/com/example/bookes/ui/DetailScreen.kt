package com.example.bookes.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookes.R


val greenNokia = Color(0xFF94BF6F)
val greenlight = Color(0xFFEEEEEE)
val CardBackground = Color.White.copy(alpha = 0.1f)


@Preview(showBackground = true)
@Composable
fun DetailScreen() {
    Column(modifier = Modifier.fillMaxWidth()
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
                .background(greenlight),
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
                    text = "Maybach Exelero",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier.padding(horizontal = 50.dp),

                    )

            }

            Image(
                painter = painterResource(R.drawable.supercar),
                contentDescription = "supercar",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(330.dp)
                    .offset(y = 80.dp)
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
                            text = "Super Car ",
                            fontSize = 25.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                                .background(Color.White),
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
                        "50000$",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                    Text(
                        "The Maybach Exelero is a one-of-a-kind luxury sports car created in 2004, blending the elegance of Maybach’s design language with the raw power of a hypercar. Commissioned by Fulda, a subsidiary of Goodyear, the Exelero was built to test high-performance tires under extreme conditions, and it succeeded in doing so with style. Powered by a massive 6.0-liter twin‑turbo V12 engine that produces around 700 horsepower, the car can reach speeds of more than 350 km/h (217 mph) ",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

            }
        }

            MenuBar()
    }


}