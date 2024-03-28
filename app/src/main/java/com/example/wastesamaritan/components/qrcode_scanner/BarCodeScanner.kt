package com.example.wastesamaritan.components.qrcode_scanner

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.QrCode2
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
fun BarcodeScanner(
    onScanResult: (String) -> Unit,
    onPermissionDenied: () -> Unit
) {
    val textResult = remember { mutableStateOf("") }
    val context = LocalContext.current

    val barCodeLauncher = rememberLauncherForActivityResult(ScanContract()) { result ->
        if (result.contents == null) {
            Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show()
        } else {
            textResult.value = result.contents
            onScanResult(result.contents)
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
    ButtonToScan(onClick = { checkCameraPermission() })
}

@Composable
fun ButtonToScan(onClick: () -> Unit) {
    androidx.compose.material3.Button(
        onClick = onClick,
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 5.dp,
            pressedElevation = 5.dp,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp, horizontal = 60.dp),
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(Color(0XFF87C4FF))
    ) {
        Icon(
            imageVector = Icons.Rounded.QrCode2,
            contentDescription = "",
            tint = Color.Black
        )
        androidx.compose.material3.Text(
            text = " Scan",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = MyColor.text,
            modifier = Modifier.padding(2.dp)
        )
    }
}