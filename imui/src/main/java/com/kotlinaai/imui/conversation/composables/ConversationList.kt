package com.kotlinaai.imui.conversation.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.kotlinaai.imui.conversation.pojos.UIConversation
import com.kotlinaai.imui.base.list.ListViewModel
import com.kotlinaai.imui.ui.theme.IMUITheme

@Composable
fun <KEY: Any> ConversationListPage(
    listViewModel: ListViewModel<KEY, UIConversation>,
    onItemClicked: (UIConversation) -> Unit
) {
    val data = listViewModel.data
    val scrollToPos = listViewModel.scrollToPosition
    val reverseLayout = listViewModel.isLayoutReversed
    val lazyListState = rememberLazyListState()

    LaunchedEffect(scrollToPos) {
        if (scrollToPos >= 0){
            lazyListState.scrollToItem(scrollToPos)
            listViewModel.scrollToPosition = -1
        }
    }

    if (data != null)
        ConversationList(
            conversations = data.collectAsLazyPagingItems(),
            reverseLayout = reverseLayout,
            listState = lazyListState,
            onItemClicked = onItemClicked
        )
}

@Composable
fun ConversationList(
    conversations: LazyPagingItems<UIConversation>,
    reverseLayout: Boolean,
    listState: LazyListState,
    onItemClicked: (UIConversation) -> Unit
) {
    LazyColumn(
        state = listState,
        reverseLayout = reverseLayout
    ) {
        items(conversations, key = {it.id}) {

            if (it != null)
                ConversationItem(item = it) {
                    onItemClicked(it)
                }
        }
    }
}

@Composable
fun ConversationList(conversations: List<UIConversation>) {
    LazyColumn() {
        items(conversations, key = {it.id}) {
            ConversationItem(item = it)
        }
    }
}

@Composable
fun ConversationItem(item: UIConversation, onClicked: () -> Unit = {}) {
    val colorPrimary = MaterialTheme.colors.primary

    Row(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .clickable { onClicked() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .padding(start = 15.dp, top = 13.dp, bottom = 13.dp)
                .size(50.dp),
            painter = rememberImagePainter(data = item.avatar) {
                crossfade(true)
                transformations(CircleCropTransformation())
            },
            contentDescription = "avatar")

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 5.dp),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.padding(start = 5.dp, end = 15.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = item.name,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color("#121213".toColorInt())
                )
                Text(
                    text = item.timeString ?: "",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color("#AAAAAA".toColorInt())
                )
            }
            Row(
                modifier = Modifier.padding(start = 5.dp, end = 15.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = item.message ?: "",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    overflow = TextOverflow.Ellipsis,
                    color = Color("#999999".toColorInt())
                )

                if (item.hasUnReading) {
                    Canvas(modifier = Modifier.size(8.dp)) {
                        drawCircle(
                            color = colorPrimary
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Divider(
                color = Color("#F0F0F0".toColorInt()),
                thickness = 0.5.dp
            )
        }

    }
}

@Preview
@Composable
fun ConversationItemPreview() {
    IMUITheme {
        ConversationItem(item = UIConversation(
            id = "1",
            avatar = "https://c.wallhere.com/photos/da/77/illustration_artwork_digital_art_fan_art_drawing_fantasy_art_fantasy_girl_women-2069215.jpg!d",
            name = "火芸",
            message = "桃花得气美人中",
            timeString = "2分钟前",
            true
        ))
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ConversationListPreview() {
    IMUITheme {
        ConversationList(conversations = listOf(
            UIConversation(
                id = "1",
                avatar = "https://c.wallhere.com/photos/da/77/illustration_artwork_digital_art_fan_art_drawing_fantasy_art_fantasy_girl_women-2069215.jpg!d",
                name = "火芸",
                message = "桃花得气美人中",
                timeString = "2分钟前",
                true
            ),
            UIConversation(
                id = "2",
                avatar = "https://c.wallhere.com/photos/da/77/illustration_artwork_digital_art_fan_art_drawing_fantasy_art_fantasy_girl_women-2069215.jpg!d",
                name = "火芸",
                message = "桃花得气美人中",
                timeString = "2分钟前",
                true
            ),
            UIConversation(
                id = "3",
                avatar = "https://c.wallhere.com/photos/da/77/illustration_artwork_digital_art_fan_art_drawing_fantasy_art_fantasy_girl_women-2069215.jpg!d",
                name = "火芸",
                message = "桃花得气美人中",
                timeString = "2分钟前",
                true
            ),
            UIConversation(
                id = "4",
                avatar = "https://c.wallhere.com/photos/da/77/illustration_artwork_digital_art_fan_art_drawing_fantasy_art_fantasy_girl_women-2069215.jpg!d",
                name = "火芸",
                message = "桃花得气美人中",
                timeString = "2分钟前",
                true
            ),
            UIConversation(
                id = "5",
                avatar = "https://c.wallhere.com/photos/da/77/illustration_artwork_digital_art_fan_art_drawing_fantasy_art_fantasy_girl_women-2069215.jpg!d",
                name = "火芸",
                message = "桃花得气美人中",
                timeString = "2分钟前",
                true
            )
        ))
    }
}