package com.example.samplemvvmpost

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.samplemvvmpost.ui.theme.SampleMVVMPostTheme
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ItemListScreen(itemViewModel: ItemViewModel = viewModel()) {
    var newItem by remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Item List", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        // Display the list of items
        Column {
            itemViewModel.getItems().forEach { item ->
                Text(text = item, fontSize = 18.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Add a new item
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = newItem,
                onValueChange = {
                    newItem = it
                },
                textStyle = TextStyle(fontSize = 18.sp),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp)
            )

            Button(
                onClick = {
                    itemViewModel.addItem(newItem.text)
                    newItem = TextFieldValue()
                }
            ) {
                Text(text = "Add")
            }
        }
    }
}



class ItemViewModel : ViewModel() {
    private val itemList = mutableListOf<String>()

    fun getItems(): List<String> {
        return itemList
    }

    fun addItem(item: String) {
        itemList.add(item)
    }
}




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleMVVMPostTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ItemListScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SampleMVVMPostTheme {
        Greeting("Android")
    }
}