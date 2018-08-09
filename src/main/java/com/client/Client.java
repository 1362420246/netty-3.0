package com.client;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;
//客户端
public class Client {
	
	public static void main(String[] args) {
		//服务类
		ClientBootstrap bootstrap = new ClientBootstrap();
		
		//线程池
		ExecutorService boss = Executors.newCachedThreadPool();
		ExecutorService worker = Executors.newCachedThreadPool();
		
		//socket工厂
		bootstrap.setFactory(new NioClientSocketChannelFactory(boss,worker));
		
		//管道工厂
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				//创建管道
				ChannelPipeline pipeline = Channels.pipeline();
				//编码和解码器
				pipeline.addLast("decoder", new StringDecoder());
				pipeline.addLast("encoder", new StringEncoder());
				//管道处理器
				pipeline.addLast("hiHandler", new HiHandler());
				return pipeline;
			}
		});
		
		//链接服务端
		ChannelFuture connet = bootstrap.connect(new InetSocketAddress("127.0.0.1",10101));
		//获取通道
		Channel channel = connet.getChannel();
		System.out.println("client start");
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("请输入");
			channel.write(scanner.next());
		}
	}

}












