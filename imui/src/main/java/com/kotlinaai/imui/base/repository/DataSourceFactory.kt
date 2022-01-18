package com.kotlinaai.imui.base.repository

fun interface DataSourceFactory<KEY: Any, ITEM: Any> {
    fun getDataSource(): DataSource<KEY, ITEM>
}