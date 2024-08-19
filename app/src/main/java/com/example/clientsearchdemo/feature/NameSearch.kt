package com.example.clientsearchdemo.feature

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
fun NameSearch() {
    var inputFname by remember { mutableStateOf("") }
    var inputLname by remember { mutableStateOf("") }
    var inputDob by remember { mutableStateOf("") }
    var outputValueFname by remember { mutableStateOf("") }
    var outputValueLname by remember { mutableStateOf("") }
    var outputValueSrf by remember { mutableStateOf("") }
    var outlineColor by remember { mutableStateOf(Color.Blue) }
    var isSearch by remember { mutableStateOf(false) }
    val searchList2 = getSearchList(2)

    var errorText = ""
    var isError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(50.dp)) //space after heading
        DefaultText("Name Search", null, null, FontFamily.Serif)
        Spacer(modifier = Modifier.height(30.dp)) //space after heading

        OutlinedTextField(value = inputFname,
            modifier = Modifier.width(300.dp),
            onValueChange = {
            outlineColor = Color.Blue
            inputFname = it
            errorText = if (inputFname.isEmpty()) "Field cannot be empty" else ""
            isError = it.isEmpty()
        }, label = { Text("Enter First Name") }, isError = errorText.isNotEmpty(),

            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = outlineColor,
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

        Log.i("inputLname::: ", inputLname)

        OutlinedTextField(value = inputLname,
            modifier = Modifier.width(300.dp),
            onValueChange = {
                outlineColor = Color.Blue
                inputLname = it
                errorText = if (inputLname.isEmpty()) "Field cannot be empty" else ""
                Log.i("OnValueChange::: ", it)
                Log.i("inputLname::: ", inputLname)
                Log.i("errorText::: ", errorText)
                isError = it.isEmpty()
            },
            label = { Text("Enter Last Name") },
            isError = errorText.isNotEmpty(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = outlineColor,
                errorBorderColor = Color.Red
            ),
            trailingIcon = {
                if (isError) {
                    outlineColor = Color.Red
                    Icon(
                        contentDescription = "Error",
                        imageVector = Icons.Default.Warning,
                        tint = Color.Red,
                    )
                }
            })

        Spacer(modifier = Modifier.height(10.dp)) //space after heading

        Row(
            modifier = Modifier.height(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            OutlinedTextField(
                value = inputDob,
                modifier = Modifier.width(165.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    outlineColor = Color.Blue
                    inputDob = it
                    errorText = if (inputDob.isEmpty()) "Field cannot be empty" else ""
                    Log.i("OnValueChange::: ", it)
                    Log.i("errorText::: ", errorText)
                    isError = it.isEmpty()
                },
                label = { Text("DOB") },
                isError = errorText.isNotEmpty(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = outlineColor,
                    errorBorderColor = Color.Red
                ),
                trailingIcon = {
                    if (isError) {
                        outlineColor = Color.Red
                        Icon(
                            contentDescription = "Error",
                            imageVector = Icons.Default.Warning,
                            tint = Color.Red,
                        )
                    }
                })

            Spacer(modifier = Modifier.width(10.dp))
        }

        Spacer(modifier = Modifier.height(30.dp)) //space after heading
        Row {
            Box {
                Button(
                    onClick = {

                        Log.i("CLICKED", "")
                        if (inputFname.isNotEmpty() && inputLname.isNotEmpty() && inputDob.isNotEmpty()) {

                            Log.i("searchList2::$searchList2", "")
                            var resp = searchList2.get(inputLname.lowercase())
                            isSearch = true;

                            if (resp != null && resp.getfName().isNotEmpty()) {
                                isError = false;
                                outputValueFname = resp.getfName()
                                outputValueLname = resp.getlName()
                                outputValueSrf = resp.srf
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

        Spacer(modifier = Modifier.height(30.dp))

        if (!isError) {
            ResponseTable(outputValueFname, outputValueLname, outputValueSrf, isSearch)
        }
    }
}