package lmp.admin.menu.checkin_out;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
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
import javax.swing.table.DefaultTableModel;

import lmp.admin.AdminFrame;
import lmp.db.dao.BookDao;
import lmp.db.dao.CheckOutDao;
import lmp.db.vo.CheckOutVO;

public class CheckIn_Out_Frame extends JFrame{

	JFrame frame = this;
	
	JPanel checkPanel;
	JLabel checkedOutLabel;
	JTable checkedOutTable, checkOutTable;
	JScrollPane checkedOutPane, checkOutPane;
	JButton checkInButton, searchbutton, checkOutButton;
	JLabel checkInLabel;
	JComboBox keyword;
	JTextField searchField;
	
	
	//	table.getValueAt(table.getSelectedRow(), 0)
	// 위 코드로 회원번호 뽑아서 DB에서 해당 회원번호에 맞는 정보 가져와서 출력 아래 배열에 저장
	String[] checkedOutCategory = {"등록번호", "도서명", "저자", "대출 일자", "반납 예정일"};
	String[][] checkedOutList = {
			{"BSDF156", "노인과 바다", "헤밍웨이", "2022/12/08", "2022/12/21"},
			{"QWT2654", "어린왕자", "생텍쥐페리", "2022/12/01", "2022/12/14"},
			{"QWT2654", "어린왕자", "생텍쥐페리", "2022/12/01", "2022/12/14"}
	};
	
	String[] category = {"등록번호", "도서명", "저자", "출판사", "ISBN", "편권수", "복권수", "도서 등록일", "가격", "비고"};
	
	
	String[] keywordList = {"등록번호", "도서명", "저자", "출판사", "ISBN", "위치"};
	
	DefaultTableModel model = new DefaultTableModel(category, 7) {
		public boolean isCellEditable(int row, int column) {
			return false;
		};
	};
	
	BookDao bdao = new BookDao();
	CheckOutDao cdao = new CheckOutDao();
	
	public CheckIn_Out_Frame() {
		setTitle("회원 대출 관리");
		
		checkOutTable = new JTable(model);
		checkOutPane = new JScrollPane(checkOutTable);
		
		checkOutTable.getTableHeader().setReorderingAllowed(false);
		checkOutTable.setRowHeight(23);
		checkOutTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		checkOutPane.setBounds(0, 300, 986, 184);
		

//		String[] checkOutCategory = new String[5];
//		String[][] checkOutList = new String[3][5];
		
		checkPanel = new JPanel();
		checkPanel.setLayout(null);
		
		checkedOutLabel = new JLabel("대출 목록");
		checkedOutLabel.setFont(new Font(null, Font.BOLD, 20));
		checkedOutLabel.setHorizontalAlignment(JLabel.CENTER);
		checkedOutLabel.setBounds(440, 10, 100, 50);
		checkedOutLabel.setForeground(Color.WHITE);
		
		
		// 대출 목록 3행 빈 테이블 넣어두고 DB에서 가져온 데이터로 채우기
		checkedOutTable = new JTable(new DefaultTableModel(category, 3) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		checkedOutPane = new JScrollPane(checkedOutTable);
		checkedOutTable.getTableHeader().setReorderingAllowed(false);
		checkedOutTable.setRowHeight(23);
		checkedOutTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		checkedOutPane.setBounds(0, 70, 986, 92);
		
		
		checkInButton = AdminFrame.getButton("반납");
//		BufferedImage buffer = ImageIO.read(new File("src/"));
		checkInButton.setHorizontalTextPosition(JButton.RIGHT);
		checkInButton.setVerticalTextPosition(JButton.CENTER);
		checkInButton.setBounds(850, 180, 100, 30);
		
		// 반납 버튼을 누르면 DB에 존재하는 해당 등록번호의 도서 정보 업데이트
		checkInButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkedOutTable.getSelectedRow() != -1) {
					if (checkedOutTable.getValueAt(checkedOutTable.getSelectedRow(), 0) != null) {
						System.out.println(checkedOutTable.getValueAt(checkedOutTable.getSelectedRow(), 0));
						
						JOptionPane.showMessageDialog(
								frame, String.format("등록번호 : %s\n\t반납 완료",
										checkedOutTable.getValueAt(checkedOutTable.getSelectedRow(), 0)));
						for (int i = 0; i < category.length; ++i) {
							checkedOutTable.setValueAt(null, checkedOutTable.getSelectedRow(), i);						
						}
					} else {
						JOptionPane.showMessageDialog(frame, "반납할 도서를 선택해주세요.");
					}
				} else {
					JOptionPane.showMessageDialog(frame, "반납할 도서를 선택해주세요.");					
				}
			}
		});
		
		
		checkInLabel = new JLabel("대출 희망 도서 검색");
		checkInLabel.setFont(new Font(null, Font.BOLD, 20));
		checkInLabel.setHorizontalAlignment(JLabel.CENTER);
		checkInLabel.setBounds(390, 200, 200, 50);
		checkInLabel.setForeground(Color.WHITE);
		
		
		keyword = new JComboBox(keywordList);
		keyword.setFont(new Font(null, Font.BOLD, 15));
		keyword.setBounds(120, 250, 120, 30);
		
		searchbutton = AdminFrame.getButton("검색");
//		try {
//			BufferedImage buffer = ImageIO.read(new File("src/lmp/admin/menuButtonImages/searchButtonIcon.png"));
//			Image image = buffer.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
//			searchbutton.setIcon(new ImageIcon(image));
//		} catch (IOException e2) {
//			e2.printStackTrace();
//		}
		searchbutton.setBounds(720, 230, 70, 70);
		
		// 텍스트 필드에서 엔터 누르면 버튼 클릭되도록 액션 추가 (검색 버튼 눌러도 되고 텍스트 필드에서 엔터 눌러도 검색됨)
		searchField = new JTextField();
		searchField.setBounds(310, 250, 350, 30);
		searchField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchbutton.doClick();
			}
		});
		
		
		
		checkOutButton = AdminFrame.getButton("대출");
		checkOutButton.setHorizontalTextPosition(JButton.RIGHT);
		checkOutButton.setVerticalTextPosition(JButton.CENTER);
//		BufferedImage buffer;
//		try {
//			buffer = ImageIO.read(new File("src/lmp/admin/menuButtonImages/checkInImage.png"));
//			Image image = buffer.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
//			checkOutButton.setIcon(new ImageIcon(image));
//		} catch (IOException e2) {
//			e2.printStackTrace();
//		}
		// 버튼을 누르면 VO에 들어있는 현재 회원의 정보, 해당 도서의 정보 업데이트
		checkOutButton.setBounds(850, 490, 120, 70);
		
		searchbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				int index = 0;
//				for (int i = 0; i < category.length; ++i) {
//					if (category[i].equals(keyword.getSelectedItem())) {
//						index = i;
//					}
//				}
				
				// DB에서 도서 정보 테이블 컬럼, 도서 정보 가져와서 category, contents에 넣어야 함
//				String[][] valid = new String[50][category.length];
//				int index2 = 0;
//				for (int i = 0; i < contents.length; ++i) {
//					if (contents[i][index].contains(searchField.getText())) {
//						valid[index2++] = contents[i];
//					}
//				}
//				model.setRowCount(valid.length);
//				
//				for (int i = 0; i < valid.length; ++i) {
//					for (int j = 0; j < category.length; ++j) {
//						model.setValueAt(valid[i][j], i, j);
//					}
//				}
				try {
					model.addRow(bdao.get(keyword.getItemCount(), searchField.getText()).toArray());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				checkOutTable.setModel(model);
				checkPanel.validate();
				
				checkOutButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// 행 선택 후 대출 버튼 클릭시 안내문구 출력 (정보가 없는 행 선택 시 다른 안내문구 출력)
						if (checkOutTable.getValueAt(checkOutTable.getSelectedRow(), 0) != null) {
//							cdao.add(cdao.);
							JOptionPane.showMessageDialog(frame, String.format("등록번호 : %s\n\t대출 완료",
									checkOutTable.getValueAt(checkOutTable.getSelectedRow(), 0)));
						} else {
							JOptionPane.showMessageDialog(frame, "대출할 도서를 선택해주세요.");							
						}
						// 대출 버튼 클릭시 해당 도서가 대출 목록의 빈 행에 들어가도록 빈 행 찾는 코드
						int emptyRow = 0;
						for (int i = 0; i < checkedOutTable.getRowCount(); ++i) {
							if (checkedOutTable.getValueAt(i, 0) == null) {
								emptyRow = i;
								break;
							}
						}
//						for (int i = 0; i < category.length; ++i) {
//							checkedOutTable.setValueAt(checkOutTable.getValueAt(checkOutTable.getSelectedRow(), i), emptyRow, i);
//							checkPanel.validate();
//						}
						// 대출 완료시 회원 대출 목록 업데이트
						
					}
				});
			}
		});
		
		checkPanel.add(checkedOutLabel);
		checkPanel.add(checkedOutPane);
		checkPanel.add(checkInButton);
		checkPanel.add(checkInLabel);
		checkPanel.add(keyword);
		checkPanel.add(searchField);
		checkPanel.add(searchbutton);
		checkPanel.add(checkOutButton);
		checkPanel.add(checkOutPane);
		checkPanel.setBackground(new Color(49, 82, 91));
		

		add(checkPanel);
		setBounds(600, 200, 1000, 600);
		setResizable(false);
		setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new CheckIn_Out_Frame();
	}
}
