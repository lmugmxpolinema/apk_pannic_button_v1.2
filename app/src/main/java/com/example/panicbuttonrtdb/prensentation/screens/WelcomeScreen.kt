package com.example.panicbuttonrtdb.prensentation.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.panicbuttonrtdb.ui.theme.CoralPink
import com.example.panicbuttonrtdb.ui.theme.SoftPink
import com.example.panicbuttonrtdb.ui.theme.TextDark

@Composable
fun WelcomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        CoralPink,
                        SoftPink
                    )
                )
            )
    ) {
        // Decorative Background Pattern
        DecorativePattern()

        // Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(100.dp))

            // Welcome Content
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Welcome",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Lorem ipsum dolor sit amet consectetur.\nLorem id sit",
                    fontSize = 16.sp,
                    color = Color.White.copy(alpha = 0.9f),
                    textAlign = TextAlign.Center,
                    lineHeight = 24.sp
                )
            }

            // Continue Button at Bottom
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                Button(
                    onClick = { navController.navigate("login") },
                    modifier = Modifier
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(28.dp),
                    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
                ) {
                    Text(
                        text = "Continue",
                        color = CoralPink,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Surface(
                        shape = CircleShape,
                        color = CoralPink,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = "Continue",
                                tint = Color.White,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DecorativePattern() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height
        
        // Draw wavy patterns (More Visible)
        val wavePath = Path().apply {
            moveTo(0f, height * 0.3f)
            cubicTo(
                width * 0.25f, height * 0.25f,
                width * 0.75f, height * 0.35f,
                width, height * 0.3f
            )
        }
        
        drawPath(
            path = wavePath,
            color = Color.White.copy(alpha = 0.25f),
            style = Stroke(width = 3f)
        )

        // Draw circles pattern (More Visible)
        val circlePositions = listOf(
            Offset(width * 0.15f, height * 0.15f) to 40f,
            Offset(width * 0.85f, height * 0.2f) to 60f,
            Offset(width * 0.25f, height * 0.4f) to 30f,
            Offset(width * 0.75f, height * 0.5f) to 45f,
            Offset(width * 0.1f, height * 0.6f) to 35f,
            Offset(width * 0.9f, height * 0.7f) to 50f
        )

        circlePositions.forEach { (offset, radius) ->
            drawCircle(
                color = Color.White.copy(alpha = 0.25f),
                radius = radius,
                center = offset,
                style = Stroke(width = 3f)
            )
        }

        // Draw X patterns (More Visible)
        val xSize = 30f
        val xPositions = listOf(
            Offset(width * 0.8f, height * 0.35f),
            Offset(width * 0.2f, height * 0.55f),
            Offset(width * 0.6f, height * 0.25f)
        )

        xPositions.forEach { offset ->
            // Draw X
            drawLine(
                color = Color.White.copy(alpha = 0.3f),
                start = Offset(offset.x - xSize / 2, offset.y - xSize / 2),
                end = Offset(offset.x + xSize / 2, offset.y + xSize / 2),
                strokeWidth = 4f
            )
            drawLine(
                color = Color.White.copy(alpha = 0.3f),
                start = Offset(offset.x + xSize / 2, offset.y - xSize / 2),
                end = Offset(offset.x - xSize / 2, offset.y + xSize / 2),
                strokeWidth = 4f
            )
        }
    }
}
