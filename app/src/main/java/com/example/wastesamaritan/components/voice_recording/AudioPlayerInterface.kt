package com.example.wastesamaritan.components.voice_recording

import java.io.File

interface AudioPlayerInterface{
    fun play(file:File)
    fun stop()
}