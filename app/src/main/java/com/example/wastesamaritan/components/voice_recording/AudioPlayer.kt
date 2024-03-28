package com.example.wastesamaritan.components.voice_recording

import android.content.Context
import android.media.MediaPlayer
import androidx.core.net.toUri
import java.io.File

class AndroidAudioPlayer(private val context: Context):AudioPlayerInterface{

    private var player:MediaPlayer?=null

    override fun play(file: File) {
        MediaPlayer.create(context, file.toUri()).apply {
            player = this
            start()
        }
    }

    override fun stop() {
        player?.stop()
        player?.release()
        player = null
    }

    fun isPlaying(): Boolean {
        return player?.isPlaying ?: false
    }

    fun currentPosition(): Int {
        return player?.currentPosition ?: 0
    }

    fun duration(): Int {
        return player?.duration ?: 0
    }
}