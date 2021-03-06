package com.client;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
//
public class HiHandler extends SimpleChannelHandler {


	//接收消息 
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		
		//未设置接收消息解码器 时 接收的不是字符，是字节流
		//ChannelBuffer message = (ChannelBuffer)e.getMessage();
		//String str = new String(message.array());
		
		//设置接收消息解码器  直接接收字符
		String str = (String)e.getMessage();
		System.out.println(str);
		
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
