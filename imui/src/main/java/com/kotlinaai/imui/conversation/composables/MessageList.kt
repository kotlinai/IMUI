package com.kotlinaai.imui.conversation.composables

import android.graphics.drawable.ColorDrawable
import android.text.format.DateUtils
import android.view.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import androidx.paging.compose.itemsIndexed
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.kotlinaai.imui.base.list.ListItemTypeStore
import com.kotlinaai.imui.base.list.ListViewModel
import com.kotlinaai.imui.conversation.adapters.MessageItemStore
import com.kotlinaai.imui.conversation.pojos.UIConversation
import com.kotlinaai.imui.conversation.pojos.UIMessage
import com.kotlinaai.imui.conversation.viewmodels.MessageListViewModel
import com.kotlinaai.imui.ui.theme.IMUITheme

@Composable
fun <KEY: Any> MessageList(
    messageListViewModel: ListViewModel<KEY, UIMessage>
) {
    val listState = rememberLazyListState()
    val data = messageListViewModel.data

    if (data != null) {
        val messages = data.collectAsLazyPagingItems()

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState,
            reverseLayout = messageListViewModel.isLayoutReversed
        ) {
            /*items(messages, key = {it.id}) { message ->
                message?.let {

                    messageListViewModel
                        .listItemTypeStore
                        .findItemType(message)
                        ?.ListItem(item = message)
                }
            }*/

            itemsIndexed(messages, key = {_, item -> item.id }) { index, value ->

                value?.let { message ->

                    message.isTimeShow = (index == messages.itemCount - 1)
                            || ((messages[index + 1]?.time ?: 0) - message.time > 60000)

                    messageListViewModel
                        .listItemTypeStore
                        .findItemType(message)
                        ?.ListItem(item = message)
                }
            }
        }
    }

    ListController(listState = listState, messageListViewModel)
}

@Composable
fun <KEY: Any> ListController(
    listState: LazyListState,
    messageListViewModel: ListViewModel<KEY, UIMessage>
) {
    val scrollToPosition = messageListViewModel.scrollToPosition

    LaunchedEffect(scrollToPosition) {
        if (scrollToPosition >= 0) {
            listState.scrollToItem(scrollToPosition)

            messageListViewModel.scrollToPosition = -1
        }
    }
}

@Composable
fun ReceivingMessage(
    message: UIMessage,
    onAvatarClicked: () -> Unit = {},
    onContentClicked: (view: View?) -> Unit = {},
    content: @Composable () -> Unit) {

    Column(modifier = Modifier.padding(vertical = 7.5.dp)) {
        if (message.isTimeShow)
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 7.5.dp),
                text = DateUtils.getRelativeTimeSpanString(message.time).toString(),
                fontSize = 13.sp,
                textAlign = TextAlign.Center)

        Row(
            modifier = Modifier.padding(start = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            if (message.avatar != null) {
                Image(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .clickable { onAvatarClicked() },
                    painter = rememberImagePainter(data = message.avatar) {
                        crossfade(true)
                        transformations(CircleCropTransformation())
                    },
                    contentDescription = message.name)
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                if (message.name != null) {
                    Text(
                        text = message.name,
                        color = MaterialTheme.colors.onBackground,
                        fontSize = 13.sp
                    )
                }

                Row(
                    modifier = Modifier.padding(end = 50.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {

                    var view: View? = null

                    Box(modifier = Modifier
                        .weight(1f, false)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable { onContentClicked(view) }
                    ) {
                        content()

                        AndroidView(
                            modifier = Modifier.matchParentSize(),
                            factory = { View(it)}) {
                            view = it
                        }
                    }

                    when (message.status) {
                        UIMessage.STATUS_ERROR -> {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = Icons.Outlined.Info,
                                contentDescription = "error",
                                tint = MaterialTheme.colors.error)
                        }
                        UIMessage.STATUS_SENDING -> {
                            CircularProgressIndicator(modifier = Modifier.size(24.dp), strokeWidth = 1.dp)
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}

@Composable
fun SendingMessage(
    message: UIMessage,
    onContentClicked: (View?) -> Unit = {},
    content: @Composable () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 7.5.dp, bottom = 7.5.dp)
    ) {

        if (message.isTimeShow) {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 7.5.dp),
                text = DateUtils.getRelativeTimeSpanString(message.time).toString(),
                fontSize = 13.sp,
                textAlign = TextAlign.Center)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 50.dp, end = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.End),
            verticalAlignment = Alignment.CenterVertically) {
            when (message.status) {
                UIMessage.STATUS_ERROR -> {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.Outlined.Info,
                        contentDescription = "error",
                        tint = MaterialTheme.colors.error)
                }
                UIMessage.STATUS_SENDING -> {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), strokeWidth = 1.dp)
                }
                else -> {}
            }

            var mask: View? = null

            Box(modifier = Modifier
                .weight(1f, false)
                .clip(RoundedCornerShape(10.dp))
                .clickable { onContentClicked(mask) }) {
                content()

                AndroidView(
                    modifier = Modifier.matchParentSize(),
                    factory = { View(it) }) {
                    mask = it
                }
            }
        }
    }
}

@Composable
fun SystemMessage(message: UIMessage, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 7.5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (message.isTimeShow) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 7.5.dp),
                text = DateUtils.getRelativeTimeSpanString(message.time).toString(),
                fontSize = 13.sp,
                textAlign = TextAlign.Center)
        }

        Box(
            modifier = Modifier.clip(RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}

@Composable
fun TextContent(
    modifier: Modifier = Modifier,
    text: String,
    backgroundColor: Color,
    contentColor: Color = contentColorFor(backgroundColor = backgroundColor)) {

    Text(
        modifier = Modifier
            .background(
                color = backgroundColor
            )
            .padding(12.dp) then modifier,
        text = text,
        fontSize = 15.sp,
        color = contentColor)
}

@Composable
fun ImageContent(
    modifier: Modifier = Modifier,
    path: String?,
    backgroundColor: Color) {

    Surface(
        modifier = Modifier
            .wrapContentSize()
            .sizeIn(38.dp, 38.dp, 140.dp, 140.dp) then modifier,
        color = backgroundColor
    ) {
        Image(
            modifier = Modifier.wrapContentSize(),
            painter = rememberImagePainter(data = path),
            contentDescription = "imageMessage")
    }
}

@Composable
fun VideoContent(
    modifier: Modifier = Modifier,
    thumbPath: String?,
    backgroundColor: Color) {

    Surface(
        modifier = Modifier
            .wrapContentSize()
            .sizeIn(38.dp, 38.dp, 140.dp, 140.dp) then modifier,
        color = backgroundColor
    ) {
        Box {
            Image(
                modifier = Modifier.wrapContentSize(),
                painter = rememberImagePainter(data = thumbPath),
                contentDescription = "imageMessage")
            Icon(
                modifier = Modifier.align(Alignment.Center),
                imageVector = Icons.Outlined.PlayArrow,
                contentDescription = "play",
                tint = Color.White
            )
        }
    }
}

@Preview
@Composable
fun TextContextPreview() {

    IMUITheme {
        TextContent(
            text = "桃花树下桃花庵，桃花庵里桃花仙",
            backgroundColor = MaterialTheme.colors.primary)
    }
}

@Preview
@Composable
fun ImageContentPreview() {
    IMUITheme {
        ImageContent(
            path = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fhbimg.huabanimg.com%2F51410c92b574969aa2fea7a84f176cb04ebc836813e5b-ytIBpO_fw658&refer=http%3A%2F%2Fhbimg.huabanimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1644737546&t=d3e1f079cf66aef8df99815c8bae0f9a",
            backgroundColor = MaterialTheme.colors.primary
        )
    }
}

@Preview
@Composable
fun VideoContentPreview() {
    IMUITheme {
        VideoContent(
            thumbPath = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fhbimg.huabanimg.com%2F51410c92b574969aa2fea7a84f176cb04ebc836813e5b-ytIBpO_fw658&refer=http%3A%2F%2Fhbimg.huabanimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1644737546&t=d3e1f079cf66aef8df99815c8bae0f9a",
            backgroundColor = MaterialTheme.colors.primary
        )
    }
}

@Preview
@Composable
fun ReceivingMessagePreview() {
    IMUITheme {
        ReceivingMessage(
            UIMessage(
                "1",
                UIMessage.TYPE_TEXT,
                UIMessage.ORIGIN_TYPE_RECEIVING,
                time = 1642492501000,
                msg = "曾经沧海难为水，出去巫山不是云",
                name = "水芸", avatar = "https://c.wallhere.com/images/fa/bc/c70c34c1a41652872e1be8ad350c-1530111.jpg!d")) {
            TextContent(
                text = "曾经沧海难为水，除却巫山不是云。取次花丛懒回顾，半缘修道半缘君。",
                backgroundColor = MaterialTheme.colors.surface)
        }
    }
}

@Preview
@Composable
fun SendingMessagePreview() {

    IMUITheme {
        SendingMessage(UIMessage(
            "1",
            UIMessage.TYPE_TEXT,
            UIMessage.ORIGIN_TYPE_SENDING,
            time = 1642492501000,
            msg = "曾经沧海难为水，出去巫山不是云",
            name = "水芸", avatar = "https://c.wallhere.com/images/fa/bc/c70c34c1a41652872e1be8ad350c-1530111.jpg!d")) {
            TextContent(
                text = "人生如雾亦如梦，缘生缘灭还自在。",
                backgroundColor = MaterialTheme.colors.surface)
        }
    }
}