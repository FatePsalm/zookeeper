package com.solace.zookeeper_client.zookeeper.client;

import org.apache.zookeeper.*;
import org.apache.zookeeper.ZooDefs.Ids;

import java.io.IOException;

public class DistributeServer {
	/**
	 * 添加运行参数
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		DistributeServer server = new DistributeServer();
		
		// 1 连接zookeeper集群
		server.getConnect();
		
		// 2 注册节点
		server.regist(args[0]);
		
		// 3 业务逻辑处理
		server.business();
	}

	private void business() throws InterruptedException {
	
		Thread.sleep(Long.MAX_VALUE);
	}

	/**
	 * 传入启动参数
	 * @param hostname
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	private void regist(String hostname) throws KeeperException, InterruptedException {
		//创建短暂的连接
		String path = zkClient.create("/servers/server", hostname.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		
		System.out.println(hostname +"is online ");
		
	}

	private String connectString = "hadoop01:2181,hadoop02:2181,hadoop03:2181";
	private int sessionTimeout = 2000;
	private ZooKeeper zkClient;

	/**
	 * 创建zookeeper集群连接
	 * @throws IOException
	 */
	private void getConnect() throws IOException {
		
		zkClient = new ZooKeeper(connectString , sessionTimeout , new Watcher() {
			
			@Override
			public void process(WatchedEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
