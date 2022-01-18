package com.kotlinaai.imui.conversation.viewmodels

import android.view.View
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.kotlinaai.imui.base.BaseViewModel
import com.kotlinaai.imui.base.list.ListItemTypeStore
import com.kotlinaai.imui.conversation.adapters.MessageItem
import com.kotlinaai.imui.conversation.adapters.MessageItemStore
import com.kotlinaai.imui.conversation.inters.MessageSourceFactory
import com.kotlinaai.imui.conversation.pojos.UIMessage
import kotlinx.coroutines.flow.Flow

class MessageListViewModel: BaseViewModel() {

    var onAvatarOnClicked: ((UIMessage) -> Unit)? = null
    var onContentClicked: ((View?, UIMessage) -> Unit)? = null
}