package com.example.wastesamaritan.screens.home_screen

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.QrCode
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.wastesamaritan.ui.theme.MyColor
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

@Composable
fun HomeScreenScanner(
    viewModel: HomeScreenViewModel,
    onPermissionDenied: () -> Unit,
    onError: () -> Unit
) {
    val context = LocalContext.current

    // Observe the scan result from the view model
    val scannedResult by viewModel.scannedResult.observeAsState()

    /// Convert the observed string state to ButtonState
    val buttonState = scannedResult?.let {
        ButtonState.values().firstOrNull { buttonState -> buttonState.text == it }
    } ?: ButtonState.START

    val barCodeLauncher = rememberLauncherForActivityResult(ScanContract()) { result ->
        if (result.contents == null) {
            Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show()
        } else {
            val scannedResult = result.contents
            if (scannedResult == "Start" || scannedResult == "Stop") {
                viewModel.setScannedResult(scannedResult)
            } else {
                onError()
            }
        }
    }

    fun showCamera() {
        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
        options.setPrompt("Scan a QR Code")
        options.setCameraId(0)
        options.setBeepEnabled(false)
        options.setOrientationLocked(false)

        barCodeLauncher.launch(options)
    }

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            showCamera()
        } else {
            onPermissionDenied()
        }
    }

    fun checkCameraPermission() {
        val ctx = context ?: return
        if (ContextCompat.checkSelfPermission(
                ctx,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            showCamera()
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(
                ctx as androidx.activity.ComponentActivity,
                Manifest.permission.CAMERA
            )
        ) {
            Toast.makeText(ctx, "Permission denied", Toast.LENGTH_SHORT).show()
            onPermissionDenied()
        } else {
            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }
    ButtonToScan(buttonState = buttonState,onClick = { checkCameraPermission() })
}

@Composable
fun ButtonToScan(buttonState: ButtonState, onClick: () -> Unit) {

    // Use the provided buttonState directly
    var currentButtonState by remember { mutableStateOf(buttonState) }

    // Define the button click handler to toggle the button state
    val onButtonClick: () -> Unit = {
        currentButtonState = when (buttonState) {
            ButtonState.START -> ButtonState.STOP
            ButtonState.STOP -> ButtonState.START
        }
        onClick() // Call the provided onClick callback
    }

    Button(
        onClick = onButtonClick,
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 1.dp,
            pressedElevation = 5.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp, horizontal = 8.dp),
        shape = RoundedCornerShape(24),
        colors = when (currentButtonState) { // Use currentButtonState here
            ButtonState.START -> ButtonDefaults.buttonColors(MyColor.primary)
            ButtonState.STOP -> ButtonDefaults.buttonColors(Color.Red)
        }
    ) {
        Icon(
            imageVector = Icons.Rounded.QrCode,
            contentDescription = "",
            tint = Color.White
        )
        Text(
            text = currentButtonState.text, // Use currentButtonState text here
            textAlign = TextAlign.Center,
            fontSize = 22.sp,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
    }
}

enum class ButtonState(val text: String) {
    START("Start"),
    STOP("Stop")
}