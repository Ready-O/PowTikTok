package com.powder.powtiktok.ui

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.powder.powtiktok.R
import com.powder.powtiktok.databinding.VideoItemBinding
import com.powder.powtiktok.network.Video

class VideoAdapter : ListAdapter<Video, VideoAdapter.VideoViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Video>() {
        override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
            return oldItem.videoUrl == newItem.videoUrl
        }
    }

    class VideoViewHolder(private var binding: VideoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(video: Video) {
            val videoView = binding.videoView
            videoView.setVideoURI(Uri.parse(video.videoUrl))
            videoView.start()
            videoView.pause()
            binding.buttonPlayPause.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            binding.buttonPlayPause.setOnClickListener {
                val position = videoView.currentPosition
                if (videoView.isPlaying){
                    videoView.pause()
                    binding.buttonPlayPause.setImageResource(R.drawable.ic_baseline_play_arrow_24)
                }
                else{
                    videoView.seekTo(position)
                    videoView.start()
                    //videoView.resume() doesn't work
                    binding.buttonPlayPause.setImageResource(R.drawable.ic_baseline_pause_24)
                }
            }

            // For looping video
            videoView.setOnPreparedListener { it.isLooping = true }

            binding.title.text =  video.title
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VideoViewHolder {
        return VideoViewHolder(VideoItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        //if (position == 1){
        val videoItem = getItem(position)
        holder.bind(videoItem)
        //}
    }
}