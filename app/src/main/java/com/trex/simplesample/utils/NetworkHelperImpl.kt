package com.trex.simplesample.utils
import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import androidx.annotation.RequiresPermission

class NetworkHelperImpl constructor(private val context: Context) : NetworkHelper {

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    override fun isNetworkConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork?.isConnected ?: false
    }
}