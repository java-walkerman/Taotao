package org.taotao.fastdfs;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal; 
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

public class FastDFS2Download {

	private void download() {
		String groupName = "group1";
		String filePath = "M00/00/00/wKgBVl4eSKGAUezNAAa7osdPoQE400.png";
		String trackerServers = "127.0.0.1:22122";
		
			try {
				ClientGlobal.initByTrackers(trackerServers);
				TrackerClient tracker = new TrackerClient();
				// trackerServer = tracker.getConnection();
				TrackerServer trackerServer = tracker.getTrackerServer();
				StorageClient storageClient = new StorageClient(trackerServer);
				
				byte[] bytes = storageClient.download_file(groupName, filePath);

				String storePath = "/Users/hydra/Desktop/download.png";
				OutputStream out = new FileOutputStream(storePath);
				out.write(bytes);
				System.out.println("============== downloaded to /Users/hydra/Desktop/download.png");
//				FileInfo file = storageClient.get_file_info(groupName, filePath);
//				System.out.println("ip--->" + file.getSourceIpAddr());
//				System.out.println("文件大小--->" + file.getFileSize());
//				System.out.println("文件上传时间--->" + file.getCreateTimestamp());
//				System.out.println("Crc32:"+file.getCrc32());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (MyException e) {
				e.printStackTrace();
			}
	}
	public static void main(String[] args) {
		FastDFS2Download ftest = new FastDFS2Download();
		ftest.download();

	}

}
