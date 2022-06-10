package com.setinbox.gam.handshake

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.orhanobut.hawk.Hawk
import com.setinbox.gam.R
import com.setinbox.gam.WhiteProgress
import kotlinx.android.synthetic.main.activity_hand_me_invite.*
import kotlinx.coroutines.*
import java.net.HttpURLConnection
import java.net.URL

class HandMeInvite : AppCompatActivity() {

    lateinit var jsoup: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hand_me_invite)

        jsoup = ""

        val job = GlobalScope.launch(Dispatchers.IO) {
            jsoup = coroutineTask()
            Log.d("jsoup status from global scope", jsoup)
        }

        runBlocking {
            job.join()
            Log.d("jsoup status out of global scope", jsoup)
            txtMainMain.text = jsoup

            if (jsoup == Constants.jsoupCheck) {
                Intent(applicationContext, WhiteProgress::class.java).also { startActivity(it) }
            } else {
                Intent(applicationContext, BBRAct::class.java).also { startActivity(it) }
            }
        }

    }

    private suspend fun coroutineTask(): String {
        val hawk: String? = Hawk.get(Constants.C1, "null")
        val hawkAppLink: String? = Hawk.get(Constants.DL1, "null")

        val forJsoupSetNaming: String = Constants.lru + Constants.odone + hawk
        val forJsoupSetAppLnk: String = Constants.lru + Constants.odone + hawkAppLink

        withContext(Dispatchers.IO) {
            if (hawk != null) {
                getCodeFromUrl(forJsoupSetNaming)
            } else {
                getCodeFromUrl(forJsoupSetAppLnk)
            }
        }
        return jsoup
    }

    private fun getCodeFromUrl(link: String) {

        val url = URL(link)
        val urlConnection = url.openConnection() as HttpURLConnection

        try {
            val text = urlConnection.inputStream.bufferedReader().readText()
            if (text.isNotEmpty()) {
                Log.d("jsoup status inside Url function", text)
                jsoup = text
            } else {
                Log.d("jsoup status inside Url function", "is null")
            }
        } finally {
            urlConnection.disconnect()
        }

    }

}

//
//runBlocking {
//
//    val job: Job = GlobalScope.launch(Dispatchers.IO) {
//        getAsync(applicationContext)
//    }
//    job.join()
//    val jsoup: String? = Hawk.get(Constants.asyncResult, "")
//    Log.d("cora", "cora $jsoup")
//
//    txtEr.text = jsoup
//
//    if (jsoup == "7Jp4") {
//        Intent(applicationContext, WhiteProgress::class.java).also { startActivity(it) }
//    } else {
//        Intent(applicationContext, BBRAct::class.java).also { startActivity(it) }
//    }
//    finish()
//}
//}
//
//private suspend fun getAsync(context: Context) {
//    val asyncKey = AsyncJsoup(context)
//    val asyncResult = asyncKey.getDocSecretKey()
//    Hawk.put(Constants.asyncResult, asyncResult)
//}
//}
