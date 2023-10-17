@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.angelaheely.presentation.home

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.angelaheely.presentation.login.MainViewModel
import com.example.angelaheely.utils.isFailureState
import com.example.angelaheely.utils.isLoadingState
import com.example.angelaheely.utils.isSuccessDataState

@Composable
fun HomeScreen(
    onClickMedicine: (medicineName: String) -> Unit,
    viewModel: MainViewModel
) {


    val user = viewModel.user
    val screenState = viewModel.screenState

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Angela Heely") },
            )
        }
    ) { padding ->
        Column {


            Text(
                text = "Welcome " + user.value?.username.toString() + "\nTime:" + viewModel.getCurrentTime(),
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
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
                                text = screenState.error,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                textAlign = TextAlign.Center,
                                color = Color.Red
                            )
                        }
                    }

                    isSuccessDataState(screenState) -> {
                        screenState.item?.drugs?.let {

                            itemsIndexed(it) { index, item ->
                                Card(
                                    modifier = Modifier
                                        .padding(vertical = 8.dp, horizontal = 16.dp)
                                        .fillMaxWidth(),
                                    onClick = {
                                        item.name?.let { it1 ->
                                            onClickMedicine.invoke(it1)
                                        }
                                    }) {

                                    item.name?.let { it1 ->
                                        Text(
                                            text = "Drug Name:" + it1,
                                            modifier = Modifier.padding(8.dp)
                                        )
                                    }

                                    item.dose?.let { it1 ->
                                        Text(
                                            text = "Drug dose:" + it1,
                                            modifier = Modifier.padding(8.dp)
                                        )
                                    }

                                    item.strength?.let { it1 ->
                                        Text(
                                            text = "Drug strength:" + it1,
                                            modifier = Modifier.padding(8.dp)
                                        )
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}