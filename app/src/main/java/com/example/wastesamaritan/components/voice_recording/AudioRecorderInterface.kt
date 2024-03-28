package com.example.wastesamaritan.components.voice_recording

import java.io.File

interface AudioRecorderInterface {
    fun start(outputFile: File)
    fun stop()
}