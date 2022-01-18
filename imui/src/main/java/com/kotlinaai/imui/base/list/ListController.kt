package com.kotlinaai.imui.base.list

import com.kotlinaai.imui.base.repository.DataSourceFactory

interface ListController<KEY: Any, ITEM: Any> {
    fun setDataSourceFactory(
        initialKey: KEY? = null,
        pageSize: Int = 20,
        dataSourceFactory: DataSourceFactory<KEY, ITEM>
    )
    fun scrollToPosition(position: Int)
    fun refresh()
    fun addItemType(itemType: ListItemType<ITEM>) {}
    fun reverseLayout(reverse: Boolean)
}

fun interface OnItemClickListener<ITEM: Any> {
    fun onItemClicked(item: ITEM)
}