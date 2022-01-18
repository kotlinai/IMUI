package com.kotlinaai.imui.base.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState

abstract class DataSource<KEY: Any, ITEM: Any>: PagingSource<KEY, ITEM>() {
    override fun getRefreshKey(state: PagingState<KEY, ITEM>): KEY? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)

            anchorPage?.prevKey ?: anchorPage?.nextKey
        }
    }
}