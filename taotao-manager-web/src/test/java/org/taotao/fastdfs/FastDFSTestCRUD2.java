package org.taotao.fastdfs;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

/**
 * #fdfs_client.conf 配置 </br>
 * connect_timeout = 10</br>
 * network_timeout = 30</br>
 * charset = UTF-8</br>
 * http.tracker_http_port = 8888</br>
 * http.anti_steal_token = no</br>
 * http.secret_key = FastDFS1234567890</br>
 * </br>
 * tracker_server = 100.98.22.253:22122</br>
 * #如果有多台服务，指定集群的IP</br>
 * #tracker_server = 192.168.10.250:22122</br>
 * 
 * @author hydra
 *
 */
public class FastDFSTestCRUD2 {
	// fdfs_client 核心配置文件
	public String conf_filename = "src/main/resources/fdfs_client.conf";

//	@Test
	public void testUpload() { // 上传文件
		TrackerServer trackerServer = null;
		StorageServer storageServer = null;

		try {
			ClientGlobal.init(conf_filename);
			TrackerClient tracker = new TrackerClient();
			// trackerServer = tracker.getConnection();
			trackerServer = tracker.getTrackerServer();
//			StorageClient1 client = new StorageClient1(trackerServer, storageServer);

			// 要上传的文件路径
			String local_filename = "/Users/shenwei/Desktop/1.png";
			// 这个参数可以指定，也可以不指定，如果指定了，可以根据 testGetFileMate()方法来获取到这里面的值
			// NameValuePair nvp [] = new NameValuePair[]{
			// new NameValuePair("age", "18"),
			// new NameValuePair("sex", "male")
			// };

			StorageClient storageClient = new StorageClient(trackerServer, storageServer);
			// String fileIds[] = storageClient.upload_file(local_filename, "png", nvp);
			String fileIds[] = storageClient.upload_file(local_filename, "png", null);

			System.out.println(fileIds.length);
			System.out.println("组名：" + fileIds[0]);
			System.out.println("路径: " + fileIds[1]);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// try {
			// if (null != storageServer)
			// storageServer.close();
			// if (null != trackerServer)
			// trackerServer.close();
			// } catch (IOException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
		}
	}

//	@Test
	public void testDownload() { // 下载文件
		TrackerServer trackerServer = null;
		StorageServer storageServer = null;

		try {
			String groupName = "group1";
			String filePath = "M00/00/00/ZGIW_lpujW-ADvpRAAblmT4ACuo125.png";
			ClientGlobal.init(conf_filename);

			TrackerClient tracker = new TrackerClient();
			// trackerServer = tracker.getConnection();
			trackerServer = tracker.getTrackerServer();

			StorageClient storageClient = new StorageClient(trackerServer, storageServer);
			byte[] bytes = storageClient.download_file(groupName, filePath);

			String storePath = "/Users/shenwei/Desktop/download.png";
			OutputStream out = new FileOutputStream(storePath);
			out.write(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// try {
			// if (null != storageServer)
			// storageServer.close();
			// if (null != trackerServer)
			// trackerServer.close();
			// } catch (IOException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
		}
	}

//	@Test
	public void testGetFileInfo() { // 获取文件信息
		TrackerServer trackerServer = null;
		StorageServer storageServer = null;

		try {
			String groupName = "group1";
			String filePath = "M00/00/00/ZGIW_lpujW-ADvpRAAblmT4ACuo125.png";
			ClientGlobal.init(conf_filename);

			TrackerClient tracker = new TrackerClient();
			// trackerServer = tracker.getConnection();
			trackerServer = tracker.getTrackerServer();

			StorageClient storageClient = new StorageClient(trackerServer, storageServer);
			FileInfo file = storageClient.get_file_info(groupName, filePath);
			System.out.println("ip--->" + file.getSourceIpAddr());
			System.out.println("文件大小--->" + file.getFileSize());
			System.out.println("文件上传时间--->" + file.getCreateTimestamp());
			System.out.println(file.getCrc32());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// try {
			// if (null != storageServer)
			// storageServer.close();
			// if (null != trackerServer)
			// trackerServer.close();
			// } catch (IOException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
		}
	}

//	@Test
	public void testGetFileMate() { // 获取文件的原数据类型
		TrackerServer trackerServer = null;
		StorageServer storageServer = null;

		try {
			String groupName = "group1";
			String filePath = "M00/00/00/ZGIW_lpujW-ADvpRAAblmT4ACuo125.png";
			ClientGlobal.init(conf_filename);

			TrackerClient tracker = new TrackerClient();
			// trackerServer = tracker.getConnection();
			trackerServer = tracker.getTrackerServer();

			StorageClient storageClient = new StorageClient(trackerServer, storageServer);

			// 这个值是上传的时候指定的NameValuePair
			NameValuePair nvps[] = storageClient.get_metadata(groupName, filePath);
			if (null != nvps && nvps.length > 0) {
				for (NameValuePair nvp : nvps) {
					System.out.println(nvp.getName() + ":" + nvp.getValue());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// try {
			// if (null != storageServer)
			// storageServer.close();
			// if (null != trackerServer)
			// trackerServer.close();
			// } catch (IOException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
		}
	}

//	@Test
	public void testDelete() { // 删除文件
		TrackerServer trackerServer = null;
		StorageServer storageServer = null;

		try {
			String groupName = "group1";
			String filePath = "M00/00/00/ZGIW_lpujW-ADvpRAAblmT4ACuo125.png";
			ClientGlobal.init(conf_filename);

			TrackerClient tracker = new TrackerClient();
			// trackerServer = tracker.getConnection();
			trackerServer = tracker.getTrackerServer();

			StorageClient storageClient = new StorageClient(trackerServer, storageServer);
			int i = storageClient.delete_file(groupName, filePath);
			System.out.println(i == 0 ? "删除成功" : "删除失败:" + i);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// try {
			// if (null != storageServer)
			// storageServer.close();
			// if (null != trackerServer)
			// trackerServer.close();
			// } catch (IOException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
		}
	}
}
