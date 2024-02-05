package com.android.unitconverter.compose.converter

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.unitconverter.data.Conversion

@Composable
fun InputBlock(
    conversion: Conversion,
    inputText: MutableState<String>,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    calculate : (String) -> Unit

) {
    // Column to arrange its children vertically with top padding
    Column(modifier = modifier.padding(0.dp, 20.dp, 0.dp, 0.dp)) {

        // Row to arrange its children horizontally, filling the maximum width
        Row(modifier = modifier.fillMaxWidth()) {

            // Define a color with alpha for TextField container
           // val containerColor = Color.Black
            val containerColor = Color(200, 50, 143, 28)
            //val containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9F)

            // TextField for user input
            TextField(
                value = inputText.value,
                onValueChange = {
                    inputText.value = it
                },
                // Set modifier to fill 50% of the available width
                modifier = modifier.fillMaxWidth(0.5F),
                keyboardOptions = KeyboardOptions(
                    KeyboardCapitalization.None,
                    autoCorrect = true,
                    keyboardType = KeyboardType.Number
                ),
                // Customize colors based on focus
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = containerColor,
                    unfocusedContainerColor = containerColor,
                    disabledContainerColor = containerColor,
                ),
                // Style for the text inside the TextField
                textStyle = TextStyle(
                    color = Color.DarkGray,
                    fontSize = 30.sp
                )
            )

            // Text displaying conversion information
            Text(
                text = conversion.convertFrom,
                fontSize = 24.sp,
                // Set padding and fill 35% of the available width
                modifier = Modifier
                    .padding(10.dp, 30.dp, 0.dp, 0.dp)
                    .fillMaxWidth(0.35f)
            )
            Spacer(modifier= modifier.height(20.dp))
            OutlinedButton(
                onClick = {
                    if (inputText.value != "") {
                        calculate(inputText.value)
                    } else {
                        Toast.makeText(context, "Please, enter your value", Toast.LENGTH_LONG).show()

                    }
                },
                modifier = modifier.fillMaxWidth(1F)
            ){
                Text(
                    text = "Convert",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                    )

            }
        }
    }
}

/*
@Composable
fun InputBlock(
    conversion: Conversion,
    inputText: MutableState<String>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(0.dp, 20.dp, 0.dp, 0.dp)) {
        Box(modifier = Modifier.fillMaxWidth()) {
            val containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.3F)
            TextField(
                value = inputText.value,
                onValueChange = {
                    inputText.value = it
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    KeyboardCapitalization.None,
                    autoCorrect = true,
                    keyboardType = KeyboardType.Number
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = containerColor,
                    unfocusedContainerColor = containerColor,
                    disabledContainerColor = containerColor,
                ),
                textStyle = TextStyle(
                    color = Color.DarkGray,
                    fontSize = 30.sp
                )
            )
        }
        Text(
            text = conversion.convertFrom,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(10.dp, 30.dp, 0.dp, 0.dp)
                .fillMaxWidth(0.35f)
        )
    }
}*/

