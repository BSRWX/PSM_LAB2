package com.example.lab2

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.OutlinedTextFieldDefaults

@Composable
fun ConverterScreen(modifier: Modifier = Modifier) {
    var inputText by rememberSaveable { mutableStateOf("") }
    val metersValue = inputText.replace(",", ".").toDoubleOrNull()

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Length in metres", color = colorResource(R.color.black)) },
            supportingText = {
                Text(
                    text = "Type a value to see the conversions",
                    color = colorResource(R.color.white)
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF3EDF7),
                unfocusedContainerColor = Color(0xFFF3EDF7),
                focusedBorderColor = colorResource(R.color.accents),
                unfocusedBorderColor = colorResource(R.color.accents)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        LengthUnit.values().forEach { unit ->
            val resultText = if (metersValue != null) {
                formatResult(convertMetersTo(metersValue, unit))
            } else {
                "—"
            }

            UnitCard(unitName = unit.unitName, symbol = unit.symbol, result = resultText)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun UnitCard(unitName: String, symbol: String, result: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF3EDF7)
        ),
        border = BorderStroke(1.dp, colorResource(R.color.accents)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row {
                Text(text = symbol, color = colorResource(R.color.black))
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = unitName, style = MaterialTheme.typography.titleMedium)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = result,
                style = MaterialTheme.typography.headlineSmall,
                color = colorResource(R.color.black)
            )
        }
    }
}