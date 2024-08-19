package com.gaur.tablayoutjetpackcompose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.clientsearchdemo.feature.NameSearch
import com.example.clientsearchdemo.feature.SrfSearch

typealias ComposableFun = @Composable ()->Unit
sealed class TabItem(val title:String,val icons:ImageVector, val screens:ComposableFun) {
    object Srf : TabItem(title = "SRF Search",icons= Icons.Default.Search, screens = { SrfSearch() })
    object Name: TabItem(title = "Name Search",icons = Icons.Default.Person, screens={ NameSearch() })

}