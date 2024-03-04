package com.example.wastesamaritan.components.RecordAudio

import java.io.File

interface AudioPlayerInterface{
    fun play(file:File)
    fun stop()
}