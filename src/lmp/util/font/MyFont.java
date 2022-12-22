package lmp.util.font;

import java.awt.Font;

public class MyFont {

	Font title;
	Font text;
	Font table;
	
	
	public MyFont() {
		
	}
	
	public void setFont(String font_size) {
		
		switch (font_size) {
		
			case "Small" :
				title = new Font("한컴 말랑말랑 Regular", Font.BOLD, 40);
				text  = new Font("한컴 말랑말랑 Regular", Font.BOLD, 15);
				table = new Font("한컴 말랑말랑 Regular", Font.BOLD, 15);
				break;
			case "Midium":
				break;
			case "Large" :
				break;
		}
		
		
	}
	
}
