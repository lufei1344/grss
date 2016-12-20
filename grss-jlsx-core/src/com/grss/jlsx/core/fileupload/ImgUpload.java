package com.grss.jlsx.core.fileupload;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;

import com.grss.jlsx.core.utils.StringUtil;

public class ImgUpload {
	
	/**
     * 外部调用方法
     * @param url
     * @param w
     * @param h
     */
    public static boolean ImgCompress(InputStream is, String filePath) {
            // 压缩质量 百分比
            float JPEGcompression = 0.7f;
            //压缩主方法
            if(!StringUtil.empty(ImgCompress(filePath, is,JPEGcompression))){
            	return true;
            }
             return false;
         
    }  
    
    public static String ImgCompress(String filePath, InputStream is,float JPEGcompression) {
        File file = new File(filePath);
        FileOutputStream fos = null;
        if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
            try {
                BufferedImage bufferedImage =  ImageIO.read(is);
                int new_w = bufferedImage.getWidth();
                int new_h = bufferedImage.getHeight(); 
                BufferedImage image_to_save = new BufferedImage(new_w, new_h,bufferedImage.getType());
                image_to_save.getGraphics().drawImage(
                        bufferedImage.getScaledInstance(new_w, new_h, Image.SCALE_REPLICATE), 0,
                        0, null);
                fos = new FileOutputStream(filePath); // 输出到文件流
                // 新的方法
                int dpi = 300;//分辨率
                saveAsJPEG(dpi, image_to_save, JPEGcompression, fos);
                return filePath;
                //返回压缩后的图片地址
            } catch (IOException ex) {
            	  //关闭输出流
                try {
                	if(fos != null)
                		fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
         
        return null;
         
    }
    public static void saveAsJPEG(Integer dpi, BufferedImage image_to_save,float JPEGcompression, FileOutputStream fos) {
    	ImageWriter imageWriter = null;  
        ImageOutputStream ios = null;
        try {
			imageWriter = ImageIO.getImageWritersBySuffix("jpg").next();
			ios = ImageIO.createImageOutputStream(fos);
			imageWriter.setOutput(ios);
			IIOMetadata imageMetaData = imageWriter.getDefaultImageMetadata(
			        new ImageTypeSpecifier(image_to_save), null);
			if (JPEGcompression >= 0 && JPEGcompression <= 1f) {
			    JPEGImageWriteParam jpegParams = (JPEGImageWriteParam) imageWriter
			            .getDefaultWriteParam();
			    jpegParams.setCompressionMode(JPEGImageWriteParam.MODE_EXPLICIT);
			    jpegParams.setCompressionQuality(JPEGcompression);
 
			}
			imageWriter.write(imageMetaData, new IIOImage(image_to_save, null, null), null);
		} catch (IOException e) {
			if(ios != null){
				try {
					ios.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		        
			}
			if(imageWriter != null){
				imageWriter.dispose();
			}
		}
        
    }
	public static void main(String args[]) {
        // 图片url，压缩后的宽和高
        String url = "C:/img/840CA592B8B4AF95E62A491E39EE6934.png";
        //压缩后的图片路径
            String newName = "123123";
            String newDir = "C:/img/";
            String filePath = newDir + newName + "_"+ url.substring(url.indexOf("."));
        //压缩
        try {
			System.out.println(ImgCompress(new FileInputStream(new File(url)),filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
}
