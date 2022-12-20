package lmp.admin.menu.checkin_out;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import lmp.admin.AdminFrame;
import lmp.db.dao.MemberDao;
import lmp.db.vo.MemberVO;

public class Member_Searching_Panel extends JPanel {

	JLabel label;
	JComboBox keyword;
	JButton searchButton, mgmtButton;
	JTextField searchField;
	JScrollPane result;
	JTable table;
	
	String[] keywordList = {"회원번호", "이름", "아이디", "연락처"};
	
	// 패널의 열 수는 검색된 정보 수에 따라 다르게 설정 가능
	// DB에서 회원정보 뽑아서 아래 액션 리스너 내부 조건에 맞는 배열 생성
	// 대출 자격은 대출 테이블에서 해당 회원의 기록 중 대출일만 있고 반납일이 없는 내역이 3개면 대출 불가능, 2개 이하면 대출 가능
	// && 반납 예정일이 지났지만 반납일이 없는 경우 (연체)면 대출 불가능
	String[] memberColumn = {"회원번호", "이름", "아이디", "비밀번호", "생년월일", "성별", "전화번호", "이메일", "주소", "가입일", "비고"};
	
	MemberDao memberDao = new MemberDao();
	ArrayList<MemberVO> memVoList = new ArrayList<>();
	
	
	// 배열의 길이 수정해야함
	// 임의로 50 넣어놓음
	String[][] valid = new String[50][memberColumn.length];
	
	DefaultTableModel model = new DefaultTableModel(memberColumn, 20) {
		public boolean isCellEditable(int row, int column) {
			return false;
		};
	};
	
	
	public ArrayList<MemberVO> getMemVoList() {
		return memVoList;
	}
	
	
	public Member_Searching_Panel() {
		
		setLayout(null);
		setBackground(new Color(87, 119, 119));
		
		table = AdminFrame.getTable(model);
		
		result = new JScrollPane(table);
		result.setBounds(0, 156, 1152, 395);
		
		
		label = new JLabel("회원 검색");
		label.setFont(new Font("한컴 말랑말랑 Regular", Font.BOLD, 20));
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBounds(475, 20, 200, 50);
		
		
		keyword = new JComboBox(keywordList);
		keyword.setFont(new Font("한컴 말랑말랑 Regular", Font.BOLD, 15));
		keyword.setBounds(190, 80, 150, 30);
		
		searchButton = AdminFrame.getButton("검색");
		try {
			BufferedImage buffer = ImageIO.read(new File("src/lmp/admin/menuButtonImages/searchButtonIcon.png"));
			Image image = buffer.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			searchButton.setIcon(new ImageIcon(image));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		searchButton.setBounds(810, 55, 100, 80);
		
		// 텍스트 필드에서 엔터 누르면 버튼 클릭되도록 액션 추가 (검색 버튼 눌러도 되고 텍스트 필드에서 엔터 눌러도 검색됨)
		searchField = new JTextField(30);
		searchField.setBounds(390, 80, 400, 30);
		searchField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchButton.doClick();
			}
		});
		
		
		mgmtButton = AdminFrame.getButton("대출/반납 관리");
		try {
			BufferedImage buffer = ImageIO.read(new File("src/lmp/admin/menuButtonImages/checkOutImage.png"));
			Image image = buffer.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			mgmtButton.setIcon(new ImageIcon(image));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		mgmtButton.setBounds(1020, 35, 130, 100);
		mgmtButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1 || model.getValueAt(table.getSelectedRow(), 0) == null) {
					JOptionPane.showMessageDialog(null, "회원을 선택해주세요.");
					return;
				}
				new CheckIn_Out_Frame(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
			}
		});
		
		add(label);
		add(keyword);
		add(searchField);
		add(searchButton);
		add(mgmtButton);
		add(result);
				
		
		// 검색 버튼 눌렀을 때 해당 키워드에 맞는 정보 있으면 출력
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				String[][] valid = new String[50][memberColumn.length];
				
				// 체크박스와 동일한 컬럼 인덱스 찾는 과정
//				int index = 0;
//				for (int i = 0; i < memberColumn.length; ++i) {
//					if (memberColumn[i].equals(keyword.getSelectedItem())) {
//						index = i;
//					}
//				}
				
				// data 살펴보면서 검색 내용이 포함된 정보가 있으면 valid에 추가
//				for (int i = 0, index2 = 0; i < memberInfo.length; ++i) {
//					if (memberInfo[i][index].contains(searchField.getText())) {
//						valid[index2++] = memberInfo[i];
//					}
//				}
				
				// 조건에 맞는 정보로 회원 조회 후 memVoList에 담기
				try {
					memVoList.clear();
					memVoList.addAll(memberDao.get(keyword.getSelectedIndex() + 1, searchField.getText()));
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				
				// 테이블 열 개수 수정
				model.setRowCount(memVoList.size());
				// 테이블 데이터 수정 (추가?)
				// 테이블 수정 안되게 세팅
				int num = 0;
				for (MemberVO list : memVoList) {
					for (int i = 0; i < list.getList().length; ++i) {
						model.setValueAt(list.getList()[i], num, i);
					}
					++num;
				}
				
//				try {
//					model.setColumnIdentifiers(memberColumn);
//					for (int i = 0; i < ;) {
//						
//					}
//					model.addRow(d.get(keyword.getItemCount() + 2, searchField.getText()).toArray());
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				}
//				for (int i = 0; i < valid.length; ++i) {
//					for (int j = 0; j < memberColumn.length; ++j) {
//						model.setValueAt(valid[i][j], i, j);
//					}
//				}
				table.setModel(model);
				table.validate();
				
				
				// 컬럼별 최소 너비 설정
//				for (int i = 0; i < category.length; ++i) {
//					table.getColumnModel().getColumn(i).setMinWidth(250);
//				}
				// 컨테이너 사이즈에 따라 자동으로 테이블 크기 조정 안되게 세팅 (가로 스크롤 나오게 설정)
//				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				
				result.setViewportView(table);
				validate();
				
			}
		});
		
		// 회원 정보 더블 클릭하면 새로운 프레임 생성
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 더블 클릭시 값이 들어있는 행만 작동하도록 (행을 검색된 정보 수에 맞게 출력하면 조건 없어도 됨)
				if (e.getClickCount() == 2 && table.getValueAt(table.getSelectedRow(), 0) != null) {
					new CheckIn_Out_Frame(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
				} else if (table.getValueAt(table.getSelectedRow(), 0) == null) {
					JOptionPane.showMessageDialog(null, "회원을 선택해주세요");
				}
			}
		});
		
		
	}
	
}
