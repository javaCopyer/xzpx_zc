package com.xzpx_zc.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
/**
 * DES非对称加密工具类
* @author ZC  
* @date 2018年11月6日 下午3:39:48
* @version
 */
public class DesUtil {
	private static final String DEFAULT_KEY = "WbX6UGGJp2eN%04MNDMc0318q&tN0C%&kkm*Wtx*RtA8VAvt5HxdcBEvd#3#y4bU";
	/**
	 * DES解密
	 * @param data
	 * @param key
	 * @return
	 * @author ZC
	 * @date 2018年11月6日 下午3:18:53
	 */
	public static String decodeByDes(String data, String key) {
		try {
			byte[] result = coderByDes(hexStr2ByteArr(data), key.getBytes("UTF-8"), Cipher.DECRYPT_MODE);
			return new String(result, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
		
	}
	
	
	/**
	 * DES加密
	 * @param data
	 * @param key
	 * @return
	 * @author ZC
	 * @date 2018年11月6日 下午3:17:29
	 */
	public static String encodeByDes(String data, String key) {
		try {
			byte[] result = coderByDes(data.getBytes("UTF-8"), key.getBytes("UTF-8"), Cipher.ENCRYPT_MODE);
			return bytes2HexString(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
		
	}
	
	
	
	public static byte[] coderByDes(byte[] data, byte[] key, int mode)
			throws InvalidKeyException, NoSuchAlgorithmException, 
					InvalidKeySpecException, NoSuchPaddingException, 
					IllegalBlockSizeException, BadPaddingException {
	
		 // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance("DES");

        // 用密钥初始化Cipher对象
        cipher.init(mode, securekey, sr);

        return cipher.doFinal(data);
	}
	
	
	/**
	 * 将byte数组转成十六进制的字符串
	 * 
	 * @param b
	 *            byte[]
	 * @return String
	 */
	private static String bytes2HexString(byte[] b) {
		StringBuffer ret = new StringBuffer(b.length);
		String hex = "";
		for (int i = 0; i < b.length; i++) {
			hex = Integer.toHexString(b[i] & 0xFF);

			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			ret.append(hex);
		}
		return ret.toString();
	}
	
	
	/** */
	/**
	 * 将表示16进制值的字符串转换为byte数组， 和public static String bytes2HexString(byte[] arrB)
	 * 互为可逆的转换过程
	 * 
	 */
	private static byte[] hexStr2ByteArr(String strIn)
			throws NumberFormatException {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;
		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}
	
	public static void main(String[] args) {
		
		String encodeByDes = encodeByDes("nihaoma", DEFAULT_KEY);
		System.out.println(encodeByDes);
		System.out.println(decodeByDes(encodeByDes, DEFAULT_KEY));
	}
}
