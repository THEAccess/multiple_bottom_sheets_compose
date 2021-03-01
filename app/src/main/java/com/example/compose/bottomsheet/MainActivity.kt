package com.example.compose.bottomsheet

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
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

        val (selected, setSelected) = remember(calculation = { mutableStateOf(0) })

        ModalBottomSheetLayout(sheetState = sheetState, sheetContent = {
            when (selected) {
                0 -> Layout1()
                1 -> Layout2()
            }
        }) {
            Content(sheetState = sheetState, setSelected = setSelected)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun Content(sheetState: ModalBottomSheetState, setSelected: (Int) -> Unit) {
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            setSelected(0)
            scope.launch {
                sheetState.show()
            }
        }) {
            Text(text = "Show First")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            setSelected(1)
            scope.launch {
                sheetState.show()
            }
        }) {
            Text(text = "Show Second")
        }
    }
}

@Composable
private fun Layout2() {
    LazyColumn(Modifier.fillMaxWidth()) {
        items(50) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "BottomSheetLayout:$it"
            )
        }
    }
}

@Composable
private fun Layout1() {
    Text(
        modifier = Modifier.padding(16.dp),
        text = "BottomSheetLayout 1"
    )
}

