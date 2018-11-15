package com.xzpx_zc.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AesUtil {
	private static final String DEFAULT_KEY = "WbX6UGGJp2eN%04MNDMc0318q&tN0C%&kkm*Wtx*RtA8VAvt5HxdcBEvd#3#y4bU";
	/**
	 * AES解密
	 * @param data
	 * @param key
	 * @return
	 * @author ZC
	 * @date 2018年11月6日 下午3:18:53
	 */
	public static String decodeByAes(String data, String key) {
		try {
			byte[] result = coderByAes(hexStr2ByteArr(data), key.getBytes("UTF-8"), Cipher.DECRYPT_MODE);
			return new String(result, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
		
	}
	
	
	/**
	 * AES加密
	 * @param data
	 * @param key
	 * @return
	 * @author ZC
	 * @date 2018年11月6日 下午3:17:29
	 */
	public static String encodeByAes(String data, String key) {
		try {
			byte[] result = coderByAes(data.getBytes("UTF-8"), key.getBytes("UTF-8"), Cipher.ENCRYPT_MODE);
			return bytes2HexString(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
		
	}
	
	
	
	public static byte[] coderByAes(byte[] data, byte[] key, int mode)
			throws InvalidKeyException, NoSuchAlgorithmException, 
					InvalidKeySpecException, NoSuchPaddingException, 
					IllegalBlockSizeException, BadPaddingException {
	
         //1.构造密钥生成器，指定为AES算法,不区分大小写
		 KeyGenerator kgen = KeyGenerator.getInstance("AES");
         //2.根据ecnodeRules规则初始化密钥生成器
         //生成一个128位的随机源,根据传入的字节数组
         kgen.init(128, new SecureRandom(key));
         //3.产生原始对称密钥
         SecretKey secretKey = kgen.generateKey();
         //4.获得原始对称密钥的字节数组
         byte[] enCodeFormat = secretKey.getEncoded();
         //5.根据字节数组生成AES密钥
         SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, "AES");
         //6.根据指定算法AES自成密码器
         Cipher cipher = Cipher.getInstance("AES");// 创建密码器
         //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
         cipher.init(mode, keySpec);// 初始化
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
		
		String encodeByDes = encodeByAes("nihaoma", DEFAULT_KEY);
		System.out.println(encodeByDes);
		System.out.println(decodeByAes(encodeByDes, DEFAULT_KEY));
	}
}
