package com.kotlinaai.imui.conversation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import com.kotlinaai.imui.base.BaseFragment
import com.kotlinaai.imui.base.list.ListFragment
import com.kotlinaai.imui.conversation.adapters.MessageItem
import com.kotlinaai.imui.conversation.adapters.messageItems.ImageMessageItem
import com.kotlinaai.imui.conversation.adapters.messageItems.SystemMessageItem
import com.kotlinaai.imui.conversation.adapters.messageItems.TextMessageItem
import com.kotlinaai.imui.conversation.adapters.messageItems.VideoMessageItem
import com.kotlinaai.imui.conversation.composables.MessageList
import com.kotlinaai.imui.conversation.inters.MessageSourceFactory
import com.kotlinaai.imui.conversation.pojos.UIMessage
import com.kotlinaai.imui.conversation.viewmodels.MessageListViewModel
import com.kotlinaai.imui.ui.theme.IMUITheme

class MessageContentFragment : ListFragment<String, UIMessage>() {

    private val viewModel by viewModels<MessageListViewModel>()

    /*private lateinit var binding: FragmentMessageContentBinding
    private val messageAdapter = UIMessageAdapter()*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /*binding = FragmentMessageContentBinding.inflate(inflater, container, false)

        binding.recyclerView.adapter = messageAdapter

        return binding.root*/
        listViewModel.isLayoutReversed = true
        listViewModel.listItemTypeStore.addItem(
            TextMessageItem(
                onAvatarClicked = { viewModel.onAvatarOnClicked?.invoke(it)},
                onContentClicked = { view, message -> viewModel.onContentClicked?.invoke(view, message)}
            ))
        listViewModel.listItemTypeStore.addItem(
            ImageMessageItem(
                onAvatarClicked = { viewModel.onAvatarOnClicked?.invoke(it)},
                onContentClicked = { view, message -> viewModel.onContentClicked?.invoke(view, message)}
            ))
        listViewModel.listItemTypeStore.addItem(
            VideoMessageItem(
                onAvatarClicked = { viewModel.onAvatarOnClicked?.invoke(it)},
                onContentClicked = { view, message -> viewModel.onContentClicked?.invoke(view, message)}
            ))
        listViewModel.listItemTypeStore.addItem(SystemMessageItem())

        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            setContent {
                IMUITheme {
                    Surface(modifier = Modifier.fillMaxSize()) {
                        MessageList(listViewModel)
                    }
                }
            }
        }
    }

    fun onAvatarClicked(onAvatarOnClicked: ((UIMessage) -> Unit)) {
        viewModel.onAvatarOnClicked = onAvatarOnClicked
    }

    fun onContentClicked(onContentClicked: ((View?, UIMessage) -> Unit)) {
        viewModel.onContentClicked = onContentClicked
    }
}
