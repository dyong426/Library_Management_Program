package lmp.admin.menu.member;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import lmp.admin.AdminFrame;
import lmp.admin.menu.book.BookMgmt;
import lmp.db.dao.MemberDao;
import lmp.db.dao.MenuDao;
import lmp.db.vo.MemberVO;

public class MemberMgmt extends JPanel {

	// JTable 구성품 
	String[] header = {"회원번호", "이름", "아이디", "비밀번호", "생년월일", "성별", "전화번호", "이메일", "주소",
			"등록일", "비고"};
	DefaultTableModel model = new DefaultTableModel(header, 30);
	JTable table;
	JScrollPane scroll;

	public MemberMgmt() {

		JLabel memberInquiry = new JLabel("회원 조회"); // 회원조회글씨
		JTextField searchField = new JTextField(); // 검색창	
		JButton searchBtn = AdminFrame.getButton("검색"); // 검색버튼
		JButton changeBtn = BookMgmt.getButton("수정"); // 수정버튼
		JButton deleteBtn = BookMgmt.getButton("삭제"); // 삭제버튼

		// 회원조회 타이틀 설정
		memberInquiry.setBounds(475, 20, 200, 50);
		memberInquiry.setFont(new Font(null, Font.BOLD, 20));
		memberInquiry.setForeground(Color.WHITE);
		memberInquiry.setHorizontalAlignment(JLabel.CENTER);
		add(memberInquiry);

		// 텍스트필드 설정
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
		try {
			BufferedImage buffer = ImageIO.read(new File("src/lmp/admin/menuButtonImages/searchButtonIcon.png"));
			Image image = buffer.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			searchBtn.setIcon(new ImageIcon(image));
		} catch (IOException e3) {
			e3.printStackTrace();
		}
		add(searchBtn);

		// 수정버튼 설정
		changeBtn.setBounds(1020, 20, 120, 40);
		try {
			BufferedImage buffer = ImageIO.read(new File("src/lmp/admin/menuButtonImages/bookModifyIconImage.png"));
			Image image = buffer.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
			changeBtn.setIcon(new ImageIcon(image));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		add(changeBtn);

		// 삭제버튼 설정
		deleteBtn.setBounds(1020, 90, 120, 40);
		try {
			BufferedImage buffer = ImageIO.read(new File("src/lmp/admin/menuButtonImages/bookdeleteIconImage.png"));
			Image image = buffer.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
			deleteBtn.setIcon(new ImageIcon(image));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		add(deleteBtn);


		// 콤보박스로 검색할내용 선택하기
		String[] keywordList = {"회원번호", "이름", "아이디", "생년월일", "성별", "전화번호"};
		JComboBox keyword = new JComboBox<>(keywordList);
		keyword.setFont(new Font(null, Font.BOLD, 15));
		keyword.setBounds(190, 80, 150, 30);
		add(keyword);


		table = new JTable(model);
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(25);
		scroll = new JScrollPane(table);
		scroll.setBounds(0, 156, 1152, 395);
		add(scroll);

		// 검색 버튼누르면 결과가 JTable로 생성
		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MemberDao mdao = new MemberDao();
				try {
					ArrayList<MemberVO> mems = new ArrayList<>();

					mems.addAll(mdao.get(keyword.getSelectedIndex() + 1, searchField.getText()));
					int num = 0;
					model.setRowCount(mems.size());
					for (MemberVO mem : mems) {						
						for (int i = 0; i < mem.getList().length; i++) {
							model.setValueAt(mem.getList()[i], num, i);
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
		
		// 수정버튼
		changeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame j = new JFrame();

				JLabel join = new JLabel("회원수정");
				JLabel id = new JLabel("아이디");
				JLabel name = new JLabel("이름");
				JLabel birth = new JLabel("생년월일");
				JLabel sex = new JLabel("성별");
				JLabel phone = new JLabel("전화번호");
				JLabel email = new JLabel("이메일");
				JLabel address = new JLabel("주소");

				JTextField idField = new JTextField
						(model.getValueAt(table.getSelectedRow() , 2).toString());
				JTextField nameField = new JTextField
						(model.getValueAt(table.getSelectedRow() , 1).toString());
				JTextField birthField = new JTextField
						(model.getValueAt(table.getSelectedRow() , 4).toString());
				JTextField sexField = new JTextField
						(model.getValueAt(table.getSelectedRow() , 5).toString());
				JTextField phoneField = new JTextField
						(model.getValueAt(table.getSelectedRow() , 6).toString());
				JTextField emailField = new JTextField
						(model.getValueAt(table.getSelectedRow() , 7).toString());
				JTextField addressField = new JTextField
						(model.getValueAt(table.getSelectedRow() , 8).toString());

				JButton idcheckBtn = new JButton("중복확인");
				JButton emailcheckBtn = new JButton("중복확인");
				JButton joinBtn = new JButton("가입하기");
				JButton changeBtn2 = new JButton("수정");
				JButton cancelBtn = new JButton("취소");


				setlabel2(join, 40, 55, 13);
				j.add(join);

				setlabel2(id, 18, 40, 90);
				setField(idField, 113);
				setBtn(idcheckBtn, 13, 400, 113);
				checkBtn(idcheckBtn);
				idField.setEditable(false);
				j.add(id);
				j.add(idField);

				setlabel2(name, 18, 40, 140);
				setField(nameField, 163);
				j.add(name);
				j.add(nameField);

				setlabel2(birth, 18, 40, 190);
				setField(birthField, 213);
				birthField.setEditable(false);
				j.add(birth);
				j.add(birthField);

				setlabel2(sex, 18, 40, 240);
				setField(sexField, 263);
				sexField.setEditable(false);
				j.add(sex);
				j.add(sexField);
				
				setlabel2(phone, 18, 40, 290);
				setField(phoneField, 313);
				j.add(phone);
				j.add(phoneField);

				setlabel2(email, 18, 40, 340);
				setField(emailField, 363);
				setBtn2(emailcheckBtn, 13, 350, 363, 80, 30);
				checkBtn(emailcheckBtn);
				j.add(email);
				j.add(emailField);
				j.add(emailcheckBtn);

				setlabel2(address, 18, 40, 390);
				setField(addressField, 413);
				j.add(address);
				j.add(addressField);

				setBtn2(changeBtn2, 18, 280, 480, 80, 40 );
				j.add(changeBtn2);
				
				changeBtn2.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int var = JOptionPane.showConfirmDialog
								(null, "수정하시겠습니까?", " ",
										JOptionPane.YES_NO_OPTION,
										JOptionPane.INFORMATION_MESSAGE, null);
						if (var == JOptionPane.YES_OPTION) {
							MenuDao mdao = new MemberDao();
							MemberVO vo = new MemberVO((int)model.getValueAt(table.getSelectedRow() , 0),
									nameField.getText(),
									phoneField.getText(),
									emailField.getText(),
									addressField.getText()
									);
							
							try {
								mdao.update(vo);
								ArrayList<MemberVO> mems = new ArrayList<>();

								mems.addAll(mdao.get(keyword.getSelectedIndex() + 1, searchField.getText()));
								int num = 0;
								model.setRowCount(mems.size());
								for (MemberVO mem : mems) {						
									for (int i = 0; i < mem.getList().length; i++) {
										model.setValueAt(mem.getList()[i], num, i);
									}
									num++;
								}
								j.dispose();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}


					}
				});

				j.setLayout(null);
				j.setBounds(330, 130, 480, 600);
				j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				j.setVisible(true);

			}
		});


		// 삭제버튼
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int var = JOptionPane.showConfirmDialog
						(null, "탈퇴하시겠습니까?", " ",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.INFORMATION_MESSAGE, null);
				if (var == JOptionPane.YES_OPTION) {
					MenuDao mdao = new MemberDao();
					try {
						System.out.println(model.getValueAt(table.getSelectedRow() , 0));
						mdao.delete(table.getValueAt(table.getSelectedRow(), 0).toString());

						// 삭제되면 테이블 업데이트
						ArrayList<MemberVO> mems = new ArrayList<>();
						mems.addAll(mdao.get(keyword.getSelectedIndex() + 1, searchField.getText()));
						int num = 0;
						model.setRowCount(mems.size());
						for (MemberVO mem : mems) {						
							for (int i = 0; i < mem.getList().length; i++) {
								model.setValueAt(mem.getList()[i], num, i);
							}
							num++;
						}

					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			}
		});

		// 판넬기본설정
		setLayout(null);
		//setBorder(new LineBorder(Color.WHITE, 5, false)); // 판넬테두리 
//		setBounds(0, 100, 1180, 650);
		setBackground(new Color(87, 119, 119));
	}

	// 라벨 생성 및 설정함수
	public void setlabel(JLabel label , int size, int x, int y) {
		Font font = new Font("한컴 말랑말랑 Bold", Font.BOLD, size);
		label.setFont(font);
		label.setForeground(Color.WHITE);
		label.setBounds(x, y, 300, 30);
		add(label);
	}

	public void setlabel2(JLabel label , int size, int x, int y) {
		Font font = new Font("한컴 말랑말랑 Bold", Font.BOLD, size);
		label.setFont(font);
		label.setForeground(new Color(49, 82, 91));
		label.setBounds(x, y, 200, 70);
		add(label);
	}

	// 버튼 생성 및 설정함수
	public void setBtn(JButton button , int size, int x, int y) {
		Font font = new Font("한컴 말랑말랑 Bold", Font.BOLD, size);

		button.setFont(font);
		button.setBackground(Color.WHITE);
		button.setForeground(new Color(49, 82, 91));
		button.setFocusable(false);
		button.setBounds(x, y, 70, 50);
		add(button);
	}

	public void setBtn2(JButton button , int size, int x, int y, int a, int b) {
		Font font = new Font("한컴 말랑말랑 Bold", Font.BOLD, size);

		button.setFont(font);
		button.setBackground(new Color(87, 119, 119));
		button.setForeground(Color.WHITE);
		button.setFocusable(false);
		button.setBounds(x, y, a, b);
		add(button);
	}

	// 텍스트필드 생성 및 설정함수
	public void setField(JTextField field, int y) {
		Font font = new Font("한컴 말랑말랑 Bold", Font.BOLD, 13);
		field.setFont(font);
		field.setBounds(130, y, 200, 30);
		field.setBorder(new LineBorder(new Color(49, 82, 91), 2, false));
		add(field);
	}

	// 중복확인 버튼 액션리스너
	public void checkBtn(JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame r = new JFrame();

				JLabel delete = new JLabel("사용가능합니다");

				delete.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 20));
				delete.setBounds(75, 40, 250, 30);
				r.add(delete);


				r.dispose();
				r.setLayout(null);
				r.setBounds(450, 350, 300, 150);
				r.getContentPane().setBackground(Color.WHITE);
				r.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				r.setVisible(true);

			}
		});
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();

		f.add(new MemberMgmt());

		f.setLayout(null);
		f.setBounds(300, 100, 1200, 800);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}


}
