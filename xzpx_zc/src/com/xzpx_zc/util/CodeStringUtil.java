package com.xzpx_zc.util;

import java.io.IOException;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class CodeStringUtil {

	/**
	 * 
	 * @Description: md5加密字符串 （信息摘要算法）
	 * @param @param str 待加密的字符串
	 * @param @return
	 * @param @throws NoSuchAlgorithmException
	 * @param @throws UnsupportedEncodingException   
	 * @return byte[]  
	 * @throws
	 * @author zhangchao
	 * @date 2017-8-14 下午2:33:54
	 */
	public static byte[] encodeMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		return messageDigest.digest(str.getBytes("utf-8"));
	}
	public static byte[] encodeSHA1(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
		return messageDigest.digest(str.getBytes("utf-8"));
	}
	/**
	 * 
	 * @Description: 将字节数组byte转换为16进制的字符串
	 * @param @param bits
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author zhangchao
	 * @date 2017-8-14 下午2:35:25
	 */
	public static String toHexString1(byte[] bits) {
		String hexString = "";
		for (byte b : bits) {
			int temp = b & 255;
			 if (temp < 16 && temp >= 0) {
				   hexString = hexString + "0" + Integer.toHexString(temp);
			 } else {
				   hexString = hexString + Integer.toHexString(temp);
			 }
		}
		return hexString;
	}
	/**
	 * 用BigInteger将字节数组转换为字符串
	 * @Description: TODO
	 * @param @param bits
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author zhangchao
	 * @date 2017-8-14 下午3:05:19
	 */
	public static String toHexString2(byte[] bits) {
		BigInteger bigInteger = new BigInteger(1, bits);
		return bigInteger.toString(16);
	}
	/**
	 * base64编码
	 * @Description: TODO
	 * @param @param str
	 * @param @return
	 * @param @throws UnsupportedEncodingException   
	 * @return String  
	 * @throws
	 * @author zhangchao
	 * @date 2017-8-14 下午2:44:07
	 */
	public static String encodeBASE64(String str) throws UnsupportedEncodingException {
		return new BASE64Encoder().encode(str.getBytes("utf-8"));
	}
	public static String decodeBASE64(String str) throws IOException {
		byte[] b = new BASE64Decoder().decodeBuffer(str);
		return new String(b);
	}
	//common-codec包 base64加密
	public String base64EncodeOfCodec(String str) throws UnsupportedEncodingException {
		return new Base64().encodeAsString(str.getBytes("utf-8"));
	}
	//common-codec包 base64解密
	public String base64DecodeOfCodec(String str) {
		byte[] b = new Base64().decode(str);
		return new String(b);
	}
	//common-codec包 md5加密
	public String md5OfCodec(String str) {
		return DigestUtils.md5Hex(str);
	}
	
	
	public static void main(String[] args) throws Exception {
		byte[] bits = encodeMD5("账号");
System.out.println(toHexString1(bits));
System.out.println(toHexString2(bits));
//	System.out.println(new BASE64Encoder().encode(bits));
}
}
