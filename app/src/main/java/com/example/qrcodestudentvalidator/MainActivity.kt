package com.example.qrcodestudentvalidator

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import com.example.qrcodestudentvalidator.ui.theme.QRCodeStudentValidatorTheme

class MainActivity : ComponentActivity() {
    private var scanResult by mutableStateOf("")
    private var validationStatus by mutableStateOf<String?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContent {
            QRCodeStudentValidatorTheme {
                QRScannerScreen(scanResult, validationStatus, onScanClick = {
                    startQRScanner()
                })
            }
        }
    }

    private fun startQRScanner() {
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("Escanea un código QR")
        integrator.setCameraId(0)
        integrator.setBeepEnabled(true)
        integrator.setBarcodeImageEnabled(true)
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result: IntentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                scanResult = "Escaneo cancelado"
                validationStatus = null
            } else {
                if (result.contents.startsWith("202") && result.contents.endsWith("MATRICULADO-2024-A")) {
                    if (result.contents.contains("+")) {
                        scanResult = "Código de estudiante válido, requiere revisión: ${result.contents}"
                        validationStatus = "Revision"
                    } else {
                        scanResult = "Código de estudiante válido: ${result.contents}"
                        validationStatus = "Valid"
                    }
                } else {
                    scanResult = "Código de estudiante no válido"
                    validationStatus = "Invalid"
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}

@Composable
fun QRScannerScreen(scanResult: String, validationStatus: String?, onScanClick: () -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (validationStatus) {
                "Valid" -> Image(painter = painterResource(id = R.drawable.valid_student), contentDescription = "Valid QR Code")
                "Revision" -> Image(painter = painterResource(id = R.drawable.caution_student), contentDescription = "QR Code Requires Revision")
                "Invalid" -> Image(painter = painterResource(id = R.drawable.invalid_student), contentDescription = "Invalid QR Code")
                null -> Text(text = "Escanea un código QR")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = scanResult)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onScanClick) {
                Text("Escanear QR")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QRScannerScreenPreview() {
    QRCodeStudentValidatorTheme {
        QRScannerScreen(scanResult = "Resultado del escaneo", validationStatus = null) {}
    }
}
