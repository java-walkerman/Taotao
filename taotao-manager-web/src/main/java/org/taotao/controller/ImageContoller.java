package org.taotao.controller;

import java.io.File;
import java.io.IOException;

import org.csource.common.NameValuePair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.taotao.common.FastDFSHelper;
import org.taotao.common.KindEditorImageResult;

/**
 * 图片上传 Controller
 * 
 * @author hydra
 *
 */
@Controller
public class ImageContoller {

	@RequestMapping("/pic/upload")
	@ResponseBody
	public KindEditorImageResult imageUpload(MultipartFile uploadFile) {
		// 接收上传的文件
		// 取扩展名
		String originalFilename = uploadFile.getOriginalFilename();
		String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
		String filename = uploadFile.getName();
		System.out.println("======== filename:" + filename);
		System.out.println("======== originalFilename:" + originalFilename);
		System.out.println("======== suffix:" + suffix);
		// 上传到图片服务器
		NameValuePair nvp[] = new NameValuePair[] { new NameValuePair("imageName", originalFilename) };
		String[] result;
		try {
			result = FastDFSHelper.helper().uploadFile(uploadFile.getBytes(), suffix, nvp);
			if (result != null) {
				String url = "http://127.0.0.1:8888/" + result[0] + "/" + result[1];
				return new KindEditorImageResult(0, url, null);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new KindEditorImageResult(1, null, "上传失败");
	}
}
