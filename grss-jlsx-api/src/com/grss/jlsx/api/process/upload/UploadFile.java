package com.grss.jlsx.api.process.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.grss.jlsx.api.process.util.ParsePropertiesUtil;
import com.grss.jlsx.core.fileupload.ImgUpload;
import com.grss.jlsx.core.fileupload.MonitoredOutputStream;
import com.grss.jlsx.core.fileupload.UploadListener;

public class UploadFile {
	/**
	 * 
	 * @comment 图片上传
	 * @param imgFile 图片文件对象
	 * @param oldPath 数据库中的文件路径
	 * @param datePath 日期路径
	 * @return
	 * @throws Exception
	 * 2015年11月3日
	 * 上午10:56:10
	 */
	public static String doUploadImg(MultipartFile imgFile,String oldPath,String datePath) throws Exception{
		String nowPath = nowPath(imgFile.getOriginalFilename(), datePath);
		String filePath = uploadFilePath(nowPath, oldPath,FileType.IMG);
		boolean flag = ImgUpload.ImgCompress(imgFile.getInputStream(), filePath);
		if (flag) {
			if(!isEmpty(oldPath)){
				return oldPath;
			}
			return ParsePropertiesUtil.getServiceAddress() + ParsePropertiesUtil.getImgAddress()+nowPath;
		}else{
			return null;
		}
		
	}
	/**
	 * 
	 * @comment 删除文件
	 * @param oldPath
	 * @return
	 * 2015年11月5日
	 * 下午12:44:52
	 */
	public static boolean removeFile(String oldPath){
		File file = new File(ParsePropertiesUtil.getParentAddress() + oldPath);
		return file.delete();
	}
	/**
	 * 
	 * @comment 文件上传
	 * @param fileObj 文件对象
	 * @param oldPath 数据库中的文件路径
	 * @param datePath 日期路径
	 * @return
	 * 2015年11月3日
	 * 上午11:05:00
	 */
	public static String doUploadFile(MultipartFile fileObj,String oldPath,String datePath){
		String nowPath = nowPath(fileObj.getOriginalFilename(), datePath);
		String filePath = uploadFilePath(nowPath, oldPath,FileType.FILE);
		boolean flag = upload(filePath, fileObj);
		if(flag){
			if(!isEmpty(oldPath)){
				return oldPath;
			}
			return ParsePropertiesUtil.getFileAddress() + nowPath;
		}
		return null;
	}
	
	public static String getUrlFileContent(String dataBaseurl){
		String fileUrl = ParsePropertiesUtil.getParentAddress()+dataBaseurl;
	    try {
	        FileInputStream fis=new FileInputStream(fileUrl);
	        byte[] b=new byte[fis.available()];
	        fis.read(b);
	        fis.close();
	        return new String(b,"UTF-8");
	    }catch(FileNotFoundException e){
	    	return "";
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    	return "";
	    }
	}
	
	private static boolean upload(String uoloadPath,MultipartFile fileObj){
		boolean flag = false;
		byte[] buffer = new byte[1024];
		MonitoredOutputStream outputStream = null;
		InputStream is = null;
		try {
			File file = existDirectory(new File(uoloadPath));
			outputStream = new MonitoredOutputStream(new FileOutputStream(file), new UploadListener(fileObj.getSize()));
			is = fileObj.getInputStream(); 
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
	
	private static String nowPath(String originalFilename,String datePath){
		String uuid = UUID.randomUUID().toString().replaceAll("-","");
		String suffix = null;
		String setPath = null;
		if(originalFilename != null){
			int index = originalFilename.indexOf(".");
			suffix = originalFilename.substring(index + 1, originalFilename.length());
		}else{
			suffix = "html";
		}
		if(!isEmpty(datePath)){
			setPath = datePath+"/"+uuid+"."+suffix;
		}else{
			setPath = uuid+"."+suffix;
		}
		return setPath;
	}
	
	private static String uploadFilePath(String nowPath,String oldPath,FileType fileType){
		String filePath = null;
		if(!isEmpty(oldPath)){
			filePath=ParsePropertiesUtil.getParentAddress() + oldPath;
		}else{
			switch (fileType) {
			case FILE:
				filePath = ParsePropertiesUtil.getUploadFileAddress()+nowPath;
				break;
			case IMG:
				filePath = ParsePropertiesUtil.getUploadImgAddress()+nowPath;
				break;
			}
		}
		return filePath;
	}
	private static File existDirectory(File file){
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		return file;
	}
	private static boolean isEmpty(String str){
		if(str != null && str.length() > 0){
			return false;
		}
		return true;
	}
	private enum FileType{
		IMG,FILE
	}
	
}
