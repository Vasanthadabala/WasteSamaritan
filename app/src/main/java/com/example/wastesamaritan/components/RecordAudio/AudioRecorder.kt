package com.example.wastesamaritan.components.RecordAudio

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaRecorder
import android.os.Build
import android.util.Log
import androidx.core.content.PackageManagerCompat.LOG_TAG
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class AndroidAudioRecorder(private val context: Context):AudioRecorderInterface{

    private var recorder:MediaRecorder?=null

    private fun createRecorder():MediaRecorder{
        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            MediaRecorder(context)
        }else MediaRecorder()
    }

    @SuppressLint("RestrictedApi")
    override fun start(outputFile: File) {
        createRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setOutputFile(FileOutputStream(outputFile).fd)

            try {
                prepare()
            } catch (e: IOException) {
                Log.e(LOG_TAG, "prepare() failed")
            }
            start()

            recorder = this
        }
    }

    override fun stop() {
        recorder?.stop()
        recorder?.reset()
        recorder = null
    }
}