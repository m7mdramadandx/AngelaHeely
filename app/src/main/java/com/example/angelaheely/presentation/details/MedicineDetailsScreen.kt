package com.example.angelaheely.presentation.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.angelaheely.domain.model.Drug
import com.example.angelaheely.presentation.login.MainViewModel

@Composable
fun MedicineDetailsScreen(
    onClickBack: () -> Unit,
    viewModel: MainViewModel,
    medicineNam: String,
) {

    var drug by remember {
        mutableStateOf(Drug())
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.screenState.item?.drugs?.find { it.name == medicineNam }?.let {
            drug = it
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Medicine Details") },
                navigationIcon = {
                    IconButton(onClick = { onClickBack.invoke() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "Name: ${drug.name}", style = MaterialTheme.typography.h4)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Dose: ${drug.dose}", style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Strength: ${drug.strength}", style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Description: {medicine.description}",
                style = MaterialTheme.typography.body1
            )
        }
    }
}