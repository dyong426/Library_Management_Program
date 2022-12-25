package lmp;

import java.io.File;
import java.sql.SQLException;
import java.util.Arrays;

import lmp.login.SelectModeFrame;
import lmp.util.ImageConvert;

public class Main {
	
	public static void main(String[] args) throws SQLException {
//		ImageConvert img = new ImageConvert();
//		File files = new File("src/lmp/util/images/imageicon");
//		
//		for (String file : files.list()) {
//			img.inputImage(new File(file));
//		}
		
		new SelectModeFrame();
	}
}
