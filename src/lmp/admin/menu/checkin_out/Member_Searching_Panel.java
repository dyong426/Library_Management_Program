package lmp.admin.menu.checkin_out;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import lmp.admin.dao.MemberDao;

public class Member_Searching_Panel extends JPanel {

	JLabel label;
	JComboBox keyword;
	JButton button;
	JTextField searchField;
	JScrollPane result;
	JTable table;

	String[] keywordList = {"회원번호", "이름"};
	
	// 패널의 열 수는 검색된 정보 수에 따라 다르게 설정 가능
	// DB에서 회원정보 뽑아서 아래 액션 리스너 내부 조건에 맞는 배열 생성
	// 대출 자격은 대출 테이블에서 해당 회원의 기록 중 대출일만 있고 반납일이 없는 내역이 3개면 대출 불가능, 2개 이하면 대출 가능
	// && 반납 예정일이 지났지만 반납일이 없는 경우 (연체)면 대출 불가능
	String[] memberColumn = {"회원번호", "이름", "아이디", "비밀번호", "생년월일", "성별", "전화번호", "이메일", "주소", "가입일", "비고"};
	
	MemberDao d = new MemberDao();
	
	// 배열의 길이 수정해야함
	// 임의로 50 넣어놓음
	String[][] valid = new String[50][memberColumn.length];
	
	DefaultTableModel model = new DefaultTableModel(memberColumn, 24);
	
	public Member_Searching_Panel() {
		
		setLayout(null);
		setBackground(new Color(49, 82, 91));
		
		table = new JTable(model);
		// 테이블 컬럼 이동 안되게 설정
		table.getTableHeader().setReorderingAllowed(false);
		// 테이블에서 하나의 행만 선택되게 설정
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		result = new JScrollPane(table);
		result.setBounds(0, 156, 1152, 395);
		

		label = new JLabel("회원 검색");
		label.setFont(new Font(null, Font.BOLD, 20));
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBounds(475, 20, 200, 50);
		
		
		keyword = new JComboBox(keywordList);
		keyword.setFont(new Font(null, Font.BOLD, 15));
		keyword.setBounds(190, 80, 150, 30);
		
		button = new JButton("검색하기");
		button.setFont(new Font(null, Font.BOLD, 15));
		button.setBounds(840, 80, 100, 30);
		
		// 텍스트 필드에서 엔터 누르면 버튼 클릭되도록 액션 추가 (검색 버튼 눌러도 되고 텍스트 필드에서 엔터 눌러도 검색됨)
		searchField = new JTextField();
		searchField.setBounds(390, 80, 400, 30);
		searchField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.doClick();
			}
		});
		
		add(label);
		add(keyword);
		add(searchField);
		add(button);
		add(result);
		
		// 검색 버튼 눌렀을 때 해당 키워드에 맞는 정보 있으면 출력
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[][] valid = new String[50][memberColumn.length];
				
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
				
				// 테이블 수정 안되게 세팅
				try {
					model = new DefaultTableModel(d.get(keyword.getItemCount(), searchField.getText()).size(), memberColumn.length) {
						@Override
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					};
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					model.addRow(d.get(keyword.getItemCount() + 2, searchField.getText()).toArray());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
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
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
				
				result.setViewportView(table); 
				validate();
				
			}
		});
		
		// 회원 정보 더블 클릭하면 새로운 프레임 생성
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 더블 클릭시 값이 들어있는 행만 작동하도록 (행을 검색된 정보 수에 맞게 출력하면 조건 없어도 됨)
				if (e.getClickCount() == 2 && table.getValueAt(table.getSelectedRow(), 1) != null) {
					new CheckIn_Out_Frame();
				}
			}
		});
		
		
	}
	
}
