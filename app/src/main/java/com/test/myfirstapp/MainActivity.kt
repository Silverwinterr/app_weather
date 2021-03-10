 package com.test.myfirstapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.test.myfirstapp.request.ServerApi
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


 class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1.setOnClickListener {
val apiServer = ServerApi()
            GlobalScope.launch(Dispatchers.Main){
                val weatherResponce = apiServer.getWeatherResponce("${editText.text}").await()

            }
        }

    }
}