package com.server;

import java.io.IOException;

import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * Created by 13624 on 2018/8/12.
 */
public class TestSocket {

    public static void  main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1",10101);

        String str = "hello ,row !";

        byte[] bytes = str.getBytes();

        int length = bytes.length;

        //定义数据包得长度
        ByteBuffer buffer = ByteBuffer.allocate(4+length);//4长度部分的字节长度

        buffer.putInt(length);

        buffer.put(bytes);

        for (int i = 0 ; i<1000 ; i++){
            socket.getOutputStream().write(buffer.array());
        }
        
        socket.close();

    }



}
