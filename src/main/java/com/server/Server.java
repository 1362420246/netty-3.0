package com.server;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;
//服务端
public class Server {
	
	public static void main(String[] args) {
		//服务类
		ServerBootstrap bootstrap = new ServerBootstrap();
		//线程池 
		//boss 负责监听端口  worker 负责读写
		ExecutorService boss = Executors.newCachedThreadPool();
		ExecutorService worker = Executors.newCachedThreadPool();
		//设置niosocket工厂
		bootstrap.setFactory(new NioServerSocketChannelFactory(boss,worker));
		//设置管道工厂
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				//创建管道
				ChannelPipeline pipeline = Channels.pipeline() ;
				//设置接收消息解码器
				pipeline.addLast("decoder", new StringDecoder());
				//设置发送消息编码器
				pipeline.addLast("encoder", new StringEncoder());
				//设置管道处理器
				pipeline.addLast("helloHandler", new HelloHandler());
				return pipeline;
			}
		});
		//绑定端口
		bootstrap.bind(new InetSocketAddress(10101));
		
		System.out.println("server start!!!");
	}

}




















