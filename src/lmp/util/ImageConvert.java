package lmp.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import lmp.members.dao.ImageDao;
import lmp.members.vo.ImageVO;

public class ImageConvert {
	
	BufferedImage bufferedImage;
	Image image;
	ImageIcon icon;
	static ImageDao imageDao = new ImageDao();
	ImageVO imageVO;
	
	public ImageIcon scaledMenuImage(String image_name) {
		try {
			imageVO = imageDao.getImage(image_name);
			bufferedImage = ImageIO.read(new File(imageVO.getImage_path()));
			image = bufferedImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			icon = new ImageIcon(image);
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		} finally {			
			return icon;
		}
	}
	
	public ImageIcon scaledPanelImage(String image_name) {
		try {
			imageVO = imageDao.getImage(image_name);
			bufferedImage = ImageIO.read(new File(imageVO.getImage_path()));
			image = bufferedImage.getScaledInstance(1500, 750, Image.SCALE_SMOOTH);
			icon = new ImageIcon(image);
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		} finally {			
			return icon;
		}
	}
	
	public void inputImage(File file) {
		try {
			imageDao.addImage(file);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}

}
