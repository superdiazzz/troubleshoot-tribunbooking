package com.example.teswebtribunbooking

import android.os.Build
import android.view.View
import android.webkit.CookieManager
import android.webkit.CookieSyncManager
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun MyWebView(url: String,
              webViewClient: WebViewClient = WebViewClient(),
              webChromeClient: WebChromeClient = WebChromeClient()
){


    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            WebView(context).apply {
                setInitialScale(1)
                this.webChromeClient = webChromeClient
                this.webViewClient = webViewClient
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                    setLayerType(View.LAYER_TYPE_HARDWARE, null)
                else
                    setLayerType(View.LAYER_TYPE_SOFTWARE, null)

                this.settings.apply {
                    userAgentString = "Mozilla/5.0 (Linux; Android 4.1.1; HTC One X Build/JRO03C) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.58 Mobile Safari/537.31"
                    javaScriptEnabled = true
                    loadWithOverviewMode = true
                    setGeolocationEnabled(false)
                    setNeedInitialFocus(false)
                    cacheMode = WebSettings.LOAD_NO_CACHE
                    useWideViewPort = true
                }

//                val cookieSyncManager = CookieSyncManager.createInstance(context)
                val cookieManager = CookieManager.getInstance()
                cookieManager.setAcceptCookie(true)
                cookieManager.removeSessionCookie()

                val cookieString = "XKMPSS=L0dBVDAxa2hQcGV6U0dsaThQY242alFHNFRkN1M5S0RkUjYzYzRFZEMzMEthalhPTXp3OUNNOVRjajdqZC9xdQ==" // --> important for SSO KOMPAS
                val cookieString2 = "kmps_usrid=48f68db1fa9a8e70cd00a8fd62856b18"
                val cookieString3 = "trbn_prof=M29KVzhENEppaHlONE1PNk1RclZrMXVWMFFUKzZSZ0lBTGZ5b3YydU9BSHNtTEUrTGI0Q2l2LzE5R2FzNXVQVjByU2RsdU0wYktIRmtOMHVwaS9nbGFzMFRoNnRNK2V3eWFKUGlZK3l4NXgwb1hqTDRvS3BGaDg2TmYrTUMvVWpoWlZhd3JOMFBpYzZZeHpaOGd3bEVBPT0=" // important for TRIBUNJUALBELI
                val cookieString4 = "usermail=b85ae95d9177a6bed1e800b9f040e93afa87403a0e0927f759164c9b5e712476"


                val baseUrl = "booking.tribunnews.com"

                cookieManager.setCookie(baseUrl, cookieString)
                cookieManager.setCookie(baseUrl, cookieString2)
                cookieManager.setCookie(baseUrl, cookieString3)
                cookieManager.setCookie(baseUrl, cookieString4)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    cookieManager.setAcceptThirdPartyCookies(this, true)
                }
                cookieManager.flush()
//                cookieSyncManager.sync()


            }
        }, update = { webview ->
            webview.loadUrl(url)
        })

}