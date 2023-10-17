@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.angelaheely.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.angelaheely.presentation.login.MainViewModel
import com.example.angelaheely.utils.isFailureState
import com.example.angelaheely.utils.isLoadingState
import com.example.angelaheely.utils.isSuccessDataState

@Composable
fun HomeScreen(
    onClickMedicine: (medicineId: Int) -> Unit,
    viewModel: MainViewModel
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.getUser()
    }

    val user = viewModel.user
    val screenState = viewModel.screenState

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Angela Heely") },
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "Welcome " + user.value?.username.toString() + " " + viewModel.getCurrentTime())

            LazyColumn() {
                when {
                    isLoadingState(screenState) -> {
                        // Show a loading indicator
                        item {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            )
                        }
                    }

                    isFailureState(screenState) -> {
                        item {
                            // Show an error message
                            Text(
                                text = "Failed to load data",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                textAlign = TextAlign.Center,
                                color = Color.Red
                            )
                        }
                    }

                    isSuccessDataState(screenState) -> {
                        screenState.item?.problems?.let {

                            itemsIndexed(it) { index, item ->
                                Card(onClick = {
                                    onClickMedicine.invoke(item.describeContents())
                                }) {


                                }
                            }
                        }
                    }
                }
            }
        }
    }
}