package com.grss.jlsx.api.process.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtil {
	private final static String PATTERN = "\r\n";
	private final static String PATTERN_ = "\n";
	public static String getFilename(String path) {
		return path.substring(path.lastIndexOf("/") + 1);
	}
	public static boolean setFileContent(String path, String content,String encodeingType) {
		boolean flag = false;
		DataOutputStream dos = null;
		try {
			if (content != null && content.length() >= 0) {
				File pathFile=new File(path);
				if(!pathFile.getParentFile().exists()){
					pathFile.getParentFile().mkdirs();
				}
				byte abyte[] = content.getBytes(encodeingType);
				dos = new DataOutputStream(new FileOutputStream(path));
				dos.write(abyte, 0, abyte.length);
				dos.flush();

				flag = true;
			}
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} finally {
			if (dos != null) {
				try {
					dos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				dos = null;
			}
		}
		return flag;
	}
	
	public static boolean setFileContent(String path, String content) {
		return setFileContent(path,content,"UTF-8");
	}
	
	public static List<String> findContent(String condition){
		List<String> contents = new ArrayList<String>();
		BufferedReader br = null;
		String fname = "E:/lucene4.0/solr/home/dic/words-my.dic";
		File file = new File(fname);
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String data = null;
			do{
				data = br.readLine();
				if(data != null){
					if(condition != null && !"".equals(condition)){
						Pattern pattern = Pattern.compile(condition);
						Matcher matcher = pattern.matcher(data);
						while(matcher.find()){
							contents.add(data);
						}
					}
					else{
						contents.add(data);
					}
				}
			}while(data != null);
			
		} catch (FileNotFoundException e1) {
			System.err.println("File not found: " + fname);
		} catch (IOException e2) {
			e2.printStackTrace();
		}finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return contents;
	}
	/**
	 * 
	 * @comment 删除某一个词
	 * @param word
	 * @param filePath
	 * @return
	 * 2015年12月2日
	 * 下午4:32:40
	 */
	public static boolean deleteContentByWord(String word,String filePath){
		return setFileContent(filePath, getContent(word, filePath, 0));
	}
	/**
	 * 
	 * @comment 添加新词
	 * @param word
	 * @param filePath
	 * @return
	 * 2015年12月2日
	 * 下午4:46:43
	 */
	public static boolean addContentByWord(String word,String filePath){
		return setFileContent(filePath, getContent(word, filePath, 1));
	}
	
	public static boolean deleteContentByWord(String word,String fileName,Class<?> cls){
		String rootPath=cls.getResource("/").getFile().toString();
		rootPath = rootPath.substring(1,rootPath.length()) + "/" + fileName;
		System.out.println(rootPath);
		return setFileContent(rootPath,getContent(word, rootPath, 0));
	}
	public static boolean addContentByWord(String word,String fileName,Class<?> cls){
		 String rootPath=cls.getResource("/").getFile().toString() + "/" + fileName;
		return setFileContent(rootPath,getContent(word, rootPath, 1));
	}
	
	private static String getContent(String word,String filePath,int type){
		String content = readFileByChars(filePath);
		if(type == 0){
			content = content.replace(word + PATTERN, "").replace(word + PATTERN_, "");
		}else{
			if(content != null && content.trim().length() > 1){
				content += PATTERN + word;
			}else{
				content += word;
			}
		}
		/*File file = new File(filePath);
		if(!file.exists()){
			file.delete();
		}*/
		return content;
	}
	 /**
     * 以字符为单位读取文件，常用于读文本，数字等类型的文件
     */
    public static String readFileByChars(String fileName) {
        Reader reader = null;
        StringBuilder sbd = new StringBuilder();
        try {
            // 一次读多个字符
            char[] tempchars = new char[30];
            int charread = 0;
            reader = new InputStreamReader(new FileInputStream(fileName),"UTF-8");
            // 读入多个字符到字符数组中，charread为一次读取字符数
            while ((charread = reader.read(tempchars)) != -1) {
                // 同样屏蔽掉\r不显示
                if ((charread == tempchars.length)
                        && (tempchars[tempchars.length - 1] != '\r')) {
                	sbd.append(tempchars);
                } else {
                    for (int i = 0; i < charread; i++) {
                        if (tempchars[i] == '\r') {
                            continue;
                        } else {
                        	sbd.append(tempchars[i]);
                        }
                    }
                }
            }
            return sbd.toString();

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return null;
    }
    
    /**
     * 以字符为单位读取文件，常用于读文本，数字等类型的文件
     */
    public static String readFileByChars(InputStream is) {
        Reader reader = null;
        StringBuilder sbd = new StringBuilder();
        try {
            // 一次读多个字符
            char[] tempchars = new char[30];
            int charread = 0;
            reader = new InputStreamReader(is,"UTF-8");
            // 读入多个字符到字符数组中，charread为一次读取字符数
            while ((charread = reader.read(tempchars)) != -1) {
                // 同样屏蔽掉\r不显示
                if ((charread == tempchars.length)
                        && (tempchars[tempchars.length - 1] != '\r')) {
                	sbd.append(tempchars);
                } else {
                    for (int i = 0; i < charread; i++) {
                        if (tempchars[i] == '\r') {
                            continue;
                        } else {
                        	sbd.append(tempchars[i]);
                        }
                    }
                }
            }
            return sbd.toString();

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return null;
    }
}
