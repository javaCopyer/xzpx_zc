package com.xzpx_zc.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
/**
 * 图片工具类
* @projectName zc2017
* @ClassName: ImageUtil 
* @Description: TODO
* @author zhangchao
* @date 2017年8月17日 下午11:43:06 
*
 */
public class ImageUtil {
	public static void main(String[] args) throws IOException {
		//drawTextToImage(new File("F:/1.jpg"), "傻逼", new Font("宋体", Font.BOLD, 30), new Color(0, 0, 0), -1, -1, 1.0F);
		drawImageToImage(new File("F:/1.jpg"), new File("F:/1099.jpg"), 400, 200, 1.0F);
	}
	/**
	 * @throws IOException 
	 * 给图片添加文字
	* @Title: drawTextToImage
	* @Description: TODO 
	* @param @param file  目标文件
	* @param @param text  文字
	* @param @param font  字体
	* @param @param color 字体颜色
	* @param @param x	      坐标
	* @param @param y     坐标
	* @param @param alpha 透明度
	* @param @return   
	* @return BufferedImage   
	* @throws
	 */
	public static void drawTextToImage(File file, String text, Font font, Color color, int x, int y, float alpha) throws IOException {
		Image image = ImageIO.read(file);
		int width = image.getWidth(null);
		int height = image.getHeight(null);
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics();
		graphics2D.drawImage(image, 0, 0, null);
		graphics2D.setFont(font);
		graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha)); //设置透明度
		graphics2D.setColor(color);
		int fontWidth = font.getSize() * getTextLength(text);
		int fontHeight = font.getSize();
		int widthDiff = width - fontWidth;
		int heightDiff = height - fontHeight;
		if (x < 0)
		  x = widthDiff / 2;
		else if (x > widthDiff)
		  x = widthDiff;
		if (y < 0)
		  y = heightDiff / 2;
		else if (y > heightDiff)
		  y = heightDiff;
		graphics2D.drawString(text, x, y + fontHeight);
		graphics2D.dispose();
		ImageIO.write(bufferedImage, "jpg", file);
	}
	/**
	 * 给目标图片画入一个图片
	* @Title: drawImageToImage
	* @Description: TODO 
	* @param @param srcImage     源文件
	* @param @param targetImage  被画如图片
	* @param @param x
	* @param @param y
	* @param @param alpha
	* @param @throws IOException   
	* @return void   
	* @throws
	 */
	public static void drawImageToImage(File srcImage, File targetImage, int x, int y , float alpha) throws IOException {
		Image srcImg = ImageIO.read(srcImage);
		Image targetImg = ImageIO.read(targetImage);
		int srcImgWidth = srcImg.getWidth(null);
		int srcImgHeight = srcImg.getHeight(null);
		int targetImgWidth = targetImg.getWidth(null);
		int targetImgHeight = targetImg.getHeight(null);
		BufferedImage bufferedImage = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = bufferedImage.createGraphics();
		// 设置对线段的锯齿状边缘处理     
		graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,     
								  RenderingHints.VALUE_INTERPOLATION_BILINEAR);   
		graphics.drawImage(srcImg, 0, 0, null);
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha)); //设置透明度 
		int widthDiff = srcImgWidth - targetImgWidth;
		int heightDiff = srcImgHeight - targetImgHeight;
		if (x < 0)
		  x = widthDiff / 2;
		else if (x > widthDiff)
		  x = widthDiff;
		if (y < 0)
		  y = heightDiff / 2;
		else if (y > heightDiff)
		 y = heightDiff;
		graphics.drawImage(targetImg, 150, 300, null);     
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));     
		graphics.dispose();
		ImageIO.write(bufferedImage, "JPG", srcImage);
	}
	/**
	 * 返回文字长度
	* @Title: getTextLength
	* @Description: TODO 
	* @param @param text
	* @param @return   
	* @return int   
	* @throws
	 */
	public static int getTextLength(String text) {
		int textLength = text.length();
		int length = 0;
		for(int i=0; i<textLength; i++) {
			if (String.valueOf(text.charAt(i)).getBytes().length > 1)
			   length++;
		}
		 return length % 2 == 0 ? length / 2 : length / 2 + 1;
	}
}
