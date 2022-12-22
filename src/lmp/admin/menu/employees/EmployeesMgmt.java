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
import lmp.admin.dao.AdminDao;
import lmp.admin.dao.MemberDao;
import lmp.admin.dao.MenuDao;
import lmp.admin.vo.AdminVO;
import lmp.admin.vo.MemberVO;
import lmp.util.Validator;

public class EmployeesMgmt extends JPanel {

	// JTable 구성품
	private String[] header = { "사번", "이름", "전화번호", "이메일", "주소", "입사일", "비고" };
	private DefaultTableModel model = new DefaultTableModel(header, 20) {
		public boolean isCellEditable(int row, int column) {
			return false;
		};
	};
	private JTable table;
	JScrollPane scroll;

	JPanel panel = this;
	
	Validator vd = new Validator();

	public EmployeesMgmt() {

		JLabel employeeInquiry = new JLabel("직원 조회"); // 직원 조회 라벨
		JTextField searchField = new JTextField(); // 검색창
		JButton searchBtn = AdminFrame.getButton("검색"); // 검색 버튼
		JButton addBtn = BookMgmt.getButton("추가"); // 추가 버튼
		JButton changeBtn = BookMgmt.getButton("수정"); // 수정버튼
		JButton deleteBtn = BookMgmt.getButton("삭제"); // 삭제 버튼

		// 직원조회 타이틀 설정
		employeeInquiry.setBounds(600, 30, 300, 50);
		employeeInquiry.setFont(new Font("한컴 말랑말랑 Regular", Font.BOLD, 40));
		employeeInquiry.setForeground(Color.WHITE);
		employeeInquiry.setHorizontalAlignment(JLabel.CENTER);
		add(employeeInquiry);

		// "이름" 텍스트필드 설정
		searchField.setBounds(530, 130, 450, 35);
		searchField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchBtn.doClick();
			}
		});
		add(searchField);

		// 검색버튼 설정
		searchBtn.setBounds(1010, 100, 120, 100);
		add(searchBtn);

		// 콤보박스로 검색할내용 선택하기
		String[] keywordList = { "사번", "이름", "전화번호", "입사일" };
		JComboBox keyword = new JComboBox<>(keywordList);
		keyword.setBounds(270, 130, 200, 35);
		keyword.setFont(new Font("한컴 말랑말랑 Regular", Font.BOLD, 15));
		add(keyword);

		table = AdminFrame.getTable(model);
		scroll = new JScrollPane(table);
		scroll.setBounds(0, 250, 1500, 500);
		add(scroll);

		try {
			BufferedImage buffer = ImageIO.read(new File("src/lmp/admin/menuButtonImages/searchButtonIcon.png"));
			Image image = buffer.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
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
						for (int i = 0; i < header.length; i++) {
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
		addBtn.setBounds(1320, 15, 150, 70);
		try {
			BufferedImage buffer = ImageIO.read(new File("src/lmp/admin/menuButtonImages/registrationIcon.png"));
			Image image = buffer.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
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

		// 수정버튼 설정
		changeBtn.setBounds(1320, 175, 150, 70);
		try {
			BufferedImage buffer = ImageIO.read(new File("src/lmp/admin/menuButtonImages/bookModifyIconImage.png"));
			Image image = buffer.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
			changeBtn.setIcon(new ImageIcon(image));
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		// 삭제버튼 설정
		deleteBtn.setBounds(1320, 95, 150, 70);
		try {
			BufferedImage buffer = ImageIO.read(new File("src/lmp/admin/menuButtonImages/deleteIcon.png"));
			Image image = buffer.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
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

				int var = JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?", "삭제 확인", JOptionPane.YES_NO_OPTION,
						JOptionPane.INFORMATION_MESSAGE);
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

		changeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1 || model.getValueAt(table.getSelectedRow(), 0) == null) {
					JOptionPane.showMessageDialog(null, "수정할 회원을 선택해주세요.");
					return;
				}

				JFrame j = new JFrame();

				JLabel join = new JLabel("직원 수정");
				JLabel id = new JLabel("사번");
				JLabel name = new JLabel("이름");
				JLabel pw = new JLabel("비밀번호");
				JLabel pwCheck = new JLabel("비밀번호 확인");
				JLabel phone = new JLabel("전화번호");
				JLabel email = new JLabel("이메일");
				JLabel address = new JLabel("주소");
				JLabel note = new JLabel("비고");

				JTextField idField = new JTextField(model.getValueAt(table.getSelectedRow(), 0).toString());
				JTextField nameField = new JTextField(model.getValueAt(table.getSelectedRow(), 1).toString());
				JTextField pwField = new JTextField();
				JTextField pwCheckField = new JTextField();
				JTextField phoneField = new JTextField(model.getValueAt(table.getSelectedRow(), 2).toString());
				JTextField emailField = new JTextField(model.getValueAt(table.getSelectedRow(), 3).toString());
				JTextField addressField = new JTextField(model.getValueAt(table.getSelectedRow(), 4).toString());
				JTextField noteField = new JTextField();
				if (model.getValueAt(table.getSelectedRow(), 6) == null) {
					noteField.setText("");
				} else {
					noteField.setText(model.getValueAt(table.getSelectedRow(), 6).toString());
				}

				JButton phonecheckBtn = new JButton("중복확인");
				JButton emailcheckBtn = new JButton("중복확인");
				JButton joinBtn = new JButton("가입하기");
				JButton changeBtn2 = new JButton("수정");
				JButton cancelBtn = new JButton("취소");

				MemberMgmt.setlabel2(join, 40, 40, 13);
				j.add(join);

				MemberMgmt.setlabel2(id, 18, 25, 90);
				MemberMgmt.setField(idField, 113);
				idField.setEditable(false);
				j.add(id);
				j.add(idField);

				MemberMgmt.setlabel2(name, 18, 25, 140);
				MemberMgmt.setField(nameField, 163);
				j.add(name);
				j.add(nameField);

				MemberMgmt.setlabel2(pw, 18, 25, 190);
				MemberMgmt.setField(pwField, 213);
				j.add(pw);
				j.add(pwField);

				MemberMgmt.setlabel2(pwCheck, 18, 25, 240);
				MemberMgmt.setField(pwCheckField, 263);
				pwCheckField.addFocusListener(new FocusAdapter() {
					@Override
					public void focusLost(FocusEvent e) {
						if (!pwCheckField.getText().equals(pwField.getText())) {
							pwCheckField.setForeground(Color.RED);
							pwCheckField.setText("비밀번호가 일치하지 않습니다.");
						}
					}

					@Override
					public void focusGained(FocusEvent e) {
						pwCheckField.setForeground(Color.BLACK);
						pwCheckField.setText("");
					}
				});
				j.add(pwCheck);
				j.add(pwCheckField);

				MemberMgmt.setlabel2(phone, 18, 25, 290);
				MemberMgmt.setField(phoneField, 313);
				MemberMgmt.setBtn(phonecheckBtn, 13, 80, 30);
				phonecheckBtn.setLocation(360, 313);
				phonecheckBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						AdminDao adminDao = new AdminDao();

						if (vd.isValidatePhone(phoneField.getText())) {
							AdminVO adminVo = null;
							try {
								adminVo = adminDao.getByPhone(phoneField.getText()).get(0);
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(null, "사용가능합니다");
								return;
							} catch (IndexOutOfBoundsException e2) {
								JOptionPane.showMessageDialog(null, "사용가능합니다");
								changeBtn2.setEnabled(true);
								return;
							}

							if (adminVo != null) {
								JOptionPane.showMessageDialog(null, "중복되는 전화번호 입니다.", "경고", JOptionPane.ERROR_MESSAGE);
								changeBtn2.setEnabled(false);
								return;
							}
						} else {
							JOptionPane.showMessageDialog(null, "사용 불가능한 전화번호입니다", "경고", JOptionPane.ERROR_MESSAGE);
							changeBtn2.setEnabled(false);
							return;
						}
					}
				});
				j.add(phone);
				j.add(phoneField);
				j.add(phonecheckBtn);

				MemberMgmt.setlabel2(email, 18, 25, 340);
				MemberMgmt.setField(emailField, 363);
				MemberMgmt.setBtn(emailcheckBtn, 13, 80, 30);
				emailcheckBtn.setLocation(360, 363);
				emailcheckBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						
						AdminDao adminDao = new AdminDao();

						if (vd.isValidateEmail(phoneField.getText())) {
							AdminVO adminVo = null;
							try {
								adminVo = adminDao.getByEmail(emailField.getText()).get(0);
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(null, "사용가능합니다");
								return;
							} catch (IndexOutOfBoundsException e2) {
								JOptionPane.showMessageDialog(null, "사용가능합니다");
								changeBtn2.setEnabled(true);
								return;
							}

							if (adminVo != null) {
								JOptionPane.showMessageDialog(null, "중복되는 이메일 입니다.", "경고", JOptionPane.ERROR_MESSAGE);
								changeBtn2.setEnabled(false);
								return;
							}
						} else {
							JOptionPane.showMessageDialog(null, "사용 불가능한 이메일입니다.", "경고", JOptionPane.ERROR_MESSAGE);
							changeBtn2.setEnabled(false);
							return;
						}
					}
				});
				j.add(email);
				j.add(emailField);
				j.add(emailcheckBtn);

				MemberMgmt.setlabel2(address, 18, 25, 390);
				MemberMgmt.setField(addressField, 413);
				j.add(address);
				j.add(addressField);

				MemberMgmt.setlabel2(note, 18, 25, 440);
				MemberMgmt.setField(noteField, 463);
				j.add(note);
				j.add(noteField);

				MemberMgmt.setBtn(changeBtn2, 18, 80, 40);
				changeBtn2.setLocation(360, 500);
				j.add(changeBtn2);

				changeBtn2.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int var = JOptionPane.showConfirmDialog(null, "수정하시겠습니까?", "수정 확인", JOptionPane.YES_NO_OPTION,
								JOptionPane.INFORMATION_MESSAGE, null);
						if (var == JOptionPane.YES_OPTION) {
							if (!pwCheckField.getText().equals(pwField.getText())) {
								JOptionPane.showMessageDialog(j, "비밀번호가 일치하지 않습니다.");
								return;
							}
							AdminDao adao = new AdminDao();
							AdminVO vo = new AdminVO((int) model.getValueAt(table.getSelectedRow(), 0),
									nameField.getText(), pwField.getText(), phoneField.getText(), emailField.getText(),
									addressField.getText(), model.getValueAt(table.getSelectedRow(), 5) + "",
									noteField.getText());
							try {
								adao.update(vo);
								ArrayList<AdminVO> admins = new ArrayList<>();

								admins.addAll(adao.get(keyword.getSelectedIndex() + 1, searchField.getText()));
								int num = 0;
								model.setRowCount(admins.size());
								for (AdminVO admin : admins) {
									for (int i = 0; i < header.length; i++) {
										model.setValueAt(admin.getList()[i], num, i);
									}
									num++;
								}
								j.dispose();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}

					}
				});

				j.setLayout(null);
				j.setBounds(330, 130, 480, 600);
				j.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				j.setVisible(true);
			}
		});
		add(changeBtn);

		// 판넬기본설정
		setLayout(null);
		setBackground(new Color(87, 119, 119));
		// setBorder(new LineBorder(Color.WHITE, 5, false)); // 테두리
	}

	public void modifyEmployee() {
		JFrame j = new JFrame();

		JLabel join = new JLabel("직원 수정");
		JLabel id = new JLabel("사번");
		JLabel name = new JLabel("이름");
		JLabel pw = new JLabel("비밀번호");
		JLabel pwCheck = new JLabel("비밀번호 확인");
		JLabel phone = new JLabel("전화번호");
		JLabel email = new JLabel("이메일");
		JLabel address = new JLabel("주소");
		JLabel note = new JLabel("비고");

		JTextField idField = new JTextField(model.getValueAt(table.getSelectedRow(), 0).toString());
		JTextField nameField = new JTextField(model.getValueAt(table.getSelectedRow(), 1).toString());
		JTextField pwField = new JTextField();
		JTextField pwCheckField = new JTextField();
		JTextField phoneField = new JTextField(model.getValueAt(table.getSelectedRow(), 2).toString());
		JTextField emailField = new JTextField(model.getValueAt(table.getSelectedRow(), 3).toString());
		JTextField addressField = new JTextField(model.getValueAt(table.getSelectedRow(), 4).toString());
		JTextField noteField = new JTextField();
		if (model.getValueAt(table.getSelectedRow(), 6) == null) {
			noteField.setText("");
		} else {
			noteField.setText(model.getValueAt(table.getSelectedRow(), 6).toString());
		}

		JButton phonecheckBtn = new JButton("중복확인");
		JButton emailcheckBtn = new JButton("중복확인");
		JButton joinBtn = new JButton("가입하기");
		JButton changeBtn2 = new JButton("수정");
		JButton cancelBtn = new JButton("취소");

		MemberMgmt.setlabel2(join, 40, 40, 13);
		j.add(join);

		MemberMgmt.setlabel2(id, 18, 25, 90);
		MemberMgmt.setField(idField, 113);
		idField.setEditable(false);
		j.add(id);
		j.add(idField);

		MemberMgmt.setlabel2(name, 18, 25, 140);
		MemberMgmt.setField(nameField, 163);
		j.add(name);
		j.add(nameField);

		MemberMgmt.setlabel2(pw, 18, 25, 190);
		MemberMgmt.setField(pwField, 213);
		j.add(pw);
		j.add(pwField);

		MemberMgmt.setlabel2(pwCheck, 18, 25, 240);
		MemberMgmt.setField(pwCheckField, 263);
		pwCheckField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!pwCheckField.getText().equals(pwField.getText())) {
					pwCheckField.setForeground(Color.RED);
					pwCheckField.setText("비밀번호가 일치하지 않습니다.");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				pwCheckField.setForeground(Color.BLACK);
				pwCheckField.setText("");
			}
		});
		j.add(pwCheck);
		j.add(pwCheckField);

		MemberMgmt.setlabel2(phone, 18, 25, 290);
		MemberMgmt.setField(phoneField, 313);
		MemberMgmt.setBtn(phonecheckBtn, 13, 80, 30);
		phonecheckBtn.setLocation(360, 313);
		j.add(phone);
		j.add(phoneField);
		j.add(phonecheckBtn);

		MemberMgmt.setlabel2(email, 18, 25, 340);
		MemberMgmt.setField(emailField, 363);
		MemberMgmt.setBtn(emailcheckBtn, 13, 80, 30);
		emailcheckBtn.setLocation(360, 363);
		j.add(email);
		j.add(emailField);
		j.add(emailcheckBtn);

		MemberMgmt.setlabel2(address, 18, 25, 390);
		MemberMgmt.setField(addressField, 413);
		j.add(address);
		j.add(addressField);

		MemberMgmt.setlabel2(note, 18, 25, 440);
		MemberMgmt.setField(noteField, 463);
		j.add(note);
		j.add(noteField);

		MemberMgmt.setBtn(changeBtn2, 18, 80, 40);
		changeBtn2.setLocation(360, 500);
		j.add(changeBtn2);

		j.setLayout(null);
		j.setBounds(330, 130, 480, 600);
		j.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		j.setVisible(true);
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
		JTextField[] tfList = { nameField, birthField, phoneField, pwField, emailField, addressField };

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
				AdminVO vo = new AdminVO(null, nameField.getText(), new String(pwField.getPassword()),
						phoneField.getText(), emailField.getText(), addressField.getText(), null, null);

				try {
					dao.add(vo);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				JOptionPane.showMessageDialog(null, "등록이 완료되었습니다.");

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