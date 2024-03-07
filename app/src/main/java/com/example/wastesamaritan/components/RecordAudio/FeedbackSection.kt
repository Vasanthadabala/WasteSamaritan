
import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Mic
import androidx.compose.material.icons.rounded.MicNone
import androidx.compose.material.icons.rounded.PlayCircle
import androidx.compose.material.icons.rounded.StopCircle
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.wastesamaritan.R
import com.example.wastesamaritan.components.RecordAudio.AndroidAudioPlayer
import com.example.wastesamaritan.components.RecordAudio.AndroidAudioRecorder
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File


@Composable
fun FeedbackSection() {
    val context = LocalContext.current

    val recorder by lazy { AndroidAudioRecorder(context) }
    val player by lazy { AndroidAudioPlayer(context) }
    val isRecording = remember { mutableStateOf(false) }
    val isPlaying = remember { mutableStateOf(false) }
    var audioFile: File? by remember { mutableStateOf(null) }


    var isTimerRunning by remember { mutableStateOf(false) }
    var elapsedTime by remember { mutableStateOf(0L) }

    val startTimer: () -> Unit = {
        isTimerRunning = true
        GlobalScope.launch {
            while (true) {
                delay(1000) // Update timer every second
                elapsedTime++
            }
        }
    }

    val stopTimer: () -> Unit = {
        isTimerRunning = false
        elapsedTime = 0L
    }

    DisposableEffect(player) {
        onDispose {
            player.stop()
        }
    }

    LaunchedEffect(player) {
        while (true) {
            delay(100)
            if (!player.isPlaying()) {
                isPlaying.value = false
            }
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isPermissionGranted ->
        if (isPermissionGranted) {
            // Permission granted, handle audio recording
            if (isRecording.value) {
                val outputFile = File(context.cacheDir, "audio_record.mp4")
                recorder.start(outputFile)
                isRecording.value = true
            } else {
                recorder.stop()
                isRecording.value = false
                audioFile = File(context.cacheDir, "audio_record.mp4")
            }
        } else {
            // Permission denied, show a toast message
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(5.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.mic),
                contentDescription = "",
                modifier = Modifier
                    .size(20.dp, 40.dp)
                    .clip(shape = RoundedCornerShape(6.dp)),
                contentScale = ContentScale.FillHeight
            )
            Text(
                text = "Feedback",
                fontWeight = FontWeight.W500,
                fontSize = 18.sp,
                color = Color.Black
            )
        }
        OutlinedCard(
            elevation = CardDefaults.cardElevation(0.dp),
            shape = RoundedCornerShape(50),
            colors = CardDefaults.outlinedCardColors(
                containerColor = Color.White
            ),
            border = BorderStroke(1.dp, Color.Gray),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                if(audioFile != null) {
                    Icon(
                        imageVector = when {
                            isPlaying.value -> Icons.Rounded.StopCircle
                            else -> Icons.Rounded.PlayCircle
                        },
                        contentDescription = if (isPlaying.value) "Stop" else "Play",
                        modifier = Modifier
                            .padding(8.dp)
                            .size(28.dp)
                            .clickable {
                                audioFile?.let {
                                    if (it.exists()) {
                                        if (!isPlaying.value) {
                                            player.play(it)
                                            isPlaying.value = true
                                        } else {
                                            player.stop()
                                            isPlaying.value = false
                                        }
                                    } else {
                                        Toast
                                            .makeText(
                                                context,
                                                "Audio file not found",
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()
                                    }
                                }
                            }
                    )
                }
                if (isTimerRunning) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = formatElapsedTime(elapsedTime),
                            fontSize = 22.sp,
                            color = Color.DarkGray,
                            fontWeight = FontWeight.W600,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
                else{
                    Text(
                        text = "Record",
                        fontSize = 22.sp,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.W600,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                Icon(
                    imageVector = when {
                        isRecording.value -> Icons.Rounded.StopCircle
                        audioFile != null -> Icons.Rounded.Delete
                        else -> Icons.Rounded.Mic
                    },
                    contentDescription = when {
                        isRecording.value -> "Stop"
                        audioFile != null -> "Delete"
                        else -> "Mic"
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .size(28.dp)
                        .clickable {
                            if (audioFile != null) {
                                audioFile?.delete()
                                audioFile = null // Set audioFile to null after deletion
                                isRecording.value = false // Ensure isRecording is set to false after deletion
                                isPlaying.value = false
                                Toast.makeText(context, "Recording deleted", Toast.LENGTH_SHORT).show()
                            } else {
                                val permissionCheckResult = ContextCompat.checkSelfPermission(
                                    context,
                                    Manifest.permission.RECORD_AUDIO
                                )
                                if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                                    if (!isRecording.value) {
                                        val outputFile = File(context.cacheDir, "audio_record.mp4")
                                        recorder.start(outputFile)
                                        isRecording.value = true
                                        startTimer()
                                    } else {
                                        recorder.stop()
                                        isRecording.value = false
                                        audioFile = File(context.cacheDir, "audio_record.mp4")
                                        stopTimer()
                                    }
                                } else {
                                    // Request a permission
                                    permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                                }
                            }
                        }
                )
            }
        }
    }
}

@SuppressLint("DefaultLocale")
private fun formatElapsedTime(seconds: Long): String {
    val minutes = (seconds % 3600) / 60
    val remainingSeconds = seconds % 60
    return String.format("%02d:%02d", minutes, remainingSeconds)
}