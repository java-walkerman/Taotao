package org.taotao.fastdfs;

import java.io.IOException;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

public class FastDFS1Upload {
	
//	conf 配置文件、所在目录、加载优先顺序
//
//	配置文件名fdfs_client.conf(或使用其它文件名xxx_yyy.conf)
//
//	文件所在位置可以是项目classpath(或OS文件系统目录比如/opt/):
//	/opt/fdfs_client.conf
//	C:\Users\James\config\fdfs_client.conf
//
//	优先按OS文件系统路径读取，没有找到才查找项目classpath，尤其针对linux环境下的相对路径比如：
//	fdfs_client.conf
//	config/fdfs_client.conf
//
//	connect_timeout = 2
//	network_timeout = 30
//	charset = UTF-8
//	http.tracker_http_port = 80
//	http.anti_steal_token = no
//	http.secret_key = FastDFS1234567890
//
//	tracker_server = 10.0.11.247:22122
//	tracker_server = 10.0.11.248:22122
//	tracker_server = 10.0.11.249:22122
//
//	connection_pool.enabled = true
//	connection_pool.max_count_per_entry = 500
//	connection_pool.max_idle_time = 3600
//	connection_pool.max_wait_time_in_ms = 1000
//
//	注1：tracker_server指向您自己IP地址和端口，1-n个
//	注2：除了tracker_server，其它配置项都是可选的
//
//	.properties 配置文件、所在目录、加载优先顺序
//
//	配置文件名 fastdfs-client.properties(或使用其它文件名 xxx-yyy.properties)
//
//	文件所在位置可以是项目classpath(或OS文件系统目录比如/opt/):
//	/opt/fastdfs-client.properties
//	C:\Users\James\config\fastdfs-client.properties
//
//	优先按OS文件系统路径读取，没有找到才查找项目classpath，尤其针对linux环境下的相对路径比如：
//	fastdfs-client.properties
//	config/fastdfs-client.properties
//
//	fastdfs.connect_timeout_in_seconds = 5
//	fastdfs.network_timeout_in_seconds = 30
//	fastdfs.charset = UTF-8
//	fastdfs.http_anti_steal_token = false
//	fastdfs.http_secret_key = FastDFS1234567890
//	fastdfs.http_tracker_http_port = 80
//
//	fastdfs.tracker_servers = 10.0.11.201:22122,10.0.11.202:22122,10.0.11.203:22122
//
//	fastdfs.connection_pool.enabled = true
//	fastdfs.connection_pool.max_count_per_entry = 500
//	fastdfs.connection_pool.max_idle_time = 3600
//	fastdfs.connection_pool.max_wait_time_in_ms = 1000
//
//	注1：properties 配置文件中属性名跟 conf 配置文件不尽相同，并且统一加前缀"fastdfs."，便于整合到用户项目配置文件
//	注2：fastdfs.tracker_servers 配置项不能重复属性名，多个 tracker_server 用逗号","隔开
//	注3：除了fastdfs.tracker_servers，其它配置项都是可选的

	
//	加载原 conf 格式文件配置：
//	ClientGlobal.init("fdfs_client.conf");
//	ClientGlobal.init("config/fdfs_client.conf");
//	ClientGlobal.init("/opt/fdfs_client.conf");
//	ClientGlobal.init("C:\\Users\\James\\config\\fdfs_client.conf");
//
//	加载 properties 格式文件配置：
//	ClientGlobal.initByProperties("fastdfs-client.properties");
//	ClientGlobal.initByProperties("config/fastdfs-client.properties");
//	ClientGlobal.initByProperties("/opt/fastdfs-client.properties");
//	ClientGlobal.initByProperties("C:\\Users\\James\\config\\fastdfs-client.properties");
//
//	加载 Properties 对象配置：
//	Properties props = new Properties();
//	props.put(ClientGlobal.PROP_KEY_TRACKER_SERVERS, "10.0.11.101:22122,10.0.11.102:22122");
//	ClientGlobal.initByProperties(props);
//
//	加载 trackerServers 字符串配置：
//	String trackerServers = "10.0.11.101:22122,10.0.11.102:22122";
//	ClientGlobal.initByTrackers(trackerServers);
	

	/**
	 * 从配置文件中 读取配置
	 * 
	 * @throws Exception
	 */
	public void uploadFile() throws Exception {
		// 1 添加 fastDFS jar
		// 2 创建 FastDFS配置文件， 指定 tracker服务器地址
		// 3 加载配置文件
		ClientGlobal.init("fastdfs/fastdfs-client.conf");
		System.out.println("ClientGlobal.configInfo(): " + ClientGlobal.configInfo());
		// 4 创建 TrackClient对象
		TrackerClient client = new TrackerClient();
		System.out.println("========== 4 ");
		// 5 使用TrackClient对象获得 TrackServer对象
		TrackerServer server = client.getTrackerServer();
		System.out.println("========== 5 ");
		// 6 创建一个 StorageServer的引用 null
		StorageServer storageServer = null;
		System.out.println("========== 6 ");
		// 7 创建一个 StorageClient对象，TrackClient对象和StorageSever两个参数
		StorageClient storageClient = new StorageClient(server, storageServer);
		System.out.println("========== 7 ");
		// 8 使用StorageClient对象上传文件
		String[] result = storageClient.upload_file("/Users/hydra/Pictures/lion.jpg", "jpg", null);
		for (String string : result) {
			System.out.println("===== string:" + string);
		}
	}

	/**
	 * 从字符串中读取配置
	 */
	public void uploadFile2() {
		// 多个trackerServer逗号隔开
		// String trackerServers = "10.0.11.101:22122,10.0.11.102:22122";
		String trackerServers = "127.0.0.1:22122";
		String fileName = "/Users/hydra/Pictures/starwars.png";
		try {
			ClientGlobal.initByTrackers(trackerServers);
			System.out.println("====ClientGlobal.configInfo(): " + ClientGlobal.configInfo());
			TrackerClient client = new TrackerClient();
			TrackerServer trackerServer = client.getTrackerServer();
			// StorageServer storageServer = null;
			// StorageClient storageClient = new StorageClient(trackerServer, null);
			StorageClient storageClient = new StorageClient(trackerServer);

			// 这个参数可以指定，也可以不指定，如果指定了，可以根据 testGetFileMate()方法来获取到这里面的值
			NameValuePair nvp[] = new NameValuePair[] { 
					new NameValuePair("imageName", "starwars"),
					new NameValuePair("imageFormat", "png"), 
					new NameValuePair("others", "test_image") };
			String fileIds[] = storageClient.upload_file(fileName, "png", nvp);
			// String fileIds[] = storageClient.upload_file(fileName, "png", null);

			System.out.println(fileIds.length);
			System.out.println("组名：" + fileIds[0]);
			System.out.println("路径: " + fileIds[1]);
//			组名：group1
//			路径: M00/00/00/wKgBVl4eSKGAUezNAAa7osdPoQE400.png
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MyException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		FastDFS1Upload  ftest = new FastDFS1Upload();
		ftest.uploadFile2();
	}

}
