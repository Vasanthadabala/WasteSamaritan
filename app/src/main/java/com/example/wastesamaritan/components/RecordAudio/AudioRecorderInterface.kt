package com.example.wastesamaritan.components.RecordAudio

import java.io.File

interface AudioRecorderInterface {
    fun start(outputFile: File)
    fun stop()
}