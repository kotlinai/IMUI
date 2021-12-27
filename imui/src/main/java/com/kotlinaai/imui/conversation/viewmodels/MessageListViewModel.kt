package com.kotlinaai.imui.conversation.viewmodels

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kotlinaai.imui.base.BaseViewModel
import com.kotlinaai.imui.conversation.inters.MessageSource
import com.kotlinaai.imui.conversation.inters.MessageSourceFactory
import com.kotlinaai.imui.conversation.pojos.UIMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MessageListViewModel: BaseViewModel() {

    /*private lateinit var messageSourceFactory: MessageSourceFactory
*/
    /*val data = Pager(
        PagingConfig(20)
    ) {
        messageSourceFactory.getMessageSource()
    }*/
    //var pager: Pager<*, UIMessage>? = null

    private var data: Flow<PagingData<UIMessage>>? = null

    fun <KEY: Any> setMessageSource(
        messageSourceFactory: MessageSourceFactory<KEY>,
        onMessagesUpdate: suspend (PagingData<UIMessage>) -> Unit) {
        data = Pager(PagingConfig(20)) {
            messageSourceFactory.getMessageSource()
        }.flow.cachedIn(viewModelScope)

        viewModelScope.launch {
            data?.collectLatest {
                onMessagesUpdate(it)
            }
        }
    }
}