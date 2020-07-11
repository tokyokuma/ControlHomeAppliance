package com.example.controlhomeappliance

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import java.net.InetSocketAddress
import java.net.Socket
import java.net.SocketException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set IP Adress
        val items: Array<String?> = arrayOfNulls<String>(256)
        var ii: Int = 0

        while(ii<256){
            items[ii] = ii.toString()
            ii++
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        spinner1.adapter = adapter
        spinner2.adapter = adapter
        spinner3.adapter = adapter
        spinner4.adapter = adapter

        spinner1.setSelection(192)
        spinner2.setSelection(168)
        spinner3.setSelection(0)
        spinner4.setSelection(0)

        button_send.setOnClickListener() {
            val IPAddress = spinner1.selectedItem.toString() + '.'.toString() +
                            spinner2.selectedItem.toString() + '.'.toString() +
                            spinner3.selectedItem.toString() + '.'.toString() +
                            spinner4.selectedItem.toString()

            // server port No
            val inetSocketAddress = InetSocketAddress(IPAddress, 55555)

            val task = @SuppressLint("StaticFieldLeak")
            object : AsyncTask<InetSocketAddress, Void, Void>() {
                override fun doInBackground(vararg inetSocketAddress: InetSocketAddress): Void?{
                    var socket: Socket? = null
                    try {
                        socket = Socket()
                        socket.connect(inetSocketAddress[0])
                    }
                    catch (e: SocketException) {
                        e.printStackTrace()
                    }
                    catch (e: IOException) {
                        e.printStackTrace()
                    }
                    return null
                }
            }
            task.execute(inetSocketAddress)
            success_connect(it)
        }
    }

    fun success_connect(view: View?){
        val intent = Intent(this, ContrlAppActivity::class.java)
        startActivity(intent)
    }
}