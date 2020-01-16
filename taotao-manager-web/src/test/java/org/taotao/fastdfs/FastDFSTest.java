package org.taotao.fastdfs;

import java.io.IOException;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

/**
 * storage.conf: http.server_port = 8888 </br>
 * tracker.conf: http.server_port = 8099 </br>
 * client.conf: http.tracker_server_port = 99 </br>
 * tracker_server = 127.0.0.1:22122
 * 
 * @author hydra
 *
 */
///usr/local/bin/fdfs_trackerd  /etc/fdfs/tracker.conf  restart
///usr/local/bin/fdfs_storaged /etc/fdfs/storage.conf  restart
//ps aux|grep fdfs
// /usr/local/bin/fdfs_upload_file /etc/fdfs/client.conf /Users/hydra/Pictures/grass.jpg
///   /usr/local/bin/fdfs_test /etc/fdfs/client.conf upload /Users/hydra/Pictures/grass.jpg
 
//fdfs_upload_file /etc/fdfs/client.conf /Users/hydra/Pictures/grass.jpg
public class FastDFSTest {

	// @Test
	/**
	 * 从配置文件中 读取配置
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
	

}
