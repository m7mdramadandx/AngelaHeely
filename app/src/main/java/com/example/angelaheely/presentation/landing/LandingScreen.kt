package com.example.angelaheely.presentation.landing

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.angelaheely.R
import com.example.angelaheely.presentation.login.MainViewModel
import kotlinx.coroutines.delay

@Composable
fun LandingScreen(
    navigateToLoginScreen: () -> Unit,
    navigateToHomeScreen: () -> Unit,
    viewModel: MainViewModel
) {

    LaunchedEffect(key1 = Unit) {
        delay(1500)
        if (viewModel.user.value == null) {
            navigateToLoginScreen.invoke()
        } else {
            navigateToHomeScreen.invoke()
        }
    }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Landing Page Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Welcome to Our App!",
            style = MaterialTheme.typography.h4,
            color = Color.White
        )

    }

}