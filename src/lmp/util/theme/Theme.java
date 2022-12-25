package lmp.util.theme;

import java.awt.Color;

public class Theme{
	
	/**
	 *  테마 설정
	 */

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
		case "Green" :
			mainColor = new Color(203, 211, 188);
			sub1Color = new Color(114, 141, 112);
			sub2Color = new Color(69, 92, 63);
			break;
		case "Brown" :
			mainColor = new Color(201, 174, 157);
			sub1Color = new Color(188, 147, 127);
			sub2Color = new Color(145, 85, 74);
			break;
		case "Purple" :
			mainColor = new Color(230, 212, 210);
			sub1Color = new Color(181, 164, 170);
			sub2Color = new Color(104, 108, 117);
			break;
		case "Gray" :
			mainColor = new Color(198, 198, 196);
			sub1Color = new Color(178, 175, 167);
			sub2Color = new Color(127, 126, 122);
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
