package lmp.util.theme;

import java.awt.Color;

public class Theme{

	Color mainColor;
	Color sub1Color;
	Color sub2Color;

	public Theme() {

	}
	
	public void setTheme(String theme) {
		switch (theme) {

		case "Gold":
			mainColor = new Color(175,150,105);
			sub1Color = new Color(148,124,83);
			sub2Color = new Color(126,100,56);
			break;
		case "BlueGreen":
			mainColor = new Color(0, 82, 91);
			sub1Color = new Color(0, 64, 61);
			sub2Color = new Color(0, 46, 31);
			break;
	}
	}

	public Color getMainColor() {
		return mainColor;
	}

	public Color getSub1Color() {
		return sub1Color;
	}

	public Color getSub2Color() {
		return sub2Color;
	}
	
	
	
	

	
}
