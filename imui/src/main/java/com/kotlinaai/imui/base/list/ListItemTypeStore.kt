package com.kotlinaai.imui.base.list

class ListItemTypeStore<ITEM: Any> {
    private val items = arrayListOf<ListItemType<ITEM>>()

    fun addItem(itemType: ListItemType<ITEM>) {
        items.add(itemType)
    }

    fun findItemType(item: ITEM): ListItemType<ITEM>? {
        return items.findLast { it.isItemType(item) }
    }
}