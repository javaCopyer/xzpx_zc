import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

public class Demo {
	public static void main(String[] args) throws IOException {
//		File file = new File("C:\\Users\\sky\\Desktop\\A.jpg");
//		ByteArrayOutputStream bas = new ByteArrayOutputStream();
//		FileInputStream fis = new FileInputStream(file);
//		int len = 0;
//		byte[] buffer = new byte[1024];
//		while (0 < (len = fis.read(buffer))) {
//			bas.write(buffer, 0, len);
//		}
//		String code = Base64.encodeBase64String(bas.toByteArray());
//		System.out.println(code);

		Double a = 100.3123;
		Double b = 120.3123;
		DecimalFormat format = new DecimalFormat("#0.00");
		Double div = ((a - b) / a) * 100;
		String divStr = format.format(div);
		Double amount = Math.abs(Double.parseDouble(divStr));
		System.out.println(amount);
	
	}
	
}
