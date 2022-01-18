package com.kotlinaai.imui.base.list

import androidx.compose.runtime.Composable

interface ListItemType<ITEM: Any> {
    fun isItemType(item: ITEM): Boolean

    @Composable
    fun ListItem(item: ITEM)
}