package com.xzpx_zc.util;
import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;



public class EncodingUtil {
	public static void main(String[] args) throws Exception {
//		    String key = "1099";
//		    FileUtil.copy(new File("f:/white.jpg"), new File("f:/" + key + "_temp.jpg"));
//		    ImageUtil.pressText("f:/" + key + "_temp.jpg", key, new Font("ו", Font.BOLD, 30), new Color(0, 0, 0), 15, 30, 1.0F);
//		    ImageUtil.pressImage("f:/" + key + ".jpg", "f:/" + key + "_temp.jpg", 200, 200, 1.0F);
//		    FileUtil.delete(new File("f:/" + key + "_temp.jpg"));
//	byte[] bits = encodeMD5("账号");
//	System.out.println(toHexString1(bits));
//	System.out.println(toHexString2(bits));
//		System.out.println(new BASE64Encoder().encode(bits));
		
//	System.out.println(new BigDecimal("24.2").setScale(0, BigDecimal.ROUND_HALF_UP));
//		URL url = new URL("http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=HKWCUGjyNuaxBGCESiGEqp1n2gAMiaIHy5KwCJZh968yI-qdsphAloTHYBuUSF6PLxhuE3KahQPyHgI691mnB2sIQ1W_FRaQXuUmnGg5GKyVHdk-eoj6ZOSdgXaKCG8cPIZaAFAVOF&media_id=XwyGAl_TXb5BjmcyjpCk4DMaaHLqUYx_Rj2TdS_3Vq77QSzYvctZwPUxA5aarWBI");
//		InputStream in = url.openConnection().getInputStream();
//		ByteArrayOutputStream bao = new ByteArrayOutputStream();
//		byte[] data = new byte[1024];
//		int len = 0;
//		while((len = in.read(data)) != -1) {
//			bao.write(data, 0, len);
//		}
//		String ACCESS_KEY = "zPspWRZTeg5yXQh6xKpv4cZgD-DIDq4d528zxKUj";
//		 String SECRET_KEY = "QH4Qf0v14RTRb6x7ojroMqWsZgJ2ZYNCwh-E2-sx";
//		 //要上传的空间
//		 String bucketname = "moozun-yhj";
//		 Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
//		 Zone z = Zone.autoZone();
//		 Configuration c = new Configuration(z);
//		 UploadManager uploadManager = new UploadManager(c);
//		 try {
//		  //调用put方法上传
//		  Response res = uploadManager.put(bao.toByteArray(),null, auth.uploadToken(bucketname));
//		  //打印返回的信息
//		  System.out.println(res.bodyString());
//		 } catch (QiniuException e) {
//			 Response r = e.response;
//			 // 请求失败时打印的异常的信息
//			  System.out.println(r.toString());
//		 }
		  
		 
		//		System.out.println("江苏省徐州市沛县".replaceAll("^.+?省", "").split("市")[0]);
//		Calendar calendar = Calendar.getInstance();
//		
//		//calendar.add(Calendar.MINUTE, -1);
//		calendar.set(Calendar.YEAR, 2017);
//		calendar.set(Calendar.MONTH, 10);
//		calendar.set(Calendar.DATE, 1);
//		System.out.println(calendar.get(Calendar.MONTH));
//		StringBuilder restDate = new StringBuilder();
//		while(calendar.get(Calendar.MONTH) <= 10) {
//		  
//			if(calendar.get(Calendar.DAY_OF_WEEK) == 7) {
//					restDate.append(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()) + ",");
//			}
//			  calendar.add(Calendar.DATE, 1);
//		}
//		System.out.println(restDate.toString());
//		System.out.println(restDate.toString().split(",").length);
	//	System.out.println(calendar.get(Calendar.DATE));
	}
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
	
}
