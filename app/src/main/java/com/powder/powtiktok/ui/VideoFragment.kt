package com.powder.powtiktok.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.powder.powtiktok.VideoViewModel
import com.powder.powtiktok.databinding.FragmentVideoBinding

class VideoFragment : Fragment() {

    private val viewModel: VideoViewModel by viewModels()

    private lateinit var binding: FragmentVideoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVideoBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.listVideosRecyclerview
        val layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        recyclerView.layoutManager = layoutManager

        val adapter = VideoAdapter()
        recyclerView.adapter = adapter

        viewModel.videos.observe(this.viewLifecycleOwner){items ->
            items.let{
                adapter.submitList(it)
            }
        }

        viewModel.status.observe(this.viewLifecycleOwner){
            if (!it){
                Toast.makeText(activity?.applicationContext, "Error !", Toast.LENGTH_LONG).show()
            }
        }


        var currentItemIndex = 0

        //RecyclerView scroll position
        fun setRecyclerViewScrollListener() {
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    // When the user lift his finger from the screen
                    if (newState == RecyclerView.SCROLL_STATE_SETTLING){
                        val firstVisible= layoutManager.findFirstVisibleItemPosition()
                        if (firstVisible == 0 && currentItemIndex == 0){
                            if (currentItemIndex < layoutManager.findLastVisibleItemPosition()){
                                currentItemIndex++
                            }
                        }
                        else if (currentItemIndex == layoutManager.itemCount - 1 && firstVisible == currentItemIndex){
                            currentItemIndex = 0
                        }
                        else if (currentItemIndex <= firstVisible){
                            currentItemIndex++
                        }
                        else {
                            currentItemIndex--
                        }
                        recyclerView.smoothScrollToPosition(currentItemIndex)
                    }
                }

            })
        }

        setRecyclerViewScrollListener()

    }
}