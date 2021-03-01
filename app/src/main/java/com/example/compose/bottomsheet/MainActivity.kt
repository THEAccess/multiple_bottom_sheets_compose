package com.example.compose.bottomsheet

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.compose.bottomsheet.ui.theme.AppTheme
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            App()

        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable()
fun App() {
    AppTheme {
        val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
        val scope = rememberCoroutineScope()
        var selected by remember(calculation = { mutableStateOf(0) })

        ModalBottomSheetLayout(sheetState = sheetState, sheetContent = {
            when (selected) {
                0 -> Layout1()
                1 -> Layout2()
            }
        }) {

            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
                Button(onClick = {
                    selected = 0
                    scope.launch {
                        sheetState.show()
                    }


                }) {
                    Text(text = "Show First")
                }
                Button(onClick = {
                    selected = 1
                    scope.launch {
                        sheetState.show()
                    }
                }) {
                    Text(text = "Show Second")
                }
            }
        }
    }
}

@Composable
private fun Layout2() {
    Text(
        modifier = Modifier.padding(16.dp),
        text = "BottomSheetLayout 2"
    )
}

@Composable
private fun Layout1() {
    Text(
        modifier = Modifier.padding(16.dp),
        text = "BottomSheetLayout 1"
    )
}

