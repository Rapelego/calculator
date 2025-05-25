package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    CalculatorUI()
                }
            }
        }
    }
}

@Composable
fun CalculatorUI() {
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("Result: ") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Simple Calculator", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = num1,
            onValueChange = { num1 = it },
            label = { Text("Enter first number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = num2,
            onValueChange = { num2 = it },
            label = { Text("Enter second number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = {
                result = "Result: ${Add(num1.toIntOrNull() ?: 0, num2.toIntOrNull() ?: 0)}"
            }) {
                Text("+")
            }

            Button(onClick = {
                result = "Result: ${Subtract(num1.toIntOrNull() ?: 0, num2.toIntOrNull() ?: 0)}"
            }) {
                Text("-")
            }

            Button(onClick = {
                result = "Result: ${Multiply(num1.toIntOrNull() ?: 0, num2.toIntOrNull() ?: 0)}"
            }) {
                Text("×")
            }

            Button(onClick = {
                val second = num2.toIntOrNull()
                result = if (second == 0 || second == null) {
                    "Cannot divide by zero"
                } else {
                    "Result: ${Divide(num1.toIntOrNull() ?: 0, second)}"
                }
            }) {
                Text("÷")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = result, style = MaterialTheme.typography.bodyLarge)
    }
}

// Operator functions
fun Add(a: Int, b: Int): Int = a + b
fun Subtract(a: Int, b: Int): Int = a - b
fun Multiply(a: Int, b: Int): Int = a * b
fun Divide(a: Int, b: Int): Int = a / b
