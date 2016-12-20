package com.grss.jlsx.core.utils;

import java.util.Random;

public class RandomUtil {
	/**
	 * 
	 * @comment 获取范围随机数
	 * @param min
	 * @param max
	 * @return
	 * 2015年12月23日
	 * 上午9:37:18
	 */
	public  static int getRandom(int min,int max){
        Random random = new Random();
       return random.nextInt(max)%(max-min+1) + min;
	}
	
	/**
	 * 取出一个指定长度大小的随机正整数.
	 * 
	 * @param length
	 *            int 设定所取出随机数的长度。length小于11
	 * @return int 返回生成的随机数。
	 */
	public static int buildRandom(int length) {
		int num = 1;
		double random = Math.random();
		if (random < 0.1) {
			random = random + 0.1;
		}
		for (int i = 0; i < length; i++) {
			num = num * 10;
		}
		return (int) ((random * num));
	}
}
