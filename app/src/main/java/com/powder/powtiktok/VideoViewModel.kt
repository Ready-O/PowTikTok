package com.powder.powtiktok

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.powder.powtiktok.network.Api
import com.powder.powtiktok.network.Video
import kotlinx.coroutines.launch

class VideoViewModel: ViewModel() {

    private var _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _videos = MutableLiveData<List<Video>>()
    val videos: LiveData<List<Video>> = _videos

    init {
        getVideos()
    }

    private fun getVideos() {
        viewModelScope.launch {
            try {
                _videos.value = Api.videoService.getVideos()
                _status.value = true
            } catch (e: Exception) {
                _status.value = false
            }
        }
    }

}