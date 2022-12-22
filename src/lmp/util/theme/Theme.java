package lmp.util.theme;

import java.awt.Color;

public class Theme{

	Color mainColor;
	Color subColor;
	
	public Theme() {
	}
	
	public void setTheme(String theme) {
		switch (theme) {

		case "Default":
			mainColor = new Color(1, 1, 1);
			subColor = new Color(99, 99, 99);
			break;
		case "Bluegreen":
			mainColor = new Color(0, 82, 91);
			subColor = new Color(0, 64, 61);
			break;
	}
	}

	public Color getMainColor() {
		return mainColor;
	}

	public Color getSubColor() {
		return subColor;
	}
	
	

	
}
