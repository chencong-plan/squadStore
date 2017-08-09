package cc.ccoder.squadStore.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {

	public  static void setSomeByFile(String fileName, String value) {
		if (fileName == null || value == null) {
			return;
		}
		try {
			File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream outputStream = new FileOutputStream(file, true);
			byte[] bytes = value.getBytes("utf-8");
			for (byte b : bytes) {
				outputStream.write(b);
			}
			outputStream.close();
		} catch (Exception e) {
		}
	}
	
	public static String getSomeByFile(String fileName) {
		if (fileName == null) {
			return null;
		}
		File file = new File(fileName);
		// 如果指定路径文件不存在 直接返回空 ,传入路径为目录则直接返回空
		if (!file.exists() || file.isDirectory()) {
			return null;
		}
		// 否则获取该文件中的内容
		StringBuffer sBuffer = new StringBuffer();
		try {
			String msg = null;
			// 创建字符流
			FileReader fileReader = new FileReader(file);
			// 创建字符缓冲流
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((msg = bufferedReader.readLine()) != null) {
				sBuffer.append(msg);
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return sBuffer.toString();
	}

}
