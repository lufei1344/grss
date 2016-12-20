package com.grss.jlsx.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

//import com.grss.jlsx.core.fileupload.ImgUpload;
import com.grss.jlsx.core.fileupload.MonitoredOutputStream;
import com.grss.jlsx.core.fileupload.UploadListener;

public class FileUtil {
	/**
	 * 上传文件
	 * @param uoloadPath
	 * @param is
	 * @param fileSize
	 * @return
	 */
	public static boolean upload(String uoloadPath,InputStream is,long fileSize){
		boolean flag = false;
		byte[] buffer = new byte[1024];
		MonitoredOutputStream outputStream = null;
		try {
			File file = existDirectory(new File(uoloadPath));
			outputStream = new MonitoredOutputStream(new FileOutputStream(file), new UploadListener(fileSize));
			int n = -1;
			while((n = is.read(buffer,0,buffer.length)) != -1){
				outputStream.write(buffer, 0, n);
			}
			flag = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(outputStream != null){
					outputStream.close();
					outputStream = null;
				}
				if(is != null){
					is.close();
					is = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	/**
	 * 上传图片
	 * @param putPath
	 * @param is
	 * @param oldPutPath
	 * @return
	 */
//	public static boolean uploadImage(String putPath,InputStream is,String oldPutPath){
//		if(!StringUtil.empty(oldPutPath)){
//			return new ImgUpload().uploadImg(is, oldPutPath);
//		}
//		return new ImgUpload().uploadImg(is, putPath);
//	}
	
	private static File existDirectory(File file){
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		return file;
	}
	public static void main(String[] args) {
		File file = new File("E:\\data\\1.mp4");
		try {
			upload("D:\\var\\static\\1.mp4", new FileInputStream(file),file.length());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
