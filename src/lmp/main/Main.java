package lmp.main;

import java.io.File;
import java.sql.SQLException;
import java.util.Arrays;

import lmp.util.ImageConvert;

public class Main {
	
	public static void main(String[] args) {
		try {
			new SelectModeFrame();
		} catch (SQLException e) {
		}
	}
}
