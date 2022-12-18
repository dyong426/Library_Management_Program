package lmp.admin.menu.employees;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import lmp.admin.AdminFrame;
import lmp.admin.menu.book.BookMgmt;
import lmp.admin.menu.member.MemberMgmt;
import lmp.db.dao.AdminDao;
import lmp.db.dao.MenuDao;
import lmp.db.vo.AdminVO;


public class EmployeesMgmt extends JPanel {
	
	// JTable 구성품
	private String[] header = {"사번", "이름", "비밀번호", "전화번호", "이메일", "주소" ,"입사일", "비고"};
	private DefaultTableModel model = new DefaultTableModel(header, 20) {
		public boolean isCellEditable(int row, int column) {
			return false;
		};
	};
	private JTable table;
	JScrollPane scroll;

	JPanel panel = this;
	
	public EmployeesMgmt() {

		JLabel employeeInquiry = new JLabel("직원 조회"); // 직원 조회 라벨
		JTextField searchField = new JTextField(); // 검색창
		JButton searchBtn = AdminFrame.getButton("검색"); // 검색 버튼
		JButton addBtn = BookMgmt.getButton("추가"); // 추가 버튼
		JButton deleteBtn = BookMgmt.getButton("삭제"); // 삭제 버튼
		
		// 직원조회 타이틀 설정
		employeeInquiry.setBounds(475, 20, 200, 50);
		employeeInquiry.setFont(new Font("한컴 말랑말랑 Regular", Font.BOLD, 20));
		employeeInquiry.setForeground(Color.WHITE);
		employeeInquiry.setHorizontalAlignment(JLabel.CENTER);
		add(employeeInquiry);

		// "이름" 텍스트필드 설정
		searchField.setBounds(390, 80, 400, 30);
		searchField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchBtn.doClick();
			}
		});
		add(searchField);

		// 검색버튼 설정
		searchBtn.setBounds(810, 55, 100, 80);
		add(searchBtn);


		// 콤보박스로 검색할내용 선택하기
		String[] keywordList = {"사번", "이름", "전화번호", "입사일"};
		JComboBox keyword = new JComboBox<>(keywordList);
		keyword.setBounds(190, 80, 150, 30);
		keyword.setFont(new Font("한컴 말랑말랑 Regular", Font.BOLD, 15));
		add(keyword);
		
		table = AdminFrame.getTable(model);
		scroll = new JScrollPane(table);
		scroll.setBounds(0, 156, 1152, 395);
		add(scroll);
		
		
		try {
			BufferedImage buffer = ImageIO.read(new File("src/lmp/admin/menuButtonImages/searchButtonIcon.png"));
			Image image = buffer.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			searchBtn.setIcon(new ImageIcon(image));
		} catch (IOException e3) {
			e3.printStackTrace();
		}
		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminDao mdao = new AdminDao();
				try {
					ArrayList<AdminVO> admins = new ArrayList<>();
					
					admins.addAll(mdao.get(keyword.getSelectedIndex() + 1, searchField.getText()));
					int num = 0;
					model.setRowCount(admins.size());
					for (AdminVO admin : admins) {
						for (int i = 0; i < admin.getList().length; i++) {
							model.setValueAt(admin.getList()[i], num, i);
						}
						num++;
					}
					searchField.setText("");
					table.validate();
					scroll.validate();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		

		// 추가버튼 설정
		addBtn.setBounds(1020, 20, 120, 40);
		try {
			BufferedImage buffer = ImageIO.read(new File("src/lmp/admin/menuButtonImages/registrationIcon.png"));
			Image image = buffer.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
			addBtn.setIcon(new ImageIcon(image));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		// 추가버튼을 누르면 새창이 뜨면서 정보입력하게..
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addEmployee();
			}
		});
		add(addBtn);

		// 삭제버튼 설정
		deleteBtn.setBounds(1020, 90, 120, 40);
		try {
			BufferedImage buffer = ImageIO.read(new File("src/lmp/admin/menuButtonImages/deleteIcon.png"));
			Image image = buffer.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
			deleteBtn.setIcon(new ImageIcon(image));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		// 삭제버튼 누르면 해당정보 삭제
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (table.getSelectedRow() == -1 || model.getValueAt(table.getSelectedRow(), 0) == null) {
					JOptionPane.showMessageDialog(null, "삭제할 회원을 선택해주세요.");
					return;
				}
				
				int var = JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?", "삭제 확인", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if (var == JOptionPane.YES_OPTION) {
					AdminDao mdao = new AdminDao();
					
					try {
						mdao.delete(table.getValueAt(table.getSelectedRow(), 0).toString());

						// 삭제되면 테이블 업데이트
						ArrayList<AdminVO> admins = new ArrayList<>();
						admins.addAll(mdao.get(keyword.getSelectedIndex() + 1, searchField.getText()));
						int num = 0;
						model.setRowCount(admins.size());
						for (AdminVO admin : admins) {						
							for (int i = 0; i < admin.getList().length; i++) {
								model.setValueAt(admin.getList()[i], num, i);
							}
							num++;
						}

					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "삭제가 완료되었습니다.");
				}
			}	
		});		
		add(deleteBtn);


		// 판넬기본설정
		setLayout(null);
		setBackground(new Color(87, 119, 119));
		//setBorder(new LineBorder(Color.WHITE, 5, false)); // 테두리
	}

	public void addEmployee() {
		JFrame f = new JFrame("직원등록");
		JLabel addemp = new JLabel("직원등록");
		JLabel name = getLabel("이름");
		JLabel birth = getLabel("생년월일");
		JLabel phone = getLabel("전화번호");
		JLabel pw = getLabel("비밀번호");
		JLabel email = getLabel("이메일");
		JLabel address = getLabel("주소");

		
		JTextField nameField = EmployeesMgmt.getTextField();
		JTextField birthField = EmployeesMgmt.getTextField();
		JTextField phoneField = EmployeesMgmt.getTextField();
		JTextField pwNoticeField = new JTextField("필수 입력사항입니다.");
		pwNoticeField.setBounds(130, 140, 150, 30);
		pwNoticeField.setForeground(Color.RED);
		pwNoticeField.setVisible(false);
		JPasswordField pwField = new JPasswordField();
		// passwordField에서는 안내문구를 띄워도 ***... 로 표시되기 때문에 안내문구 출력용 textField 생성
		pwField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (new String(pwField.getPassword()).trim().equals("")) {
					pwField.setText("필수 입력사항입니다.");
					pwField.setForeground(Color.RED);
					pwField.setVisible(false);
					pwNoticeField.setVisible(true);
				}
			}
		});
		pwNoticeField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pwField.setText("");
				pwField.setForeground(Color.BLACK);
				pwNoticeField.setVisible(false);
				pwField.setVisible(true);
				pwField.requestFocus();
			}
		});
		JTextField emailField = EmployeesMgmt.getTextField();
		JTextField addressField = EmployeesMgmt.getTextField();

		// 반복문으로 유효성 검사를 하기 위해 생성
		JTextField[] tfList = {nameField, birthField, phoneField, pwField, emailField, addressField};
		
		JButton finishBtn = new JButton("완료");
		finishBtn.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 18));
		finishBtn.setBackground(new Color(87, 119, 119));
		finishBtn.setForeground(Color.WHITE);
		finishBtn.setFocusable(false);
		

		f.add(pwNoticeField);
		
		
		// 직원등록 타이틀 설정

		addemp.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 30));
		addemp.setForeground(new Color(49, 82, 91));
		addemp.setBounds(50, 30, 150, 35);
		addemp.setHorizontalTextPosition(JLabel.CENTER);
		f.add(addemp);
		
		// 이름 라벨 , 텍스트필드 설정
		f.add(name);
		f.add(nameField);
		name.setBounds(50, 90, 100, 30);
		nameField.setBounds(130, 90, 150, 30);
		
		
		// 비밀번호 라벨 , 텍스트필드 설정
		f.add(pw);
		f.add(pwField);
		pw.setBounds(50, 140, 100, 30);
		pwField.setBounds(130, 140, 150, 30);
		
		// 전화번호 라벨 , 텍스트필드 설정
		f.add(phone);
		f.add(phoneField);
		phone.setBounds(50, 190, 100, 30);
		phoneField.setBounds(130, 190, 150, 30);

		// 이메일 라벨 , 텍스트필드 설정
		f.add(email);
		f.add(emailField);
		email.setBounds(50, 240, 100, 30);
		emailField.setBounds(130, 240, 150, 30);

		// 주소 라벨 , 텍스트필드 설정
		f.add(address);
		f.add(addressField);
		address.setBounds(50, 290, 100, 30);
		addressField.setBounds(130, 290, 150, 30);

		f.add(finishBtn);
		finishBtn.setBounds(290, 340, 70, 40);

		finishBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// 입력하지 않은 정보가 있으면 안내문구 출력 후 완료 버튼 무효
				for (JTextField field : tfList) {
					if (field.getForeground() == Color.RED) {
						JOptionPane.showMessageDialog(null, "입력하지 않은 정보가 존재합니다.");
						return;
					}
				}
				
				AdminDao dao = new AdminDao();
				AdminVO vo = new AdminVO(null,nameField.getText(), new String(pwField.getPassword()),
						phoneField.getText(), emailField.getText(), addressField.getText(),
						null, null); 
				
				try {
					dao.add(vo);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				int num = JOptionPane.showConfirmDialog(null, "등록 완료", "등록이 완료되었습니다.", JOptionPane.OK_OPTION);
				if (num == 0) {
					f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
			}
		});


		f.dispose();
		f.setLayout(null);
		f.setBounds(500, 300, 400, 450);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setVisible(true);
	}
	
	
	public static JLabel getLabel(String text) {
		JLabel label = new JLabel(text);

		Font font = new Font("한컴 말랑말랑 Regular", Font.BOLD, 15);
		
		label.setFont(font);
		label.setForeground(new Color(49, 82, 91));
		
		return label;
	}
	
	
	// 빈 칸 있으면 안내문구 띄우는 textField 생성 메서드
	public static JTextField getTextField() {
		JTextField tf = new JTextField();
		
		tf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (tf.getText().trim().equals("")) {
					tf.setForeground(Color.RED);
					tf.setText("필수 입력사항입니다.");
				}
			}
		});
		tf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tf.getForeground() == Color.RED) {
					tf.setForeground(Color.BLACK);
					tf.setText("");
				}
			}
		});
		
		return tf;
	}
	
}