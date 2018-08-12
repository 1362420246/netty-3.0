package com.server;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandler;//接口
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
//管道处理器   
public class HelloHandler extends SimpleChannelHandler {//处理消息接收和写

	//接收消息 
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
//		System.out.println("messageReceived");

		//未设置接收消息解码器 时 接收的不是字符，是字节流
		//ChannelBuffer message = (ChannelBuffer)e.getMessage();
		//String str = new String(message.array());
		
		//设置接收消息解码器  直接接收字符
		String str = (String)e.getMessage();
		System.out.println(str);
		
		//回写数据
		//获取会话的通道
		Channel channel = ctx.getChannel();
		//未设置发送消息编码器  时 发送的是字节流
		//ChannelBuffer copiedBuffer = ChannelBuffers.copiedBuffer("hi".getBytes());
		//channel.write(copiedBuffer);//发送ChannelBuffer类型
		
		//设置发送消息编码器  直接发送字符
//		channel.write("hi");
		
		super.messageReceived(ctx, e);
	}

	//异常触发
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		Throwable cause = e.getCause();
		cause.printStackTrace();
		System.out.println("exceptionCaught");
		super.exceptionCaught(ctx, e);
	}

	//链接成功   新连接，通常用来检测IP是否是黑名单
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		System.out.println("channelConnected");
		//关闭通道
		//ChannelFuture close = ctx.getChannel().close();
		super.channelConnected(ctx, e);
	}

	//断开链接：只有在连接建立后断开才会调用
	//可以再用户断线的时候清楚用户的缓存数据等
	@Override
	public void channelDisconnected(ChannelHandlerContext ctx,
			ChannelStateEvent e) throws Exception {
		System.out.println("channelDisconnected");
		super.channelDisconnected(ctx, e);
	}

	//channel关闭的时候触发  ：无论连接是否成功都会调用关闭资源
	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		System.out.println("channelClosed");
		super.channelClosed(ctx, e);
	}

}
