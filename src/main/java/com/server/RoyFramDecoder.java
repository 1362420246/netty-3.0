package com.server;


import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

/**
 * Netty粘包和拆包解决方案
 Netty提供了多个解码器，可以进行分包的操作，分别是：
 * LineBasedFrameDecoder
 * DelimiterBasedFrameDecoder（添加特殊分隔符报文来分包）
 * FixedLengthFrameDecoder（使用定长的报文来分包）
 * LengthFieldBasedFrameDecoder
 *
 * 自定义  decode
 *
 */
public class RoyFramDecoder extends FrameDecoder {

    //数据包基本长度
    private static  final  int BASE_LEN = 4;

    @Override
    protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer) throws Exception {

        //1.首先收到数据之后，先要判断buffer中刻度的数据包的长度必须要大于定义好的数据包的基本长度
        if(buffer.readableBytes() > BASE_LEN){

            //做标记:记住当前buffer读指针位置
            buffer.markReaderIndex();

            //读长度
            final int datalen = buffer.readInt();//读了四个字节

            if(buffer.readableBytes() < datalen){
                //说明数据包不完整。需要继续等待下一个数据包到来

                //数据存放到缓存之前，重置指针
                buffer.resetReaderIndex();

                return null;
            }

            //读数据本身
            byte[] bytes = new byte[datalen];
            buffer.readBytes(bytes);

            return  new String(bytes);
        }

        // return null; 表示此时的数据包不完整，需要等待下一个数据包的到来
        return null;
    }
}
