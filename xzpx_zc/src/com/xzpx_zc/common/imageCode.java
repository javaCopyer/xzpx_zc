package com.xzpx_zc.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;
/**
 * 图片二维码
* @projectName zc2017
* @ClassName: imageCode 
* @Description: TODO
* @author zhangchao
* @date 2017年8月17日 下午11:42:52 
*
 */
public class imageCode {
	private String answer;

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	private Color getRandomColor(Random random, int color1, int color2) {
		if (color1 > 255)
		   color1 = 255; 
	    if (color2 > 255)
		   color2 = 255; 
		int R = color1 + random.nextInt(color2 - color1);
		int G = color1 + random.nextInt(color2 - color1);
		int B = color1 + random.nextInt(color2 - color1);
		return new Color(R, G, B);
	}
	public BufferedImage drawImage() {
		int width = 126;
	    int height = 30; 
	    String codes = "\u0030\u0031\u0032\u0033\u0034\u0035\u0036\u0037\u0038\u0039";  //數字
	    String family[] = { "\u5b8b\u4f53", "\u65b0\u5b8b\u4f53", "\u9ed1\u4f53", "\u6977\u4f53", "\u96b6\u4e66" }; //字體
	    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    Graphics graphics = image.getGraphics();
	    Random random = new Random();
	    graphics.setColor(getRandomColor(random, 200, 250));
	    graphics.fillRect(0, 0, width, height);
	    graphics.setColor(getRandomColor(random, 160, 200));
	    graphics.setFont(new Font("Times New Roman", Font.PLAIN, 14)); 
	    for (int i = 0; i < 200; i++) {
	        int x1 = random.nextInt(width);
	        int y1 = random.nextInt(height);
	        int x2 = random.nextInt(15);
	        int y2 = random.nextInt(15);
	        graphics.drawLine(x1, y1, x1+x2, y1+y2);
	      }
	    graphics.setColor(getRandomColor(random, 120, 240));
	    for (int i = 0; i < 100; i++) {
	        int x = random.nextInt(width); 
	        int y = random.nextInt(height); 
	        graphics.drawOval(x, y, 0, 0); 
	      }
	    this.answer = "";
	    String randomAnswer;
	    for (int i = 0; i < 4; i++) {
	      int start = random.nextInt(codes.length()); 
	      randomAnswer = codes.substring(start, start+1); 
	      this.answer += randomAnswer;   
	      graphics.setColor(this.getRandomColor(random, 10, 150));
	      graphics.setFont(new Font(family[random.nextInt(family.length)], Font.BOLD, 18+random.nextInt(4))); 
	      graphics.drawString(randomAnswer, 24*i+10+random.nextInt(8), 24);
	    }
	    graphics.dispose();
	    return image;
	}
	public InputStream imageToStream(BufferedImage image) throws IOException {
		 ByteArrayOutputStream baos = new ByteArrayOutputStream();
		 ImageIO.write(image, "GIF", baos);
		 ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		 return bais;
	}
	public static void main(String[] args) throws IOException {
		imageCode imageCode = new imageCode();
	
		ImageIO.write(imageCode.drawImage(), "GIF", new File("F:\\a.jpg"));
		System.out.println(imageCode.getAnswer());
	}
}
