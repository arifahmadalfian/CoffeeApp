package com.arifahmadalfian.coffeeapp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.arifahmadalfian.coffeeapp.R

@Composable
fun StatefulConvertInput(
    modifier: Modifier = Modifier,
) {
    var input by remember { mutableStateOf("") }
    var output by remember { mutableStateOf("")}

    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.stateful_converter),
            style = MaterialTheme.typography.h5
        )
        OutlinedTextField(
            value = input,
            label = { Text(text = stringResource(id = R.string.enter_celsius)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange =  {
                input = it
                output = convertToFahrenheit(it)
            }
        )
        Text(
            text = stringResource(id = R.string.temperature_fahrenheit, output)
        )
    }
}

@Composable
fun ConverterApp(
    modifier: Modifier = Modifier,
) {
    var input by remember { mutableStateOf("") }
    var output by remember { mutableStateOf("") }

    StatelessTemperatureInput(
        input = input,
        output = output,
        onValueChange = { newInput ->
            input = newInput
            output = convertToFahrenheit(newInput)
        },
        modifier = modifier
    )
}

@Composable
fun StatelessTemperatureInput(
    input: String,
    output: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.stateful_converter),
            style = MaterialTheme.typography.h5
        )
        OutlinedTextField(
            value = input,
            label = { Text(text = stringResource(id = R.string.enter_celsius)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = onValueChange
        )
        Text(
            text = stringResource(id = R.string.temperature_fahrenheit, output)
        )
    }
}

@Composable
fun TwoWayConverterApp(
    modifier: Modifier = Modifier,
) {
    var celsius by remember { mutableStateOf("") }
    var fahrenheit by remember { mutableStateOf("") }

    Column (modifier.padding(16.dp)){
        Text(
            text = stringResource(R.string.two_way_converter),
            style = MaterialTheme.typography.h5
        )
        GeneralTemperatureInput(
            scale = Scale.CELSIUS,
            input = celsius,
            onValueChange = { newInput ->
                celsius = newInput
                fahrenheit = convertToFahrenheit(newInput)
            }
        )
        GeneralTemperatureInput(
            scale = Scale.FAHRENHEIT,
            input = fahrenheit,
            onValueChange = { newInput ->
                fahrenheit = newInput
                celsius = convertToCelsius(newInput)
            }
        )
    }
}

private fun convertToCelsius(fahrenheit: String) =
    fahrenheit.toDoubleOrNull()?.let {
        (it - 32) * 5 / 9
    }.toString()

@Composable
fun GeneralTemperatureInput(
    scale: Scale,
    input: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        OutlinedTextField(
            value = input,
            label = { Text(stringResource(R.string.enter_temperature, scale.scaleName)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = onValueChange,
        )
    }
}

enum class Scale(val scaleName: String) {
    CELSIUS("Celsius"),
    FAHRENHEIT("Fahrenheit")
}

private fun convertToFahrenheit(celsius: String) =
    celsius.toDoubleOrNull()?.let {
        (it * 9 / 5) + 32
    }.toString()