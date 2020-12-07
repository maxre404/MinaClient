package com.example.shuiguoqiang.minacient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shuiguoqiang.minacient.mina.MinaConnector
import com.example.shuiguoqiang.minacient.mina.MinaIOListener
import kotlinx.android.synthetic.main.activity_main.*
import org.apache.mina.core.session.IoSession

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_connect.setOnClickListener {
            Thread(Runnable {
            MinaConnector().connect("172.17.1.19",11199,object :MinaIOListener{
                override fun onConnect(session: IoSession?) {
                    LogUtil.log(".........连接成功.......")
                }

                override fun onReConnect(session: IoSession?) {
                    LogUtil.log("........重连成功........")
                }

                override fun onmessageReceived(session: IoSession?, message: Any?) {
                    LogUtil.log(".........收到消息........")
                }

            })
            }).start()
        }
    }
}
