package org.taotao.fastdfs;

import java.io.IOException;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

public class FastDFS4GetFileMate {

	private void getFileMate() {
		String groupName = "group1";
		String filePath = "M00/00/00/wKgBVl4eSKGAUezNAAa7osdPoQE400.png";
		String trackerServers = "127.0.0.1:22122";

		try {
			ClientGlobal.initByTrackers(trackerServers);
			TrackerClient tracker = new TrackerClient();
			// trackerServer = tracker.getConnection();
			TrackerServer trackerServer = tracker.getTrackerServer();
			StorageClient storageClient = new StorageClient(trackerServer);

			// 这个值是上传的时候指定的NameValuePair
			NameValuePair[] pairs = storageClient.get_metadata(groupName, filePath);
			if (null != pairs && pairs.length > 0) {
				for (NameValuePair nvp : pairs) {
					System.out.println(nvp.getName() + ":" + nvp.getValue());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MyException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		FastDFS4GetFileMate ftest = new FastDFS4GetFileMate();
		ftest.getFileMate();
	}

}
