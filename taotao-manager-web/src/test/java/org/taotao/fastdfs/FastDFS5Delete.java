package org.taotao.fastdfs;

import java.io.IOException;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

public class FastDFS5Delete {

	private void delete() {
		String groupName = "group1";
		String filePath = "M00/00/00/wKgBVl4eSKGAUezNAAa7osdPoQE400.png";
		String trackerServers = "127.0.0.1:22122";

		try {
			ClientGlobal.initByTrackers(trackerServers);
			TrackerClient tracker = new TrackerClient();
			// trackerServer = tracker.getConnection();
			TrackerServer trackerServer = tracker.getTrackerServer();
			StorageClient storageClient = new StorageClient(trackerServer);

			int i = storageClient.delete_file(groupName, filePath);
			System.out.println(i == 0 ? "删除成功" : "删除失败:" + i);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (MyException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		FastDFS5Delete ftest = new FastDFS5Delete();
		ftest.delete();
	}

}
