# QR Code Student Validator

QR Code Student Validator es una aplicación Android que escanea códigos QR para validar si pertenecen a estudiantes registrados. La aplicación muestra una imagen y un mensaje indicando si el código es válido, requiere revisión o es inválido.

## Características

- Escanea códigos QR para validación.
- Valida que el código QR no contenga espacios.
- Diferencia entre códigos válidos, códigos que requieren revisión (contienen "+") y códigos inválidos.
- Muestra imágenes correspondientes según la validez del código QR.

## Requisitos

- Android Studio
- Gradle
- Dispositivo Android o emulador

## Instalación

1. Clona este repositorio:
2. Abre el proyecto en Android Studio.
3. Asegúrate de tener las siguientes dependencias en tu archivo build.gradle:
   dependencies {
    implementation "androidx.core:core-ktx:1.9.0"
    implementation "androidx.appcompat:appcompat:1.5.1"
    implementation "com.google.zxing:core:3.3.3"
    implementation "com.journeyapps:zxing-android-embedded:4.3.0"
    implementation "androidx.compose.ui:ui:1.3.1"
    implementation "androidx.compose.material3:material3:1.0.0"
    implementation "androidx.compose.ui:ui-tooling-preview:1.3.1"
    implementation "androidx.lifecycle:lifecycle-runtime-ktx:2.5.1"
    implementation "androidx.activity:activity-compose:1.6.1"
}

4. Sincroniza el proyecto con Gradle.
5. Ejecuta la aplicación en tu dispositivo o emulador.

## Uso
-Abre la aplicación en tu dispositivo Android.
-Presiona el botón "Escanear QR" para iniciar el escaneo.
-Apunta la cámara hacia el código QR.
-La aplicación mostrará un mensaje y una imagen indicando si el código QR es válido, requiere revisión o es inválido.

##Estructura del Proyecto
-MainActivity.kt: Contiene la lógica principal para escanear y validar códigos QR.
-QRScannerScreen.kt: Composable que define la interfaz de usuario.
-res/: Contiene los recursos de la aplicación, incluyendo las imágenes para cada estado de validación.

