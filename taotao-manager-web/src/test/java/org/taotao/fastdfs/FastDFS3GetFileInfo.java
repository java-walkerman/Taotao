package org.taotao.fastdfs;

import java.io.IOException;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

public class FastDFS3GetFileInfo {

	public void getFileInfo() {
		String groupName = "group1";
		String filePath = "M00/00/00/wKgBVl4eSKGAUezNAAa7osdPoQE400.png";
		String trackerServers = "127.0.0.1:22122";

		try {
			ClientGlobal.initByTrackers(trackerServers);
			TrackerClient tracker = new TrackerClient();
			// trackerServer = tracker.getConnection();
			TrackerServer trackerServer = tracker.getTrackerServer();
			StorageClient storageClient = new StorageClient(trackerServer);

			FileInfo file = storageClient.get_file_info(groupName, filePath);
			System.out.println("ip--->" + file.getSourceIpAddr());
			System.out.println("文件大小--->" + (file.getFileSize()/1024)+"KB");
			System.out.println("文件上传时间--->" + file.getCreateTimestamp());
			System.out.println("crc32:"+file.getCrc32());

//			ip--->192.168.1.86
//			文件大小--->441250
//			文件上传时间--->Wed Jan 15 07:02:57 CST 2020
//			crc32:-951082751

		} catch (IOException e) {
			e.printStackTrace();
		} catch (MyException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		FastDFS3GetFileInfo ftest = new FastDFS3GetFileInfo();
		ftest.getFileInfo();
	}

}
