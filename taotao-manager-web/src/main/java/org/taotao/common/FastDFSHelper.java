package org.taotao.common;

import java.io.IOException;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

public class FastDFSHelper {

	private static FastDFSHelper helper = null;

	private FastDFSHelper() {
	}

	public static FastDFSHelper helper() {
		if (helper == null) {
			helper = new FastDFSHelper();
		}
		return helper;
	}

	/**
	 * 从配置文件中 读取配置
	 * 
	 * @throws Exception
	 */
	private void uploadFileTest() throws Exception {
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
	public String[] uploadFile(byte[] bytes, String fileSuffix, NameValuePair[] nvp) {
		// 多个trackerServer逗号隔开
		// String trackerServers = "10.0.11.101:22122,10.0.11.102:22122";
		String trackerServers = "127.0.0.1:22122";
		// String fileName = "/Users/hydra/Pictures/starwars.png";
		try {
			ClientGlobal.initByTrackers(trackerServers);
			System.out.println("====ClientGlobal.configInfo(): " + ClientGlobal.configInfo());
			TrackerClient client = new TrackerClient();
			TrackerServer trackerServer = client.getTrackerServer();
			// StorageServer storageServer = null;
			// StorageClient storageClient = new StorageClient(trackerServer, null);
			StorageClient storageClient = new StorageClient(trackerServer);

			// 这个参数可以指定，也可以不指定，如果指定了，可以根据 testGetFileMate()方法来获取到这里面的值
			// NameValuePair nvp[] = new NameValuePair[] {
			// new NameValuePair("imageName", "starwars"),
			// new NameValuePair("imageFormat", "png"),
			// new NameValuePair("others", "test_image") };
			String[] fileIds = storageClient.upload_file(bytes, fileSuffix, nvp);
			// String fileIds[] = storageClient.upload_file(fileName, "png", null);

			System.out.println(fileIds.length);
			System.out.println("====== 上传文件后  组名：" + fileIds[0]);
			System.out.println("====== 上传文件后  路径: " + fileIds[1]);

			return fileIds;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MyException e) {
			e.printStackTrace();
		}
		return null;

	}

}
