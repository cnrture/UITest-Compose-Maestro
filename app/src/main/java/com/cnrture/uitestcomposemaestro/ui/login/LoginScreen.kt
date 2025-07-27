package com.cnrture.uitestcomposemaestro.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun LoginScreen(
    onNavigateHome: () -> Unit,
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.testTag(LoginComponentKey.WELCOME_TEXT),
            text = "Welcome",
            fontSize = 32.sp,
        )
        OutlinedTextField(
            modifier = Modifier.testTag(LoginComponentKey.USERNAME_FIELD),
            value = username,
            onValueChange = { username = it },
            singleLine = true,
            label = { Text("Username") },
        )
        OutlinedTextField(
            modifier = Modifier.testTag(LoginComponentKey.PASSWORD_FIELD),
            value = password,
            onValueChange = { password = it },
            singleLine = true,
            label = { Text("Password") },
        )
        Button(
            modifier = Modifier.testTag(LoginComponentKey.LOGIN_BUTTON),
            onClick = {
                if (isValidCredentials(username, password)) {
                    onNavigateHome()
                } else {
                    showDialog = true
                }
            },
            enabled = username.isNotEmpty() && password.isNotEmpty(),
        ) {
            Text("Login")
        }
    }

    if (showDialog) {
        Dialog(
            onDismissRequest = { showDialog = false },
            content = {
                Column(
                    modifier = Modifier
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        modifier = Modifier.testTag(LoginComponentKey.ERROR_DIALOG_TEXT),
                        text = "Please enter valid credentials.",
                    )
                    Button(
                        onClick = { showDialog = false },
                    ) {
                        Text("Okay")
                    }
                }
            }
        )
    }
}

private fun isValidCredentials(username: String, password: String): Boolean {
    return username == "test" && password == "password"
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        onNavigateHome = {},
    )
}