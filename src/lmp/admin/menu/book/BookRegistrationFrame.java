package lmp.admin.menu.book;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import lmp.admin.dao.BookDao;
import lmp.admin.vo.BookVO;
import lmp.admin.vo.LocationVO;

public class BookRegistrationFrame extends JFrame implements MouseListener, KeyListener {

	static final String[] labels_Regist = { "제목", "저자", "출판사", "ISBN", "편권수", "가격", "위치", "비고" };
	private JTextField[] fields_Regist = new JTextField[8];
	String[] comboBox_BookLocations = { "A.철학", "B.종교", "C.사회과학", "D.자연과학", "E.기술과학", "F.예술", "G.언어", "H.문학", "I.역사" };
	private JScrollPane scrolledTable_Regist;
	JTable table_Regist;
	private int rowCount;
	DefaultTableModel model_Regist = new DefaultTableModel(labels_Regist, rowCount);
	private JButton addBtn;
	private JButton delBtn;
	private JButton saveBtn_Regist;
	JComboBox cb_Regist = new JComboBox(comboBox_BookLocations);

	public BookRegistrationFrame(String title) {

		this.setTitle("신규 도서등록 페이지");
		this.setLayout(new BorderLayout(10, 10));

		// 상단 패널(도서등록 시 텍스트필드 영역)
		JPanel topPanel = new JPanel(new GridLayout(4, 2, 100, 5));
		for (int i = 0; i < labels_Regist.length; i++) {
			JLabel label = new JLabel(labels_Regist[i]);
			label.setForeground(Color.WHITE);
			topPanel.add(label);
			fields_Regist[i] = new JTextField(100);
			if (i == 6) {
				topPanel.add(cb_Regist);
			} else {
				topPanel.add(fields_Regist[i]);
			}
		}
		topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		this.add("North", topPanel); // 가장 위쪽 Panel 설정
		topPanel.setBackground(Color.DARK_GRAY);

		// 중앙 스크롤테이블(도서정보 입력시 그 내용이 출력되는 영역)
		table_Regist = new JTable(model_Regist);
		// table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrolledTable_Regist = new JScrollPane(table_Regist); // 스크롤 될 수 있도록 JScrollPane 적용
		scrolledTable_Regist.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // 너무 붙어있어서 가장자리 띄움(padding)
		this.add("Center", scrolledTable_Regist); // 가운데에 JTable 추가

		// 하단 패널(추가/제외/저장 버튼이 위치하는 영역)
		JPanel bottomPanel = new JPanel(new GridLayout(1, 3, 10, 10));

		addBtn = new JButton("추가");
		delBtn = new JButton("제외");
		saveBtn_Regist = new JButton("저장");

		// 추가,제외,저장 각 버튼 이미지 삽입
		// 추가버튼 이미지
		BufferedImage bfi_add = null;
		try {
			bfi_add = ImageIO.read(new File("src/lmp/admin/menuButtonImages/plusBtnIcon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image image_add = bfi_add.getScaledInstance(60, 60, Image.SCALE_AREA_AVERAGING);

		addBtn = BookMgmt.getButton(" 추가");
		addBtn.setIcon(new ImageIcon(image_add));
		addBtn.setBounds(420, 5, 120, 40);
		addBtn.setForeground(Color.BLACK);

		// 제외버튼 이미지
		BufferedImage bfi_del = null;
		try {
			bfi_del = ImageIO.read(new File("src/lmp/admin/menuButtonImages/minusBtnIcon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image del = bfi_del.getScaledInstance(60, 60, Image.SCALE_AREA_AVERAGING);

		delBtn = BookMgmt.getButton(" 제외");
		delBtn.setIcon(new ImageIcon(del));
		delBtn.setBounds(720, 0, 120, 40);
		delBtn.setForeground(Color.BLACK);

		// 저장버튼 이미지
		BufferedImage bfi_save = null;
		try {
			bfi_save = ImageIO.read(new File("src/lmp/admin/menuButtonImages/saveIconImage_Regist.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image save = bfi_save.getScaledInstance(60, 60, Image.SCALE_AREA_AVERAGING);

		saveBtn_Regist = BookMgmt.getButton(" 저장");
		saveBtn_Regist.setIcon(new ImageIcon(save));
		saveBtn_Regist.setBounds(1020, 0, 120, 40);
		saveBtn_Regist.setForeground(Color.BLACK);

		bottomPanel.add(addBtn);
		bottomPanel.add(delBtn);
		bottomPanel.add(saveBtn_Regist);
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.add("South", bottomPanel); // 오른쪽에 버튼들 추가

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1000, 500);
		this.setLocationRelativeTo(null); // 창 가운데 위치
		this.setVisible(true);

		// 이벤트 추가
		addBtn.addMouseListener(this); // 추가 처리
		delBtn.addMouseListener(this); // 삭제 처리
		saveBtn_Regist.addMouseListener(this);
		for (int i = 0; i < labels_Regist.length; i++)
			fields_Regist[i].addKeyListener(this); // 엔터 처리
		table_Regist.addMouseListener(this); // 셀 읽기 처리
	}

	private boolean isInvalidInput(String input) {
		return input == null || input.length() == 0;
	}

	public void removeRecord(int index) {
		DefaultTableModel model = (DefaultTableModel) table_Regist.getModel();
		if (index < 0) {
			if (table_Regist.getRowCount() == 0)// 비어있는 테이블이면
				return;
			index = 0;
		}
		model.removeRow(index);
	}

	public void addRecord() {
		
		// 정보 없는 칸 있으면 거르기
		for (int i = 0; i < labels_Regist.length; ++i) {
			if (!(labels_Regist[i].equals("위치") || labels_Regist[i].equals("비고"))) {
				if (isInvalidInput(fields_Regist[i].getText())) {
					JOptionPane.showMessageDialog(this, "입력하지 않은 정보가 있습니다.");
					return;
				} else if (labels_Regist[i].equals("편권수")) {
					try {
						if (Integer.parseInt(fields_Regist[i].getText()) <= 0) {
							JOptionPane.showMessageDialog(null, "편권수에 1이상의 수를 입력해주세요.");
							return;
						}
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "편권수를 숫자로 입력해주세요.");
						return;
					}
				} else if (labels_Regist[i].equals("가격")) {
					try {
						if (Integer.parseInt(fields_Regist[i].getText()) <= 0) {
							JOptionPane.showMessageDialog(null, "가격에 1이상의 수를 입력해주세요.");
							return;
						}
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "가격을 숫자로 입력해주세요.");
						return;
					}
				}
			}
		}
		
		++rowCount;
		model_Regist.setRowCount(rowCount);
		for (int i = 0; i < labels_Regist.length; i++) {
			if (!labels_Regist[i].equals("위치")) {
					model_Regist.setValueAt(fields_Regist[i].getText(), rowCount - 1, i);
				
			} else {
				model_Regist.setValueAt(cb_Regist.getSelectedItem(), rowCount - 1, i);
			}
		}
		
		
//		++rowCount;
//		model_Regist.setRowCount(rowCount);
		table_Regist.setModel(model_Regist);
		scrolledTable_Regist.validate();
		// 모든 TextField 비우기
		for (int i = 0; i < labels_Regist.length; i++)
			fields_Regist[i].setText("");
		fields_Regist[0].requestFocus();
		
	}
	
	public void saveRecord() {
		
		if (model_Regist.getRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "저장할 도서를 추가해주세요.");
			return;
		}
		
		BookDao bookDao = new BookDao();
		BookVO bookVo = new BookVO();
		
		for (int i = 0; i < model_Regist.getRowCount(); ++i) {
			bookVo.setTitle(model_Regist.getValueAt(i, 0).toString());
			bookVo.setAuthor(model_Regist.getValueAt(i, 1).toString());
			bookVo.setPublisher(model_Regist.getValueAt(i, 2).toString());
			bookVo.setIsbn(model_Regist.getValueAt(i, 3).toString());
			bookVo.setBias(Integer.parseInt(model_Regist.getValueAt(i, 4).toString()));
			bookVo.setPrice(Integer.parseInt(model_Regist.getValueAt(i, 5).toString()));
			bookVo.setLocation(new LocationVO(model_Regist.getValueAt(i, 6).toString().substring(0, 1), model_Regist.getValueAt(i, 6).toString().substring(2)));
			if (model_Regist.getValueAt(i, 7) != null) {
				bookVo.setNote(model_Regist.getValueAt(i, 7).toString());
			}
			try {
				bookDao.add(bookVo);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		JOptionPane.showMessageDialog(null, "도서 정보가 등록되었습니다.");
	}

	public void printCell(int row, int col) {
		DefaultTableModel model = (DefaultTableModel) table_Regist.getModel();
		System.out.println(model.getValueAt(row, col));
	}

	// MouseListener Overrides
	@Override
	public void mouseClicked(MouseEvent e) {
		Object src = e.getSource();
		if (src == addBtn)
			addRecord();

		if (src == delBtn) {
			int selected = table_Regist.getSelectedRow();
			removeRecord(selected);
		}
		
		if (src == saveBtn_Regist) {
			saveRecord();
			rowCount = 0;
			model_Regist.setRowCount(rowCount);
			table_Regist.setModel(model_Regist);
		}

		// 저장버튼 클릭 시 작동기능 명령(196-216)
//		if (src == saveBtn_Regist) {
//			int num = JOptionPane.showConfirmDialog(table, "정말로 저장합니까?", "DB에 저장하기", JOptionPane.YES_NO_OPTION);
//			switch (num) {
//			case 0:
//				BookDao bookDao = new BookDao();
//				ArrayList<BookVO> bookVO = new ArrayList<>();
//				try {
//					bookVO.clear();
//					bookVO.addAll(bookDao.get(1, String.valueOf(table.getValueAt(table.getSelectedRow(), 0))));
//					bookDao.add(bookVO.get(0));
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				}
//				table.validate();
//				JOptionPane.showMessageDialog(table, "입력하신 도서의 정보가 DB에 저장되었습니다.");
//				break;
//			case 1:
//				JOptionPane.showMessageDialog(table, "취소합니다.");
//				break;
//			}				
//		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	// KeyListener Overrides
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
//		if (keyCode == KeyEvent.VK_ENTER) {
//			addRecord();
//		}
	}

}