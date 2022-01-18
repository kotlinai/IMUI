package com.kotlinaai.app

import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.lifecycleScope
import com.kotlinaai.imui.conversation.fragments.ConversationListFragment
import com.kotlinaai.imui.base.repository.DataSource
import com.kotlinaai.imui.conversation.fragments.MessageContentFragment
import com.kotlinaai.imui.conversation.pojos.UIConversation
import com.kotlinaai.imui.conversation.pojos.UIMessage
import kotlinx.coroutines.delay
import kotlin.random.Random

/*
*
 * {"returnCode":"0","returnMsg":"获取成功","data":{"lists":[{"id":230,"teamId":52,"userId":62,"invitorId":62,"inviterAccid":0,"notifyState":0,"type":1,"nickname":"John","isMuted":0,"customInfo":" ","isDel":1,"createTime":"1639634917908","updateTime":"1639634917908"},{"id":231,"teamId":52,"userId":62,"invitorId":62,"inviterAccid":0,"notifyState":0,"type":0,"nickname":"John","isMuted":0,"customInfo":" ","isDel":1,"createTime":"1639634917908","updateTime":"1639634917908"},{"id":232,"teamId":52,"userId":43,"invitorId":62,"inviterAccid":0,"notifyState":0,"type":0,"nickname":"用户名36053","isMuted":0,"customInfo":" ","isDel":1,"createTime":"1639634917908","updateTime":"1639634917908"},{"id":233,"teamId":52,"userId":63,"invitorId":62,"inviterAccid":0,"notifyState":0,"type":0,"nickname":"23dddddd","isMuted":0,"customInfo":" ","isDel":1,"createTime":"1639634917908","updateTime":"1639634917908"},{"id":234,"teamId":52,"userId":3,"invitorId":62,"inviterAccid":0,"notifyState":0,"type":0,"nickname":"天下风云","isMuted":0,"customInfo":" ","isDel":1,"createTime":"1639634917908","updateTime":"1639634917908"}],"hasMore":false}}

*/

class MainActivity : AppCompatActivity() {
    //private lateinit var container: FragmentContainerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // container = findViewById(R.id.fragmentContainerView)

        (supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
                as MessageContentFragment).apply {

            /*lifecycleScope.launchWhenStarted {
                delay(10 * 1000)
                //scrollToPosition(20)
                refresh()
            }

                    setDataSourceFactory {
                        object : DataSource<Long, UIConversation>() {
                            override suspend fun load(params: LoadParams<Long>): LoadResult<Long, UIConversation> {

                                val count = Random.nextInt(5, 20)

                                return LoadResult.Page(
                                    buildList {
                                        for (i in 0..count) {
                                            val item = UIConversation(
                                                i.toString(),
                                                "https://c.wallhere.com/photos/da/77/illustration_artwork_digital_art_fan_art_drawing_fantasy_art_fantasy_girl_women-2069215.jpg!d",
                                                "水芸$i",
                                                "桃花得气美人中",
                                                "刚刚",
                                                i % 2 == 0
                                            )

                                            add(item)
                                        }
                                    },
                                    null,
                                    null
                                )
                            }
                        }
                    }

            whenItemClicked {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }*/
            /*addMessageItemType(TextMessageItemL())
            addMessageItemType(TextMessageItemR())*/

            onAvatarClicked {
                Toast.makeText(requireContext(), "点击头像", Toast.LENGTH_SHORT).show()
            }

            onContentClicked { view, uiMessage ->
                val textView = TextView(requireContext()).apply {
                    text = "点击消息"
                }

                PopupWindow(
                    textView,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    isOutsideTouchable = true

                    showAsDropDown(view)
                }
            }

            setDataSourceFactory {
                object : DataSource<String, UIMessage>() {
                    override suspend fun load(params: LoadParams<String>): LoadResult<String, UIMessage> {
                        return LoadResult.Page(
                            listOf(
                                UIMessage(
                                    "1",
                                    UIMessage.TYPE_TEXT,
                                    UIMessage.ORIGIN_TYPE_SENDING,
                                    status = UIMessage.STATUS_ERROR,
                                    time = 1642492501000,
                                    msg = "曾经沧海难为水，除却巫山不是云。取次花丛懒回顾，半缘修道半缘君。",
                                    name = "水芸", avatar = "https://c.wallhere.com/images/fa/bc/c70c34c1a41652872e1be8ad350c-1530111.jpg!d"),
                                UIMessage(
                                    "2",
                                    UIMessage.TYPE_TEXT,
                                    UIMessage.ORIGIN_TYPE_RECEIVING,
                                    status = UIMessage.STATUS_ERROR,
                                    time = 1642491301000,
                                    msg = "曾经沧海难为水，除却巫山不是云。取次花丛懒回顾，半缘修道半缘君。",
                                    name = "水芸", avatar = "https://c.wallhere.com/images/fa/bc/c70c34c1a41652872e1be8ad350c-1530111.jpg!d"),
                                UIMessage(
                                    "100",
                                    messageType = UIMessage.TYPE_SYSTEM,
                                    time = 1642491301000,
                                    msg = "系统消息"),
                                UIMessage(
                                    "3",
                                    UIMessage.TYPE_IMAGE,
                                    UIMessage.ORIGIN_TYPE_SENDING,
                                    time = 1642491121000,
                                    image = UIMessage.Image("https://c.wallhere.com/images/fa/bc/c70c34c1a41652872e1be8ad350c-1530111.jpg!d"),
                                    name = "水芸", avatar = "https://c.wallhere.com/images/fa/bc/c70c34c1a41652872e1be8ad350c-1530111.jpg!d"
                                ),
                                UIMessage(
                                    "4",
                                    UIMessage.TYPE_IMAGE,
                                    UIMessage.ORIGIN_TYPE_RECEIVING,
                                    time = 1642492501000,
                                    image = UIMessage.Image("https://c.wallhere.com/images/fa/bc/c70c34c1a41652872e1be8ad350c-1530111.jpg!d"),
                                    name = "水芸", avatar = "https://c.wallhere.com/images/fa/bc/c70c34c1a41652872e1be8ad350c-1530111.jpg!d"),
                                UIMessage(
                                    "200",
                                    UIMessage.TYPE_IMAGE,
                                    UIMessage.ORIGIN_TYPE_RECEIVING,
                                    time = 1642492501000,
                                    image = UIMessage.Image("https://c.wallhere.com/images/fa/bc/c70c34c1a41652872e1be8ad350c-1530111.jpg!d"),
                                    name = "水芸", avatar = "https://c.wallhere.com/images/fa/bc/c70c34c1a41652872e1be8ad350c-1530111.jpg!d"),
                                UIMessage(
                                    "201",
                                    UIMessage.TYPE_IMAGE,
                                    UIMessage.ORIGIN_TYPE_RECEIVING,
                                    time = 1642492501000,
                                    image = UIMessage.Image("https://c.wallhere.com/images/fa/bc/c70c34c1a41652872e1be8ad350c-1530111.jpg!d"),
                                    name = "水芸", avatar = "https://c.wallhere.com/images/fa/bc/c70c34c1a41652872e1be8ad350c-1530111.jpg!d"),
                                UIMessage(
                                    "202",
                                    UIMessage.TYPE_IMAGE,
                                    UIMessage.ORIGIN_TYPE_RECEIVING,
                                    time = 1642492501000,
                                    image = UIMessage.Image("https://c.wallhere.com/images/fa/bc/c70c34c1a41652872e1be8ad350c-1530111.jpg!d"),
                                    name = "水芸", avatar = "https://c.wallhere.com/images/fa/bc/c70c34c1a41652872e1be8ad350c-1530111.jpg!d"),
                                UIMessage(
                                    "203",
                                    UIMessage.TYPE_IMAGE,
                                    UIMessage.ORIGIN_TYPE_RECEIVING,
                                    time = 1642492501000,
                                    image = UIMessage.Image("https://c.wallhere.com/images/fa/bc/c70c34c1a41652872e1be8ad350c-1530111.jpg!d"),
                                    name = "水芸", avatar = "https://c.wallhere.com/images/fa/bc/c70c34c1a41652872e1be8ad350c-1530111.jpg!d"),
                                UIMessage(
                                    "5",
                                    UIMessage.TYPE_VIDEO,
                                    UIMessage.ORIGIN_TYPE_SENDING,
                                    time = 1642492501000,
                                    video = UIMessage.Video("https://c.wallhere.com/images/fa/bc/c70c34c1a41652872e1be8ad350c-1530111.jpg!d"),
                                    name = "水芸", avatar = "https://c.wallhere.com/images/fa/bc/c70c34c1a41652872e1be8ad350c-1530111.jpg!d"),
                                UIMessage(
                                    "6",
                                    UIMessage.TYPE_VIDEO,
                                    UIMessage.ORIGIN_TYPE_SENDING,
                                    time = 1642492501000,
                                    video = UIMessage.Video("https://c.wallhere.com/images/fa/bc/c70c34c1a41652872e1be8ad350c-1530111.jpg!d"),
                                    name = "水芸", avatar = "https://c.wallhere.com/images/fa/bc/c70c34c1a41652872e1be8ad350c-1530111.jpg!d"),
                                UIMessage(
                                    "7",
                                    UIMessage.TYPE_VIDEO,
                                    UIMessage.ORIGIN_TYPE_RECEIVING,
                                    time = 1642492501000,
                                    video = UIMessage.Video("https://c.wallhere.com/images/fa/bc/c70c34c1a41652872e1be8ad350c-1530111.jpg!d"),
                                    name = "水芸", avatar = "https://c.wallhere.com/images/fa/bc/c70c34c1a41652872e1be8ad350c-1530111.jpg!d")
                                ),
                            null,
                            null
                        )
                    }

                }
            }
            /*setMessageSource {
                object : MessageSource<Int>() {
                    override fun loadMessage(key: LoadParams<Int>): LoadResult<Int> {
                        return LoadResult.Page(
                            listOf(
                                UIMessage("1", "你好a", UIMessage.TYPE_TEXT, UIMessage.ORIGIN_TYPE_SENDING),
                                UIMessage("2", "你好b", UIMessage.TYPE_TEXT, UIMessage.ORIGIN_TYPE_RECEIVING),
                                UIMessage("3", "你好c", UIMessage.TYPE_TEXT, UIMessage.ORIGIN_TYPE_SENDING),
                                UIMessage("4", "你好d", UIMessage.TYPE_TEXT, UIMessage.ORIGIN_TYPE_SENDING),
                                UIMessage("5", "你好e", UIMessage.TYPE_TEXT, UIMessage.ORIGIN_TYPE_RECEIVING),
                                UIMessage("6", "你好f", UIMessage.TYPE_TEXT, UIMessage.ORIGIN_TYPE_SENDING),
                                UIMessage("7", "你好g", UIMessage.TYPE_TEXT, UIMessage.ORIGIN_TYPE_RECEIVING),
                                UIMessage("8", "你好h", UIMessage.TYPE_TEXT, UIMessage.ORIGIN_TYPE_SENDING)
                            ),
                            null,
                            null
                        )
                    }
                }
            }*/
        }
    }
}
