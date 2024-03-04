
import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Mic
import androidx.compose.material.icons.rounded.PlayCircle
import androidx.compose.material.icons.rounded.Stop
import androidx.compose.material.icons.rounded.StopCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.wastesamaritan.R
import com.example.wastesamaritan.components.RecordAudio.AndroidAudioPlayer
import com.example.wastesamaritan.components.RecordAudio.AndroidAudioRecorder
import com.example.wastesamaritan.ui.theme.MyColor
import java.io.File

@Composable
fun FeedbackSection() {
    val context = LocalContext.current

    val recorder by lazy { AndroidAudioRecorder(context) }
    val player by lazy { AndroidAudioPlayer(context) }
    val isRecording = remember { mutableStateOf(false) }
    val isPlaying = remember { mutableStateOf(false) }
    var audioFile:File? = null

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isPermissionGranted ->
        if (isPermissionGranted) {
            // Permission granted, handle audio recording
            if (isRecording.value) {
                val outputFile = File(context.cacheDir, "audio_record.mp4")
                recorder.start(outputFile)
                isRecording.value = true
            }
            else {
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
                fontSize = 20.sp,
                color = Color.Black
            )
        }
        Row() {
            Button(
                onClick = {
                    val permissionCheckResult = ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.RECORD_AUDIO
                    )
                    if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                        if(!isRecording.value) {
                            val outputFile = File(context.cacheDir, "audio_record.mp4")
                            recorder.start(outputFile)
                            isRecording.value = true
                        }else{
                            recorder.stop()
                            isRecording.value = false
                            audioFile = File(context.cacheDir,"audio_record.mp4")
                        }
                    } else {
                        // Request a permission
                        permissionLauncher.launch(Manifest.permission.CAMERA)
                    }
                },
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 1.dp,
                    pressedElevation = 5.dp,
                ),
                modifier = Modifier
                    .padding(5.dp),
                shape = RoundedCornerShape(20),
                colors = ButtonDefaults.buttonColors(MyColor.primary)
            ) {
                Icon(
                    imageVector = if (isRecording.value) Icons.Rounded.Stop else Icons.Rounded.Mic,
                    contentDescription = if (isRecording.value) "Stop" else "Mic",
                    modifier = Modifier.padding(1.dp)
                )
            }
            Button(
                onClick = {
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
                            Toast.makeText(context, "Audio file not found", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 1.dp,
                    pressedElevation = 5.dp,
                ),
                modifier = Modifier
                    .padding(5.dp),
                shape = RoundedCornerShape(20),
                colors = ButtonDefaults.buttonColors(MyColor.primary)
            ) {
                Icon(
                    imageVector = if (isPlaying.value) Icons.Rounded.StopCircle else Icons.Rounded.PlayCircle,
                    contentDescription = if (isPlaying.value) "Stop" else "Play",
                    modifier = Modifier.padding(1.dp)
                )
            }
        }
    }
}