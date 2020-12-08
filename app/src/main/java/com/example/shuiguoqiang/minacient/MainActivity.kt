package com.example.shuiguoqiang.minacient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shuiguoqiang.tcplib.LogUtil
import com.example.shuiguoqiang.tcplib.mina.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_connect.setOnClickListener {
            Thread(Runnable {
            MinaConnector().connect("172.17.1.19",11199,object : MinaIOListener {
                override fun onConnect(session: Any?) {
                    LogUtil.log(".........连接成功.......")
                    MinaClient.getInstance().setSession(session)
                }

                override fun onReConnect(session: Any?) {
                    MinaClient.getInstance().setSession(session)
                    LogUtil.log("........重连成功........")
                }

                override fun onmessageReceived(session: Any?, message: Any?) {

                    try {
                        var tcpRespond=TcpRespond(message as ByteArray?)
                        var cmd=tcpRespond.int
                        LogUtil.log(".........收到cmd...$cmd..........")
                        when(cmd){
                            205->{
                               var bankNum= tcpRespond.utF_8
                                var test=tcpRespond.utF_8
                                LogUtil.log("bankNumber:$bankNum  other : $test")
                            }
                        }
                    } catch (e: Exception) {
                    }

                }

            })
            }).start()
        }
        btn_send.setOnClickListener {
            var tcpBuffer=TcpBuffer(104)
            tcpBuffer.writeStr("您好吗")
            Thread(Runnable {
                MinaClient.getInstance().sendMessage(tcpBuffer.param)
            }).start()
        }
    }
}
