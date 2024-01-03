package com.kevin.androidfcmnotificationdemo.utils

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun requestNotificationPerm(isGranted: (Boolean) -> Unit) = rememberPermissionState(
    Manifest.permission.POST_NOTIFICATIONS
) { isGranted(it) }