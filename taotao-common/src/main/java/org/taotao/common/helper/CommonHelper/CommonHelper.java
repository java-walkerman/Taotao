package org.taotao.common.helper.CommonHelper;

import java.util.Random;

/**
 * 通用 helper
 * 
 * @author hydra
 *
 */
public class CommonHelper {

	public static long generateId() {
		// 取当前实践的长整型，包含毫秒
		long currentTime = System.currentTimeMillis();
//		System.out.println("====== currentTime:"+currentTime);
		// 加上3未随机数，保险一点，省的并发多了，ID重复
		int tail = new Random().nextInt(999);
//		System.out.println("====== tail:"+tail);
		// 如果随机数不足3位前面加0
		// String.format("%03d", tail);
		// return Long.valueOf(currentTime+"00"+tail);
//		System.out.println("== "+(currentTime * 1000 + tail));
		return currentTime * 1000 + tail;
	}

	public static void main(String[] args) { 
		CommonHelper.generateId();
		CommonHelper.generateId();
		CommonHelper.generateId();
		CommonHelper.generateId();
		CommonHelper.generateId();
		CommonHelper.generateId();
		CommonHelper.generateId();
		
	}

}
