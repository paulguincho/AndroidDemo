@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.clientsearchdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clientsearchdemo.feature.DefaultText
import com.example.clientsearchdemo.feature.TabContent
import com.example.clientsearchdemo.feature.Tabs
import com.example.clientsearchdemo.models.SearchResults
import com.example.clientsearchdemo.ui.theme.ClientSearchTheme
import com.example.clientsearchdemo.ui.theme.yellow
import com.gaur.tablayoutjetpackcompose.TabItem
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClientSearchTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    MainContent()
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainContent() {
    val list = listOf(TabItem.Srf, TabItem.Name)
    val pagerState = rememberPagerState(initialPage = 0)

    Column(
        modifier = Modifier
            .background(
                color = Color.Transparent
            ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start

    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Row {
            Spacer(modifier = Modifier.width(85.dp))
            DefaultText(
                "Client Search POC", 29.sp,
                yellow, FontFamily.Serif
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Tabs(tabs = list, pagerState = pagerState)
        TabContent(tabs = list, pagerState = pagerState)
    }

}



fun getSearchList(searchType: Int): HashMap<String, SearchResults> {
    val keyValues = HashMap<Int, Array<String>>()
    val values = arrayOf("3", "777777777", "888888888", "999999999")
    val values2 = arrayOf("3", "senna", "hamilton", "schumi")

    keyValues[1] = values
    keyValues[2] = values2

    val searchList = HashMap<String, SearchResults>()

    searchList[keyValues[searchType]?.get(1) ?: "na"] =
        SearchResults("Ayrton", "Senna", "777777777")
    searchList[keyValues[searchType]?.get(2) ?: "na"] =
        SearchResults("Lewis", "Hamilton", "888888888")
    searchList[keyValues[searchType]?.get(3) ?: "na"] =
        SearchResults("Michael", "Schumacher", "999999999")

    return searchList
}