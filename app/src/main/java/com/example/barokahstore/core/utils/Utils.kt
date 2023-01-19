package com.example.barokahstore.core.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

fun isNetworkAvailable(context: Context): Boolean{
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    } else {
        return true
    }
}

//fun isOnline(context: Context): Boolean {
//    val connectivityManager =
//        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//    if (connectivityManager != null) {
//        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
//        if (capabilities != null) {
//            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
//                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
//                return true
//            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
//                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
//                return true
//            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
//                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
//                return true
//            }
//        }
//    }
//    return false
//}