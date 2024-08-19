package com.example.clientsearchdemo.feature

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.clientsearchdemo.ui.theme.lightBlue
import com.example.clientsearchdemo.ui.theme.yellow

@Composable
fun ResponseTable(
    outputValueFname: String,
    outputValueLname: String,
    outputValueSrf: String,
    searchFlag: Boolean) {

    if (searchFlag) {
        if (outputValueFname.isNotEmpty()) {

            val tableData = (1..1).mapIndexed { index, item ->
                index to "Item $index"
            }
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .padding(30.dp)
                    .background(color = lightBlue),


            ) {
                // Header row
                item {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colors.primary)
                            .padding(start = 5.dp)
                    ) {
                        Text(
                            text = "First Name", modifier = Modifier
                                .width(110.dp)
                                //  .border(1.dp, Color.White)
                                .padding(8.dp), color = Color.White
                        )
                        Text(
                            text = "Last Name", modifier = Modifier
                                .width(110.dp)
                                // .border(1.dp, Color.White)
                                .padding(8.dp), color = Color.White
                        )
                        Text(
                            text = "SRF", modifier = Modifier
                                .width(110.dp)
                                //.border(1.dp, Color.White)
                                .padding(8.dp), color = Color.White
                        )
                    }
                }

                // Data rows
                items(tableData) { (id, text) ->
                    Row(Modifier.fillMaxWidth()) {
                        TableCell(outputValueFname)
                        TableCell(outputValueLname)
                        TableCell(outputValueSrf)
                    }
                }

            }
        } else {
            Row(
                modifier = Modifier.height(60.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    // painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "Not Found",
                    imageVector = Icons.Default.Info,
                    tint = yellow
                    )
                TableCell("Not found")
            }

        }




    } else {
        Text("")
    }


}

@Composable
fun TableCell(
    text: String,
) {
    Text(
        text = text,
        Modifier
            .width(110.dp)
            .padding(6.dp)
    )
}

