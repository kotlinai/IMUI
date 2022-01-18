package com.kotlinaai.imui.conversation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Surface
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import com.kotlinaai.imui.base.list.ListFragment
import com.kotlinaai.imui.base.list.OnItemClickListener
import com.kotlinaai.imui.conversation.composables.ConversationListPage
import com.kotlinaai.imui.conversation.pojos.UIConversation
import com.kotlinaai.imui.conversation.viewmodels.ConversationListViewModel
import com.kotlinaai.imui.ui.theme.IMUITheme

class ConversationListFragment : ListFragment<Long, UIConversation>() {

    private val viewModel by viewModels<ConversationListViewModel>()
    private var onItemClickListener: OnItemClickListener<UIConversation>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            setContent {
                IMUITheme {
                    Surface {
                        ConversationListPage(listViewModel) {
                            onItemClickListener?.onItemClicked(it)
                        }
                    }
                }
            }
        }
    }

    fun whenItemClicked(onItemClicked: OnItemClickListener<UIConversation>) {
        this.onItemClickListener = onItemClicked
    }
}