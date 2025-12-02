package com.example.panicbuttonrtdb.prensentation.screens

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.panicbuttonrtdb.R
import com.example.panicbuttonrtdb.prensentation.components.OutlinedTextFieldPassword
import com.example.panicbuttonrtdb.viewmodel.ViewModel
import com.example.panicbuttonrtdb.viewmodel.ViewModelFactory
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.ui.unit.dp
import com.example.panicbuttonrtdb.data.FirebaseRepository
import com.example.panicbuttonrtdb.data.Perumahan
import com.example.panicbuttonrtdb.data.UserPreferences
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import com.example.panicbuttonrtdb.ui.theme.CoralPink
import com.example.panicbuttonrtdb.ui.theme.SoftPink
import com.example.panicbuttonrtdb.ui.theme.TextDark

@Composable
fun LoginScreen(
    context: Context,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: ViewModel = viewModel(factory = ViewModelFactory(LocalContext.current))
) {

    BackHandler {
        (context as? Activity)?.finish()
    }
    var houseNumber by remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }  // Indikator loading
    var rememberMe by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(CoralPink, SoftPink)
                )
            )
    ) {
        // Batik Pattern Background (More Visible)
        Image(
            painter = painterResource(id = R.drawable.batik_pattern),
            contentDescription = "Batik Pattern",
            modifier = Modifier.fillMaxSize(),
            contentScale = androidx.compose.ui.layout.ContentScale.Crop,
            alpha = 0.7f
        )
        
        // Decorative Pattern
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height
            
            // Draw wavy lines
            val wavePath = Path().apply {
                moveTo(0f, height * 0.15f)
                cubicTo(
                    width * 0.25f, height * 0.1f,
                    width * 0.75f, height * 0.2f,
                    width, height * 0.15f
                )
            }
            
            drawPath(
                path = wavePath,
                color = Color.White.copy(alpha = 0.1f),
                style = Stroke(width = 2f)
            )

            // Draw circles
            val circlePositions = listOf(
                Offset(width * 0.15f, height * 0.1f) to 30f,
                Offset(width * 0.85f, height * 0.08f) to 40f,
                Offset(width * 0.75f, height * 0.25f) to 35f
            )

            circlePositions.forEach { (offset, radius) ->
                drawCircle(
                    color = Color.White.copy(alpha = 0.1f),
                    radius = radius,
                    center = offset,
                    style = Stroke(width = 2f)
                )
            }

            // Draw X patterns
            val xSize = 25f
            val xPositions = listOf(
                Offset(width * 0.3f, height * 0.2f),
                Offset(width * 0.9f, height * 0.18f)
            )

            xPositions.forEach { offset ->
                drawLine(
                    color = Color.White.copy(alpha = 0.15f),
                    start = Offset(offset.x - xSize / 2, offset.y - xSize / 2),
                    end = Offset(offset.x + xSize / 2, offset.y + xSize / 2),
                    strokeWidth = 3f
                )
                drawLine(
                    color = Color.White.copy(alpha = 0.15f),
                    start = Offset(offset.x + xSize / 2, offset.y - xSize / 2),
                    end = Offset(offset.x - xSize / 2, offset.y + xSize / 2),
                    strokeWidth = 3f
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Top Section with Logo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.35f),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = "Logo",
                    modifier = Modifier.size(140.dp)
                )
            }

            // White Content Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.65f)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 28.dp, vertical = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Login",
                        fontSize = 38.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFBA4661)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // --- DROPDOWN PERUMAHAN ---
                    val repo = remember { FirebaseRepository() }

                    var perumahanList by remember { mutableStateOf<List<Perumahan>>(emptyList()) }
                    var expanded by remember { mutableStateOf(false) }
                    var selectedName by remember { mutableStateOf("Pilih Perumahan") }
                    var selectedPerumahan by remember { mutableStateOf<Perumahan?>(null) }

                    // Load data perumahan
                    LaunchedEffect(Unit) {
                        repo.fetchPerumahanList(
                            onResult = { list ->
                                perumahanList = list
                            },
                            onError = {
                                // optional handle error
                            }
                        )
                    }

                    // Dropdown Perumahan
                    Box(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            value = selectedName,
                            onValueChange = { },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { expanded = true },
                            enabled = false,
                            label = { Text("Perumahan") },
                            placeholder = { Text(text = "Pilih Perumahan", color = Color(0xFFBA4661)) },
                            singleLine = true,
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_home),
                                    contentDescription = "home",
                                    tint = Color(0xFFBA4661)
                                )
                            },
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = "dropdown",
                                    tint = Color(0xFFBA4661)
                                )
                            },
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                disabledTextColor = Color(0xFFBA4661),
                                disabledContainerColor = Color.White,
                                disabledLabelColor = Color(0xFFBA4661),
                                disabledLeadingIconColor = Color(0xFFBA4661),
                                disabledTrailingIconColor = Color(0xFFBA4661),
                                disabledBorderColor = Color(0xFFBA4661)
                            )
                        )

                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White),
                            offset = androidx.compose.ui.unit.DpOffset(0.dp, 8.dp)
                        ) {
                            perumahanList.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(item.nama) },
                                    onClick = {
                                        selectedPerumahan = item
                                        selectedName = item.nama
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    OutlinedTextField(
                        value = houseNumber,
                        onValueChange = {houseNumber = it},
                        label = { Text(text = "Nomor Rumah") },
                        placeholder = { Text(text = "Nomor Rumah", color = Color(0xFF9E9E9E)) },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_home),
                                contentDescription = "ic home",
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFBA4661),
                            focusedLabelColor = Color(0xFFBA4661),
                            focusedLeadingIconColor = Color(0xFFBA4661),
                            unfocusedBorderColor = Color(0xFFBA4661),
                            unfocusedLeadingIconColor = Color(0xFFBA4661),
                            unfocusedLabelColor = Color(0xFFBA4661),
                            cursorColor = Color(0xFFBA4661)
                        )
                    )
                    
                    Spacer(modifier = Modifier.height(18.dp))

                    OutlinedTextFieldPassword(password, setPassword)

                    Spacer(modifier = Modifier.height(18.dp))
                    
                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            if (selectedPerumahan == null) {
                                Toast.makeText(context, "Pilih perumahan terlebih dahulu", Toast.LENGTH_SHORT).show()
                                return@Button
                            }

                            if (houseNumber.isNotEmpty() && password.isNotEmpty()) {

                                // Simpan perumahan
                                UserPreferences(context).savePerumahan(
                                    selectedPerumahan!!.id,
                                    selectedPerumahan!!.nama
                                )

                                isLoading = true

                                viewModel.validateLogin(houseNumber, password) { success, isAdmin ->
                                    isLoading = false
                                    if (success) {
                                        if (isAdmin) {
                                            navController.navigate("dashboard_admin")
                                        } else {
                                            navController.navigate("dashboard")
                                        }
                                    } else {
                                        Toast.makeText(context, "Nomor rumah atau Sandi salah", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            } else {
                                Toast.makeText(context,"Mohon isi semua kolom", Toast.LENGTH_SHORT).show()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        enabled = !isLoading,
                        shape = RoundedCornerShape(28.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFBA4661),
                            contentColor = Color.White,
                            disabledContainerColor = Color(0xFFBA4661).copy(alpha = 0.6f)
                        )
                    ) {
                        Text(
                            text = "Login",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Belum memiliki akun? ",
                            fontSize = 15.sp,
                            color = Color(0xFF757575)
                        )
                        Text(
                            modifier = Modifier
                                .clickable { navController.navigate("signup") },
                            text = "Daftar",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFBA4661)
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}
