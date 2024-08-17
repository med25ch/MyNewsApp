package com.example.mynewsapp.screens.discoverScreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.MonitorHeart
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material.icons.outlined.Casino
import androidx.compose.material.icons.outlined.Devices
import androidx.compose.material.icons.outlined.MonitorHeart
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.Science
import androidx.compose.material.icons.outlined.SportsSoccer
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.mynewsapp.repositories.CATEGORY

data class TabItem(
    val title: String, val unSelectedItem: ImageVector, val selectedIcon: ImageVector
)

object Items {
    val tabItem = listOf(
        TabItem(
            title = CATEGORY.Business.name,
            unSelectedItem = Icons.Outlined.Payments,
            selectedIcon = Icons.Filled.Payments
        ), TabItem(
            title = CATEGORY.Technology.name,
            unSelectedItem = Icons.Outlined.Devices,
            selectedIcon = Icons.Filled.Devices
        ), TabItem(
            title = CATEGORY.Science.name,
            unSelectedItem = Icons.Outlined.Science,
            selectedIcon = Icons.Filled.Science
        ),
        TabItem(
            title = CATEGORY.Sports.name,
            unSelectedItem = Icons.Outlined.SportsSoccer,
            selectedIcon = Icons.Filled.SportsSoccer
        ), TabItem(
            title = CATEGORY.Health.name,
            unSelectedItem = Icons.Outlined.MonitorHeart,
            selectedIcon = Icons.Filled.MonitorHeart
        ), TabItem(
            title = CATEGORY.Entertainment.name,
            unSelectedItem = Icons.Outlined.Casino,
            selectedIcon = Icons.Filled.Casino
        )
    )
}