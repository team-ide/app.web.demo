package com.teamide.demo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import com.teamide.demo.server.BackgroundService
import com.teamide.demo.server.ServerContext
import com.teamide.demo.ui.theme.AppWebDemoTheme
import java.net.NetworkInterface

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        startForegroundService(BackgroundService.createIntent(this));
        startService(BackgroundService.createIntent(this));
        super.onCreate(savedInstanceState)
        setContent {
            AppWebDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var text = "on create"
                    if (ServerContext.get() == null) {
                        text = "context is null"
                    } else {
                        text = "http://" + getIPAddress() + ":" + ServerContext.getServerPort()
                    }
                    Greeting(text)
                }
            }
        }
    }
}

private fun getIPAddress(): String {
    try {
        val interfaces = NetworkInterface.getNetworkInterfaces() ?: return "Not Found Network"
        while (interfaces.hasMoreElements()) {
            val networkInterface = interfaces.nextElement()
            val addresses = networkInterface.inetAddresses
            while (addresses.hasMoreElements()) {
                val address = addresses.nextElement()
                if (!address.isLoopbackAddress && address.isSiteLocalAddress) {
                    return address.hostAddress?.toString() ?: ""
                }
            }
        }
    } catch (error: Throwable) {
        return error.toString()
    }
    return ""
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Web Server Url : $name",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppWebDemoTheme {
        Greeting("GreetingPreview")
    }
}