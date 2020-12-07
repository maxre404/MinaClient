package com.example.shuiguoqiang.minacient.mina;

import org.apache.mina.core.session.IoSession;

public interface MinaIOListener {

    void onConnect(IoSession session);

    void onReConnect(IoSession session);


    void onmessageReceived(IoSession session, Object message);

}
