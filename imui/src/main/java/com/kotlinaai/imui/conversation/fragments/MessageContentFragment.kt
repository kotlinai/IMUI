package com.kotlinaai.imui.conversation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.kotlinaai.imui.R
import com.kotlinaai.imui.base.BaseFragment
import com.kotlinaai.imui.conversation.adapters.UIMessageAdapter
import com.kotlinaai.imui.conversation.inters.MessageSourceFactory
import com.kotlinaai.imui.conversation.viewmodels.MessageListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MessageContentFragment : BaseFragment() {

    private val viewModel by viewModels<MessageListViewModel>()
    private val messageAdapter = UIMessageAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message_content, container, false)
    }

    fun <KEY: Any> setMessageSource(messageSourceFactory: MessageSourceFactory<KEY>) {

        viewModel.setMessageSource(messageSourceFactory) {
            messageAdapter.submitData(it)
        }
    }
}