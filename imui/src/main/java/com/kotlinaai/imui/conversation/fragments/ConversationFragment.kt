package com.kotlinaai.imui.conversation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import com.kotlinaai.imui.R

class ConversationFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val videoView = VideoView(requireContext())

        videoView.setMediaController(MediaController(requireContext()))
        return inflater.inflate(R.layout.fragment_conversation, container, false)
    }
}