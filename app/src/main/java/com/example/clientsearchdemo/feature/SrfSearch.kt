package com.example.clientsearchdemo.feature

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.clientsearchdemo.getSearchList

@Composable
fun SrfSearch() {
    var inputValue by remember { mutableStateOf("") }
    var outputValueFname by remember { mutableStateOf("") }
    var outputValueLname by remember { mutableStateOf("") }
    var outputValueSrf by remember { mutableStateOf("") }
    var outlineColor by remember { mutableStateOf(Color.Blue) }

    val searchList = getSearchList(1)
    var isSearch by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Spacer(modifier = Modifier.height(50.dp)) //space after heading
        DefaultText("SRF Search", null, null, FontFamily.Serif)
        Spacer(modifier = Modifier.height(30.dp)) //space after heading

        var errorText = ""

        var isError by remember { mutableStateOf(false) }

        OutlinedTextField(value = inputValue,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

            onValueChange = {
                outlineColor = Color.Blue
                inputValue = it
                errorText = if (inputValue.isEmpty()) "Field cannot be empty" else ""
                Log.i("OnValueChange::: ", it)
                Log.i("errorText::: ", errorText)
                isError = it.isEmpty()
            },
            label = { Text("Enter SRF") },
            isError = errorText.isNotEmpty(),

            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = outlineColor,
                //   unfocusedBorderColor = Color.Yellow,
                errorBorderColor = Color.Red
            ),

            trailingIcon = {
                if (isError) {
                    outlineColor = Color.Red
                    Icon(
                        // painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "Error",
                        imageVector = Icons.Default.Warning,
                        tint = Color.Red,
                        )
                }
            })

        // Hoist the MutableInteractionSource that we will provide to interactions
        val interactionSource = remember { MutableInteractionSource() }
        Spacer(modifier = Modifier.height(30.dp)) //space after heading
        Row {
            Box {
                //  var default = "Search"
                Button(
                    onClick = {
                        if (inputValue.isNotEmpty()) {
                            var resp = searchList.get(inputValue)

                            isSearch = true

                            if (resp != null && resp.getfName().isNotEmpty()) {
                                isError = false;
                                outputValueFname = resp.getfName()
                                outputValueLname = resp.getlName()
                                outputValueSrf = inputValue
                            } else {
                                outputValueFname = ""
                                outputValueLname = ""
                                outputValueSrf = ""
                            }
                        } else {
                            isError = true;
                            isSearch = false;
                        }
                    },
                    colors = ButtonDefaults.outlinedButtonColors(containerColor = MaterialTheme.colors.primary)
                ) {
                    Text(
                        text = "Search",
                        modifier = Modifier.padding(10.dp),
                        color = Color.White, fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(30.dp)) //space after heading

        if (!isError) {
            ResponseTable(outputValueFname, outputValueLname, outputValueSrf, isSearch)
        }
    }
}
