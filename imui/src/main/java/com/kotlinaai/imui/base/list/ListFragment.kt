package com.kotlinaai.imui.base.list

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kotlinaai.imui.base.repository.DataSourceFactory

abstract class ListFragment<KEY: Any, ITEM: Any> : Fragment(), ListController<KEY, ITEM> {
    protected val listViewModel by viewModels<ListViewModel<KEY, ITEM>>()

    override fun scrollToPosition(position: Int) {
        listViewModel.scrollToPosition = position
    }

    override fun refresh() {
        listViewModel.refresh()
    }

    override fun reverseLayout(reverse: Boolean) {
        listViewModel.isLayoutReversed = reverse
    }

    override fun setDataSourceFactory(
        initialKey: KEY?,
        pageSize: Int,
        dataSourceFactory: DataSourceFactory<KEY, ITEM>
    ) {
        listViewModel.setDataSource(initialKey, pageSize, dataSourceFactory)
    }

    override fun addItemType(itemType: ListItemType<ITEM>) {
        listViewModel.listItemTypeStore.addItem(itemType)
    }

    /*override fun setDataSourceFactory(
        initialKey: KEY?,
        pageSize: Int,
        dataSourceFactory: DataSourceFactory<Long, UIConversation>
    ) {
        listViewModel.setDataSource(initialKey, pageSize, dataSourceFactory)
    }*/
}