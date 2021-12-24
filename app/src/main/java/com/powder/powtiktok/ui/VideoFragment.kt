package com.powder.powtiktok.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.powder.powtiktok.VideoViewModel
import com.powder.powtiktok.databinding.FragmentVideoBinding

class VideoFragment : Fragment() {

    private val viewModel: VideoViewModel by viewModels()

    private lateinit var binding: FragmentVideoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideoBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.listVideosRecyclerview
        recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

        val adapter = VideoAdapter()
        recyclerView.adapter = adapter

        viewModel.videos.observe(this.viewLifecycleOwner){items ->
            items.let{
                adapter.submitList(it)
            }
        }


    }
}