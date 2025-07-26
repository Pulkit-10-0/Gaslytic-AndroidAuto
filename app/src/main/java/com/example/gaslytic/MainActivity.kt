package com.example.gaslytic

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.LocalGasStation
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gaslytic.ui.theme.GaslyticTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GaslyticTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FuelCostCalculator()
                }
            }
        }
    }
}

@Composable
fun FuelCostCalculator() {
    val context = LocalContext.current

    var distanceInput by remember { mutableStateOf("") }
    var mileageInput by remember { mutableStateOf("") }
    var fuelPriceInput by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("⛽ Gaslytic", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Text("Fuel Cost Estimator", fontSize = 16.sp, color = MaterialTheme.colorScheme.primary)

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = distanceInput,
            onValueChange = { distanceInput = it },
            label = { Text("Distance (km)") },
            leadingIcon = { Icon(Icons.Filled.DirectionsCar, contentDescription = null) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = mileageInput,
            onValueChange = { mileageInput = it },
            label = { Text("Mileage (km/l)") },
            leadingIcon = { Icon(Icons.Filled.LocalGasStation, contentDescription = null) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = fuelPriceInput,
            onValueChange = { fuelPriceInput = it },
            label = { Text("Fuel Price (₹/L)") },
            leadingIcon = { Icon(Icons.Filled.AttachMoney, contentDescription = null) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                val distance = distanceInput.toDoubleOrNull()
                val mileage = mileageInput.toDoubleOrNull()
                val price = fuelPriceInput.toDoubleOrNull()

                if (distance != null && mileage != null && price != null && mileage != 0.0) {
                    val cost = (distance / mileage) * price
                    result = "Estimated Fuel Cost: ₹%.2f".format(cost)
                } else {
                    result = null
                    Toast.makeText(context, "Please enter valid numbers", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calculate")
        }

        result?.let {
            Spacer(modifier = Modifier.height(24.dp))
            Text(it, fontSize = 20.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFuelCostCalculator() {
    GaslyticTheme {
        FuelCostCalculator()
    }
}
