package com.example.controlhomeappliance

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_contrl_app.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedWriter
import java.io.IOException
import java.io.OutputStreamWriter
import java.net.InetSocketAddress
import java.net.Socket
import java.net.SocketException

class ContrlAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contrl_app)

        light_on.setOnClickListener() {
            // server port No
            val inetSocketAddress = InetSocketAddress("IP", 55555)
            val task = @SuppressLint("StaticFieldLeak")
            object : AsyncTask<InetSocketAddress, Void, Void>() {
                override fun doInBackground(vararg inetSocketAddress: InetSocketAddress): Void? {
                    var socket01: Socket? = null
                    try {
                        socket01 = Socket()
                        socket01.connect(inetSocketAddress[0])
                        val writer = BufferedWriter(OutputStreamWriter(socket01.getOutputStream()))
                        writer.write(light_on.text.toString())
                        writer.close()
                    } catch (e: SocketException) {
                        e.printStackTrace()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    return null
                }
            }
            task.execute(inetSocketAddress)
        }

        light_off.setOnClickListener() {
            // server port No
            val inetSocketAddress = InetSocketAddress("IP", 55555)
            val task = @SuppressLint("StaticFieldLeak")
            object : AsyncTask<InetSocketAddress, Void, Void>() {
                override fun doInBackground(vararg inetSocketAddress: InetSocketAddress): Void? {
                    var socket: Socket? = null
                    try {
                        socket = Socket()
                        socket.connect(inetSocketAddress[0])
                        val writer = BufferedWriter(OutputStreamWriter(socket.getOutputStream()))
                        writer.write(light_off.text.toString())
                        writer.close()

                    } catch (e: SocketException) {
                        e.printStackTrace()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    return null
                }
            }
            task.execute(inetSocketAddress)
        }
    }
}