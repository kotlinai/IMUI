package com.kotlinaai.imui.conversation.inters

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kotlinaai.imui.conversation.pojos.LoadParams as UILoadParams
import com.kotlinaai.imui.conversation.pojos.UIMessage

abstract class MessageSource<KEY: Any>(){

    val pagingSource = object : PagingSource<KEY, UIMessage>() {
        override fun getRefreshKey(state: PagingState<KEY, UIMessage>): KEY? {
            return state.anchorPosition?.let { anchorPosition ->
                val anchorPage = state.closestPageToPosition(anchorPosition)

                anchorPage?.nextKey ?: anchorPage?.prevKey
            }
        }

        override suspend fun load(params: LoadParams<KEY>): PagingSource.LoadResult<KEY, UIMessage> {

            return when (val loadResult = loadMessage(UILoadParams(params.key))) {
                is MessageSource.LoadResult.Page -> PagingSource.LoadResult.Page(
                    loadResult.data,
                    loadResult.prevKey,
                    loadResult.nextKEY
                )
                is MessageSource.LoadResult.Error -> PagingSource.LoadResult.Error(
                    loadResult.error
                )
            }
        }
    }

    abstract fun loadMessage(key: UILoadParams<KEY>): LoadResult<KEY>

    fun refresh() {
        pagingSource.invalidate()
    }

    sealed class LoadResult<KEY> {
        class Page<KEY>(
            val data: List<UIMessage>,
            val prevKey: KEY?,
            val nextKEY: KEY?
        ): LoadResult<KEY>()
        class Error<KEY>(val error: Throwable): LoadResult<KEY>()
    }
}