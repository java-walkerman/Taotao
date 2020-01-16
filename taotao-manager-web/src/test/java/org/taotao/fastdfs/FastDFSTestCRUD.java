package org.taotao.fastdfs;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * 文件上传：upload_file 
 * 文件下载：download_file 
 * 文件信息：get_file_info 
 * 用户自定义元信息：get_metadata
 * 删除文件：delete_file
 * 
 * @author hydra
 *
 */
public class FastDFSTestCRUD {

	private static final String FASTDFS_CLIENT_PROPERTIES = "fastdfs-client.properties";
	private static String dfsFileName = "M00/00/00/wKg6ZVyM1dKAeC7_AAADxVvVFj019.java";

//	@Test
	public void testUpload() throws Exception { // 上传文件
		TrackerServer trackerServer = null;
		try {
			ClientGlobal.initByProperties(FASTDFS_CLIENT_PROPERTIES);
			TrackerClient tracker = new TrackerClient();
			// trackerServer = tracker.getConnection();
			trackerServer = tracker.getTrackerServer();
			String local_filename = "F://Debug.java";

			StorageClient storageClient = new StorageClient(trackerServer, null);
			NameValuePair valuePair = new NameValuePair();
			valuePair.setName("name");
			valuePair.setValue("tenmao test");
			NameValuePair[] pairs = { valuePair };
			String[] fileIds = storageClient.upload_file(local_filename, null, pairs);

			System.out.println("组名：" + fileIds[0]);
			System.out.println("路径: " + fileIds[1]);
		} finally {
			// try {
			// if (null != trackerServer) {
			// trackerServer.close();
			// }
			// } catch (IOException e) {
			// e.printStackTrace();
			// }
		}
	}

//	@Test
	public void testDownload() throws Exception { // 下载文件
		TrackerServer trackerServer = null;

		try {
			String groupName = "group1";
			String filePath = dfsFileName;
			ClientGlobal.initByProperties(FASTDFS_CLIENT_PROPERTIES);

			TrackerClient tracker = new TrackerClient();
			// trackerServer = tracker.getConnection();
			trackerServer = tracker.getTrackerServer();

			StorageClient storageClient = new StorageClient(trackerServer, null);
			byte[] bytes = storageClient.download_file(groupName, filePath);

			Assert.assertNotNull(bytes);
			String storePath = "F://hello.java";
			try (OutputStream out = new FileOutputStream(storePath)) {
				out.write(bytes);
			}
		} finally {
			// try {
			// if (null != trackerServer)
			// trackerServer.close();
			// } catch (IOException e) {
			// e.printStackTrace();
			// }
		}
	}

//	@Test
	public void testGetFileInfo() { // 获取文件信息
		TrackerServer trackerServer = null;

		try {
			String groupName = "group1";
			String filePath = dfsFileName;
			ClientGlobal.initByProperties(FASTDFS_CLIENT_PROPERTIES);

			TrackerClient tracker = new TrackerClient();
			// trackerServer = tracker.getConnection();
			trackerServer = tracker.getTrackerServer();

			StorageClient storageClient = new StorageClient(trackerServer, null);
			FileInfo file = storageClient.get_file_info(groupName, filePath);
			System.out.println("ip--->" + file.getSourceIpAddr());
			System.out.println("文件大小--->" + file.getFileSize());
			System.out.println("文件上传时间--->" + file.getCreateTimestamp());
			System.out.println(file.getCrc32());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// try {
			// if (null != trackerServer)
			// trackerServer.close();
			// } catch (IOException e) {
			// e.printStackTrace();
			// }
		}
	}

//	@Test
	public void testGetFileMate() throws Exception { // 获取文件的原数据类型
		TrackerServer trackerServer = null;

		try {
			String groupName = "group1";
			String filePath = dfsFileName;
			ClientGlobal.initByProperties(FASTDFS_CLIENT_PROPERTIES);

			TrackerClient tracker = new TrackerClient();
			// trackerServer = tracker.getConnection();
			trackerServer = tracker.getTrackerServer();

			StorageClient storageClient = new StorageClient(trackerServer, null);

			// 这个值是上传的时候指定的NameValuePair
			NameValuePair[] pairs = storageClient.get_metadata(groupName, filePath);
			if (null != pairs && pairs.length > 0) {
				for (NameValuePair nvp : pairs) {
					System.out.println(nvp.getName() + ":" + nvp.getValue());
				}
			}
		} finally {
			// try {
			// if (null != trackerServer)
			// trackerServer.close();
			// } catch (IOException e) {
			// e.printStackTrace();
			// }
		}
	}

//	@Test
	public void testDelete() throws Exception { // 删除文件
		TrackerServer trackerServer = null;

		try {
			String groupName = "group1";
			String filePath = dfsFileName;
			ClientGlobal.initByProperties(FASTDFS_CLIENT_PROPERTIES);

			TrackerClient tracker = new TrackerClient();
			// trackerServer = tracker.getConnection();
			trackerServer = tracker.getTrackerServer();

			StorageClient storageClient = new StorageClient(trackerServer, null);
			int i = storageClient.delete_file(groupName, filePath);
			System.out.println(i == 0 ? "删除成功" : "删除失败:" + i);
		} finally {
			// try {
			// if (null != trackerServer)
			// trackerServer.close();
			// } catch (IOException e) {
			// e.printStackTrace();
			// }
		}
	}
}
