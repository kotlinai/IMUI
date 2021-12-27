package com.kotlinaai.imui.conversation.pojos

data class UIMessage(
    val id: String,
    var msg: String,
    var messageType: Int,
    var originType: Int,
    var avatar: String? = null,
    var name: String? = null,
    var time: Long? = null,
    var isTimeShow: Boolean = false,
    var status: Int = STATUS_SUCCESS,
    var extra: Any? = null
) {
    companion object {
        const val TYPE_TEXT_SEND = 1
        const val TYPE_TEXT_RECEIVE = 2
        const val TYPE_IMAGE_SEND = 3
        const val TYPE_IMAGE_RECEIVE = 4
        const val TYPE_VIDEO_SEND = 5
        const val TYPE_VIDEO_RECEIVE = 6
        const val TYPE_EXTEND_POST_SEND = 101
        const val TYPE_EXTEND_POST_RECEIVE = 102
        const val TYPE_UNKNOWN = 0

        const val ORIGIN_TYPE_SENDING = -1
        const val ORIGIN_TYPE_RECEIVING = 1
        const val ORIGIN_TYPE_UNDEFINED = 0

        const val STATUS_SUCCESS = 0
        const val STATUS_SENDING = 1
        const val STATUS_ERROR = 2
    }
}
