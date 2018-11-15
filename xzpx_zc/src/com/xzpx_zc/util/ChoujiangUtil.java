package com.xzpx_zc.util;

import java.util.Arrays;
/**
 *固定数量抽奖类
* @projectName xzpx_zc
* @ClassName: ChoujiangUtil 
* @Description: TODO
* @author zhangchao
* @date 2018年1月27日 上午12:09:00 
*
 */
public class ChoujiangUtil {
	private static String ONE = "一等奖";
	private static String TWO = "二等奖";
	private static String THREE = "三等奖";
	private static int PNO;
	
	public static void main(String[] args) {
		String[] datas = generatorData(1, 2, 3, 6);
		System.out.println(Arrays.toString(datas));
		choujiang(datas, 6);
//		System.out.println(Arrays.toString(datas));
//		datas = generatorData(1, datas);
//		System.out.println(Arrays.toString(datas));
//		
	
	}
	
	
	/**
	 * 生成抽奖数据的数组
	 * {"一等奖", "二等奖", "三等奖"}
	* @Title: generatorData
	* @Description: TODO 
	* @param @param one
	* @param @param two
	* @param @param three
	* @param @param amount
	* @param @return   
	* @return String[]   
	* @throws
	 */
	public static String[] generatorData(int one, int two, int three, int amount) {
		String[] datas = new String[amount];
			for(int j=0; j<one; j++) { //添加一等奖
				datas[j] = ONE;
			}
			for(int k=(one); k<(one+two); k++) {//添加二等奖
				datas[k] = TWO;
			}
			for(int i=(one+two); i<(one+two+three); i++) { //添加三等奖
				datas[i] = THREE;
			}
		return datas;
	}
	
	private static int getNum(int amount) {
		int num = 0;
		num = (int)(Math.random() * amount);
		return num;
	}
	
	private static String[] generatorData(int num, String[] datas) {
		datas[num]= datas[datas.length - 1];
		datas = Arrays.copyOf(datas, datas.length -1);
		return datas;
	}
	
	
	
	public static String choujiang(String[] datas, int amount) {
		String result = "";
		if(amount == 0) {
			result = "抽奖已经完毕";
			System.out.println("抽奖已经完毕");
			return result;
		}
		int num = getNum(amount);
		result = datas[num];
		PNO ++;
		System.out.println("编号" + PNO + "： " + result);
		if(result.equals(ONE)) {//一等奖
			/**一等奖数量减一操作   数量为持久化***/
		}
		if(result.equals(TWO)) {//二等奖
			/**二等奖数量减一操作 数量为持久化***/
		}
		if(result.equals(THREE)) {//三等奖
			/**三等奖数量减一操作  数量为持久化***/
		}
		/**生成最新的抽奖数据， 若是持久化的数据应该用奖的数量从新生成抽奖数据**/
		datas = generatorData(num, datas);
		/**总数量减一操作  数量可为持久化**/
		amount--;
		
		choujiang(datas, amount);	
		return result;
	}
	 
	
}
	

