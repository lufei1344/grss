package com.grss.jlsx.core.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

import com.grss.jlsx.core.wx.constant.WXConstant;

/**
 * 
 * ClassName: SecurityUtil
 * @Description: 涉及安全的通用方法
 * @author Ryan.wang
 * @date 2015-10-8
 */
public class SecurityUtil {
	public static final String DEFAULT_TRANSLATION = "[|]";
	public static final String TRANSLATION = "%7C";
	public static final String DEFAULT_PASSWORD = "123456";
	/**
	 * 
	 * @Description: MessageDigest MD5加密算法
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException   
	 * @author Ryan.wang
	 * @date 2015-10-8 上午9:58:59
	 */
	@Deprecated
	public static String md5(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		return new BigInteger(0,md.digest()).toString(16);
	}
	


	/**
	 * 
	 * @Description: 手动MD5算法
	 * @param s
	 * @return   
	 * @author Ryan.wang
	 * @date 2015-10-8 上午11:14:27
	 */
    public final static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};       
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	/**
	 * 
	 * @Description: 与APP端MD5保持相同的加密结果
	 * @param @param str
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author Ryan.wang
	 * @date 2015-10-8
	 */
	public static String md5Hex(String str){
		return DigestUtils.md5Hex(str);
	}
	/**
	 * 
	 * @comment 获取Token
	 * @param userId
	 * @param password
	 * @param flag
	 * @return
	 * 2015年12月18日
	 * 下午8:57:43
	 */
	public static String token(String userId,String password){
		if(StringUtil.empty(password)){
			password = DEFAULT_PASSWORD;
		}
			/*return userId + "|" + MD5(password + DateUtil.getMonth());*/
		return userId + "|" + MD5(password);
	}
	/**
	 * 
	 * @comment 分解Token
	 * @param token
	 * @return
	 * 2015年12月21日
	 * 上午9:18:12
	 */
	public static String[] unToken(String token){
		String[] arrays = token.split(DEFAULT_TRANSLATION);
		if(arrays.length <= 1){
			arrays =token.split(TRANSLATION);
		}
		return arrays;
	}
	/**
	 * 
	 * @comment token转用户ID
	 * @param token
	 * @return
	 * 2015年12月24日
	 * 下午12:38:43
	 */
	public static String token2UserId(String token){
		return unToken(token)[0];
	}
	/**
	 * 
	 * @comment 验证密码
	 * @param oldPassword
	 * @param password
	 * @param salt
	 * @return
	 * 2015年12月18日
	 * 下午8:57:32
	 */
	public static boolean checkPassword(String oldPassword,String password){
		if(StringUtil.trim(password).length() < 32){
			return oldPassword.equals(SecurityUtil.MD5(password));
		}
		return oldPassword.equals(password);
	}
	/**
	 * 
	 * @comment 验证密文密码
	 * @param oldPassword
	 * @param password
	 * @return
	 * 2016年1月8日
	 * 上午10:51:49
	 */
	public static boolean checkciPhertextPassword(String oldPassword,String password){
		return oldPassword.equals(password);
	}
	
	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		String hexDigits[] = { "0", "1", "2", "3", "4", "5","6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String MD5Encode(String origin, String charsetname) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charsetname == null || "".equals(charsetname))
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes()));
			else
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes(charsetname)));
		} catch (Exception exception) {
		}
		return resultString;
	}
	
	public static String getSha1(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}

		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };

		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes());

			byte[] md = mdTemp.digest();
			int j = md.length;
			char buf[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(buf);
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 微信签名
	 * @param params
	 * @return
	 */
	public static String sign(Map<String, String> params){

		Collection<String> keyset = params.keySet();
		List<String> list = new ArrayList<>(keyset);
		Collections.sort(list);
		StringBuilder stringBuilder = new StringBuilder();
		for(String key : list){
			if(StringUtil.empty(params.get(key))){
				continue;
			}
			stringBuilder.append(key + "=" + params.get(key) + "&");
		}
		stringBuilder.append("key" + "=" + WXConstant.PAY_KEY);
		return SecurityUtil.md5Hex(stringBuilder.toString()).toUpperCase();
	}
}
