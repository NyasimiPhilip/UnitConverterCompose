package com.android.unitconverter.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.android.unitconverter.data.Conversion

// Composable function for a Conversion Menu
@Composable
fun ConversionMenu(list: List<Conversion>, modifier: Modifier = Modifier, convert: (Conversion)-> Unit) {

    // State variables to manage the display text, text field size, and dropdown expansion
    var displayText by remember { mutableStateOf("Select the conversion type") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    var expanded by remember { mutableStateOf(false) }

    // Determine the icon based on the dropdown state
    val icon = if (expanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }
    Column{

    // OutlinedTextField for displaying the selected conversion type
    OutlinedTextField(
        value = displayText,
        onValueChange = {
            displayText = it
        },
        textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
        modifier = modifier
            .fillMaxWidth()
            .onGloballyPositioned { coordinates ->
                textFieldSize = coordinates.size.toSize()
            },
        label = {
            Text(text = "Conversion type")
        },
        trailingIcon = {
            // Clickable icon to toggle dropdown expansion
            Icon(icon, contentDescription = "icon", modifier = Modifier.clickable { expanded = !expanded })
        },
        readOnly = true
    )

    // DropdownMenu displaying the available conversion options
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = modifier.width(with(LocalDensity.current) {
            textFieldSize.width.toDp()
        })
    ) {
        // Create a dropdown item for each conversion option
        list.forEach { conversion ->
            DropdownMenuItem(
                {
                    // Display the conversion description as bold text
                    Text(
                        text = conversion.description,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                onClick = {
                    // Set the selected conversion and close the dropdown
                    displayText = conversion.description
                    expanded = false
                    convert(conversion)
                }
            )
        }
    }
    }
}
