package com.codeofmario.blogapp.ui.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.codeofmario.blogapp.R
import com.codeofmario.blogapp.Screen
import com.codeofmario.blogapp.Tab
import com.codeofmario.blogapp.extension.toast
import com.codeofmario.blogapp.ui.theme.BlogAppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    vm: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val login by vm.login.observeAsState(vm.initLogin())

    val passwordVisibility = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = stringResource(R.string.auth_login), style = MaterialTheme.typography.headlineSmall)

        Spacer(Modifier.size(16.dp))

        OutlinedTextField(
            value = login.username,
            onValueChange = { vm.onUsernameChanged(it) },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = stringResource(R.string.auth_label_username))
            },
        )

        Spacer(Modifier.size(16.dp))

        OutlinedTextField(
            value = login.password,
            onValueChange = { vm.onPasswordChanged(it) },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = stringResource(R.string.auth_label_password))
            },
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisibility.value = !passwordVisibility.value
                }) {
                    Icon(
                        imageVector = if (passwordVisibility.value) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = "Visibility",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            },
            visualTransformation = if (!passwordVisibility.value) PasswordVisualTransformation() else VisualTransformation.None
        )

        Spacer(Modifier.size(16.dp))

        Button(
            onClick = {
                scope.launch {
                    if (vm.login()) {
                        navController.navigate(Screen.Tabs.getRoute(Tab.Posts)) {
                            popUpTo(Screen.Login.route) {
                                inclusive = true
                            }
                        }
                    } else {
                        context.toast(R.string.auth_msg_login_failed)
                    }
                }
            },
            content = {
                Text(stringResource(R.string.auth_login), color = Color.White)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()

    BlogAppTheme {
        LoginScreen(navController)
    }
}