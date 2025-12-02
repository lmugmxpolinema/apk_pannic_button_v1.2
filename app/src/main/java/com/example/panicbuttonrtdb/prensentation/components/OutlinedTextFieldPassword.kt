package com.example.panicbuttonrtdb.prensentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.panicbuttonrtdb.R
import com.example.panicbuttonrtdb.ui.theme.CoralPink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextFieldPassword(
    password: String,
    setPassword: (String) -> Unit
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = password,
        onValueChange = {setPassword(it)},
        label = { Text(text = "Sandi") },
        placeholder = { Text(text = "Sandi", color = Color(0xFF9E9E9E)) },
        visualTransformation =
        if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFFBA4661),
            focusedLabelColor = Color(0xFFBA4661),
            focusedLeadingIconColor = Color(0xFFBA4661),
            unfocusedBorderColor = Color(0xFFBA4661),
            unfocusedLabelColor = Color(0xFFBA4661),
            unfocusedLeadingIconColor = Color(0xFFBA4661),
            cursorColor = Color(0xFFBA4661)
        ),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_password),
                contentDescription = "password",
                modifier = Modifier.size(24.dp)
            )
        },
        trailingIcon = {
            val icon = if (passwordVisible) {
                painterResource(id = R.drawable.ic_hint_password)
            } else {
                painterResource(id = R.drawable.ic_hint_password)
            }

            IconButton(
                onClick = {
                    passwordVisible = !passwordVisible
                }
            ) {
                Icon(
                    painter = icon,
                    contentDescription = if (passwordVisible) "Hide Password" else "Show Password",
                    tint = if (passwordVisible) Color(0xFFBA4661) else colorResource(id = R.color.defauld)
                )
            }
        }
    )
}