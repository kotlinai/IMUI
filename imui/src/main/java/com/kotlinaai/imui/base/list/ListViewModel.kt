package com.kotlinaai.imui.base.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kotlinaai.imui.base.repository.DataSource
import com.kotlinaai.imui.base.repository.DataSourceFactory
import kotlinx.coroutines.flow.Flow

open class ListViewModel<KEY: Any, ITEM: Any>: ViewModel() {

    private var dataSource: DataSource<KEY, ITEM>? = null

    var scrollToPosition by mutableStateOf(-1)
    var isLayoutReversed by mutableStateOf(false)
    val listItemTypeStore = ListItemTypeStore<ITEM>()

    var data by mutableStateOf<Flow<PagingData<ITEM>>?>(null)

    fun setDataSource(
        initialKey: KEY? = null, pageSize: Int = 20,
        dataSourceFactory: DataSourceFactory<KEY, ITEM>
    ) {

        data = Pager(PagingConfig(pageSize), initialKey) {
            dataSourceFactory.getDataSource().also {
                dataSource = it
            }
        }.flow.cachedIn(viewModelScope)
    }

    fun refresh() {
        dataSource?.invalidate()
    }
}