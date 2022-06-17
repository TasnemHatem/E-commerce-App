package com.example.e_commerceapp.utils

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.*
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.InetSocketAddress
import javax.net.SocketFactory
import android.net.ConnectivityManager.NetworkCallback
import androidx.annotation.WorkerThread


val TAG = "C-Manager"

class ConnectionLiveData(context: Context) : LiveData<Boolean>() {
    private val connectivityManager: ConnectivityManager = context.getSystemService((CONNECTIVITY_SERVICE)) as ConnectivityManager
    private lateinit var connectivityManagerCallback: NetworkCallback

    override fun setValue(value: Boolean?) {
        if (getValue() == value)
            return
        super.setValue(value)
    }

    override fun onActive() {
        super.onActive()
        updateConnection()
        connectivityManagerCallback = object : NetworkCallback() {
            @WorkerThread
            override fun onCapabilitiesChanged(network: Network,
                                               networkCapabilities: NetworkCapabilities
            ) {
                super.onCapabilitiesChanged(network, networkCapabilities)
                if (networkCapabilities.hasCapability(
                        NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    && networkCapabilities.hasCapability(
                        NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
                    postValue(true)
                }
            }

            @WorkerThread
            override fun onLost(network: Network) {
                postValue(false)
            }
        }
        connectivityManager.registerDefaultNetworkCallback(connectivityManagerCallback)
    }

    override fun onInactive() {
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(connectivityManagerCallback)
    }

    @Suppress("DEPRECATION")
    private fun updateConnection() {
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        value = activeNetwork?.isConnected == true
    }
}

//    private lateinit var networkCallback: ConnectivityManager.NetworkCallback
//    private val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
//    private val validNetworks: MutableSet<Network> = HashSet()
//
//    private fun checkValidNetworks() {
//        postValue(validNetworks.size > 0)
//    }
//
//    override fun onActive() {
//        networkCallback = createNetworkCallback()
//        val networkRequest = NetworkRequest.Builder()
//            .addCapability(NET_CAPABILITY_INTERNET)
//            .build()
//        cm.registerNetworkCallback(networkRequest, networkCallback)
//    }
//
//    override fun onInactive() {
//        cm.unregisterNetworkCallback(networkCallback)
//    }
//
//    private fun createNetworkCallback() = object : ConnectivityManager.NetworkCallback() {
//
//        /*
//          Called when a network is detected. If that network has internet, save it in the Set.
//          Source: https://developer.android.com/reference/android/net/ConnectivityManager.NetworkCallback#onAvailable(android.net.Network)
//         */
//        override fun onAvailable(network: Network) {
//            Log.d(TAG, "onAvailable: ${network}")
//            val networkCapabilities = cm.getNetworkCapabilities(network)
//            val hasInternetCapability = networkCapabilities?.hasCapability(NET_CAPABILITY_INTERNET)
//            Log.d(TAG, "onAvailable: ${network}, $hasInternetCapability")
//            if (hasInternetCapability == true) {
//                // check if this network actually has internet
//                CoroutineScope(Dispatchers.IO).launch {
//                    val hasInternet = DoesNetworkHaveInternet.execute(network.socketFactory)
//                    if(hasInternet){
//                        withContext(Dispatchers.Main){
//                            Log.d(TAG, "onAvailable: adding network. ${network}")
//                            validNetworks.add(network)
//                            checkValidNetworks()
//                        }
//                    }
//                }
//            }
//        }
//
//        /*
//          If the callback was registered with registerNetworkCallback() it will be called for each network which no longer satisfies the criteria of the callback.
//          Source: https://developer.android.com/reference/android/net/ConnectivityManager.NetworkCallback#onLost(android.net.Network)
//         */
//        override fun onLost(network: Network) {
//            Log.d(TAG, "onLost: ${network}")
//            validNetworks.remove(network)
//            checkValidNetworks()
//        }
//
//    }
//
//}

object DoesNetworkHaveInternet {

    // Make sure to execute this on a background thread.
    fun execute(socketFactory: SocketFactory): Boolean {
        return try{
            Log.d(TAG, "PINGING google.")
            val socket = socketFactory.createSocket() ?: throw IOException("Socket is null.")
            socket.connect(InetSocketAddress("8.8.8.8", 53), 1500)
            socket.close()
            Log.d(TAG, "PING success.")
            true
        }catch (e: IOException){
            Log.e(TAG, "No internet connection. ${e}")
            false
        }
    }
}