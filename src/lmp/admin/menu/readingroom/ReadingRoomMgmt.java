package lmp.admin.menu.readingroom;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import lmp.db.dao.SeatUseDetailDao;
import lmp.db.vo.SeatUseDetailVO;

public class ReadingRoomMgmt extends JPanel implements ActionListener{

	String colNames[] = {"좌석번호","회원번호","회원명","연락처","성별", "시작시간"};
	DefaultTableModel model = new DefaultTableModel(colNames, 18); // 데이터 저장 부분
	JTable table = new JTable(model);
	JScrollPane scrollPane = new JScrollPane(table);
	DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 디폴트테이블셀렌더러를 생성

	int totalSeatCnt = 18;
	int usingCnt;
	int remainCnt;
	JTextField totalCntTf = new JTextField(totalSeatCnt + "석");
	JTextField usingCntTf = new JTextField();
	JTextField remainCntTf = new JTextField(); 

	JPanel panel = this;
	JLabel[] labels = new JLabel[totalSeatCnt];
	JLabel exitLb = new JLabel("출구");
	Font font = new Font("한컴 말랑말랑 Regular", Font.PLAIN, 15);

	public ReadingRoomMgmt() { // 컴포넌트 새성하여 부착
		
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(25);
		
		add(scrollPane, "Center");

		for (int i = 0; i < totalSeatCnt; i++) {
			labels[i] = new JLabel("" + (i + 1));
			labels[i].setOpaque(true);
			labels[i].setFont(font);
			labels[i].setForeground(Color.GRAY);
			labels[i].setHorizontalAlignment(JTextField.CENTER);
			labels[i].setBorder(javax.swing.BorderFactory.createEmptyBorder());
		}

		labels[0].setBounds(310, 315, 70, 70);
		labels[1].setBounds(310, 387, 70, 70);
		labels[2].setBounds(310, 459, 70, 70);
		labels[3].setBounds(382, 315, 70, 70);
		labels[4].setBounds(382, 387, 70, 70);
		labels[5].setBounds(382, 459, 70, 70);
		labels[6].setBounds(505, 315, 70, 70);
		labels[7].setBounds(505, 387, 70, 70);
		labels[8].setBounds(505, 459, 70, 70);
		labels[9].setBounds(577, 315, 70, 70);
		labels[10].setBounds(577, 387, 70, 70);
		labels[11].setBounds(577, 459, 70, 70);
		labels[12].setBounds(698, 315, 70, 70);
		labels[13].setBounds(698, 387, 70, 70);
		labels[14].setBounds(698, 459, 70, 70);
		labels[15].setBounds(770, 315, 70, 70);
		labels[16].setBounds(770, 387, 70, 70);
		labels[17].setBounds(770, 459, 70, 70);


		exitLb.setBounds(980, 315, 70, 220);
		exitLb.setOpaque(true);
		exitLb.setFont(font);
		exitLb.setHorizontalAlignment(JTextField.CENTER);
		exitLb.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		exitLb.setHorizontalAlignment(JTextField.CENTER);
		exitLb.setForeground(Color.WHITE);



		// ★★★★★★★★★★★ 데이터베이스 불러오기 (기본틀!!) ★★★★★★★★★★
		SeatUseDetailDao sudDao = new SeatUseDetailDao();
		ArrayList<SeatUseDetailVO> sudList =  new ArrayList<>();

		try {
			sudList.addAll(sudDao.get());

			int row = 0;

			for (int i = 0; i < totalSeatCnt; i++) {
				labels[i].setBackground(Color.WHITE);
			}
			for (SeatUseDetailVO sud : sudList) {

				model.setRowCount(sudList.size());
				for (int i = 0; i < sud.getSudList().length; i++) {
					model.setValueAt(sud.getSudList()[i], row, i);
				}
				row++; 

				usingCnt = model.getRowCount();
				remainCnt = totalSeatCnt - usingCnt;
				usingCntTf.setText(usingCnt + "석");
				remainCntTf.setText(remainCnt + "석");

				if (sud.getMember().getSex().equals("0")) {
					labels[sud.getReadingroom().getSeatNum() - 1].setBackground(new Color(138, 228, 255)); // 남자
				} else if (sud.getMember().getSex().equals("1")) {
					labels[sud.getReadingroom().getSeatNum() - 1].setBackground(new Color(255, 183, 210)); // 여자
				}
				table.validate(); // 새로고침 - 버튼 액션으로
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★

		table.getTableHeader().setReorderingAllowed(false); // 테이블 컬럼 이동 불가
		table.getTableHeader().setResizingAllowed(false); // 테이블 크기 조절 불가
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 한개의 행만 선택 가능
		table.getTableHeader().setResizingAllowed(false); // JTable 컬럼의 사이즈 변경 불가

		// 컬럼내 데이터 가운데정렬
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 디폴트테이블셀렌더러를 생성
		dtcr.setHorizontalAlignment(SwingConstants.CENTER); // 렌더러의 가로정렬을 CENTER로
		TableColumnModel tcm = table.getColumnModel(); // 정렬할 테이블의 컬럼모델을 가져옴

		//전체 열을 가운데 정렬
		for(int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}	

		// 패널1
		JPanel p1 = new JPanel(); 
		p1.setLayout(new BoxLayout(p1, BoxLayout.X_AXIS));
		p1.setBackground(new Color(87, 119, 119));
		//		p1.setBounds(18, 110, 1150, 70);
		p1.setBounds(0, 0, 1150, 50);

		// 패널2
		JPanel p2 = new JPanel();
		p2.setLayout(new BoxLayout(p2, BoxLayout.X_AXIS));
		p2.setBackground(new Color(87, 119, 119));
		//		p2.setBounds(18, 180, 1150, 290);
		p2.setBounds(0, 50, 1150, 250);
		p2.add(scrollPane);

		// 패널3
		JPanel p3 = new JPanel();
		p3.setLayout(new BoxLayout(p3, BoxLayout.X_AXIS));
		p3.setBackground(new Color(87, 119, 119));
		//		p3.setBounds(18, 470, 1150, 275);
		p3.setBounds(0, 250, 1150, 300);


		// 메인 배경색상 변경
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		mainPanel.setBackground(new Color(87, 119, 119));
		//		mainPanel.setBounds(0, 0, 1200, 800);
		mainPanel.setBounds(0, 0,  1150, 550);


		JButton checkOutBtn = new JButton("강제퇴실");
		checkOutBtn.setBounds(1030, 10, 100, 30);
		checkOutBtn.setFont(new Font("한컴 말랑말랑 Regular", Font.BOLD, 15));
		checkOutBtn.setForeground(Color.WHITE);
		checkOutBtn.setBackground(Color.LIGHT_GRAY);
		checkOutBtn.setBorderPainted(false);
		checkOutBtn.setFocusPainted(false); 
		checkOutBtn.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {

				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(panel, "퇴실할 좌석을 선택해 주세요");
					return;
				}

				int result = JOptionPane.showConfirmDialog(scrollPane, "해당 사용자를 퇴실처리 하시겠습니까?", "강제퇴실 확인", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.NO_OPTION) {

				} else if (result == JOptionPane.YES_OPTION) {

					e.getSource();


					int selectRow = table.getSelectedRow();// 선택된 테이블의 행값
					Object seat_num = table.getValueAt(selectRow, 0); 
					System.out.println(seat_num);

					try {
						sudDao.update((int)seat_num);
					} catch (SQLException e2) {
						e2.printStackTrace();
					}



					// --------- 데이터베이스 수정하기 --------- 
					SeatUseDetailDao sudDao = new SeatUseDetailDao();
					ArrayList<SeatUseDetailVO> sudList =  new ArrayList<>();

					try {
						sudList.addAll(sudDao.get()); // DB 내용 가져오기
						model = new DefaultTableModel(colNames,0);

						for (int i = 0; i < totalSeatCnt; i++) {
							labels[i].setBackground(Color.WHITE);
						}
						int resetRow = 0;
						for (SeatUseDetailVO sud : sudList) {

							model.setRowCount(sudList.size());
							for (int i = 0; i < sud.getSudList().length; i++) {
								model.setValueAt(sud.getSudList()[i], resetRow, i);
							}
							resetRow++; 

							if (sud.getMember().getSex().equals("0")) {
								labels[sud.getReadingroom().getSeatNum() - 1].setBackground(new Color(138, 228, 255));
							} else if (sud.getMember().getSex().equals("1")) {
								labels[sud.getReadingroom().getSeatNum() - 1].setBackground(new Color(255, 183, 210));
							}
						}
						table.setModel(model);

						//전체 열을 가운데 정렬
						for(int i = 0; i < tcm.getColumnCount(); i++) {
							tcm.getColumn(i).setCellRenderer(dtcr);
						}

						table.validate(); // 새로고침 - 버튼 액션으로
						usingCnt = model.getRowCount();
						remainCnt = totalSeatCnt - usingCnt;

						usingCntTf.setText(usingCnt + "석");
						remainCntTf.setText(remainCnt + "석");


						p3.validate();


					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					// ------------------------------------------------

				}  
			}
		});



//		JLabel checkOutLb = new JLabel("강제퇴실");
//		checkOutLb.setBounds(1045, 0, 300, 50);
//		checkOutLb.setFont(new Font("한컴 말랑말랑 Regular", Font.BOLD, 14));
//		checkOutLb.setForeground(Color.WHITE);

		// 라벨추가
		JLabel titleLabel = new JLabel("좌석 이용 현황");
		JLabel totalLabel = new JLabel("총 좌석");
		JLabel usingLabel = new JLabel("이용 중"); 
		JLabel remainLabel = new JLabel("이용 가능");


		// 사용여부 컬럼에 '이용중' 입력
//		for (int i = 0; i < table.getRowCount(); i++) {
//			table.setValueAt("이용 중", i, 6); // i : row, 6 : 컬럼 번호
//		}

		totalCntTf.setBorder(null); // TextField 테두리 없애기
		usingCntTf.setBorder(null); // TextField 테두리 없애기
		remainCntTf.setBorder(null); // TextField 테두리 없애기
		totalCntTf.setBounds(20, 310, 100, 30);
		usingCntTf.setBounds(20, 370, 100, 30);
		remainCntTf.setBounds(20, 430, 100, 30);
		totalCntTf.setFont(new Font("한컴 말랑말랑 Regular", Font.PLAIN, 16));
		usingCntTf.setFont(new Font("한컴 말랑말랑 Regular", Font.PLAIN, 16));
		remainCntTf.setFont(new Font("한컴 말랑말랑 Regular", Font.PLAIN, 16));
		totalCntTf.setHorizontalAlignment(JTextField.CENTER); // 텍스트필드 내 글자 가운데 정렬
		usingCntTf.setHorizontalAlignment(JTextField.CENTER);
		remainCntTf.setHorizontalAlignment(JTextField.CENTER);
		totalCntTf.setForeground(Color.GRAY);
		usingCntTf.setForeground(Color.GRAY);
		remainCntTf.setForeground(Color.GRAY);

		totalCntTf.validate();
		usingCntTf.validate();
		remainCntTf.validate();

		// 텍스트필드 테두리 없애기
		totalCntTf.setBorder(null);
		usingCntTf.setBorder(null);
		remainCntTf.setBorder(null); 

		// 텍스트필드 위치와 크기
		totalCntTf.setBounds(100, 340, 100, 30);
		usingCntTf.setBounds(100, 400, 100, 30);
		remainCntTf.setBounds(100, 460, 100, 30);

		// 텍스트필드 폰트체
		totalCntTf.setFont(new Font("한컴 말랑말랑 Regular", Font.PLAIN, 16));
		usingCntTf.setFont(new Font("한컴 말랑말랑 Regular", Font.PLAIN, 16));
		remainCntTf.setFont(new Font("한컴 말랑말랑 Regular", Font.PLAIN, 16));

		// 텍스트필드 폰트색
		totalCntTf.setForeground(Color.GRAY);
		usingCntTf.setForeground(Color.GRAY);
		remainCntTf.setForeground(Color.GRAY);

		// 텍스트필드 내 글자 가운데 정렬
		totalCntTf.setHorizontalAlignment(JTextField.CENTER); 
		usingCntTf.setHorizontalAlignment(JTextField.CENTER);
		remainCntTf.setHorizontalAlignment(JTextField.CENTER);

		// 텍스트 필드 수정 금지
		totalCntTf.setEditable(false);
		usingCntTf.setEditable(false);
		remainCntTf.setEditable(false);

		// 텍스트 필드 배경색
		totalCntTf.setBackground(Color.WHITE);
		usingCntTf.setBackground(Color.WHITE);
		remainCntTf.setBackground(Color.WHITE);
		exitLb.setBackground(new Color(195, 166, 160));

		// 라벨의 위치와 크기
		titleLabel.setBounds(420, 0, 300, 50);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		totalLabel.setBounds(125, 310, 100, 30);
		usingLabel.setBounds(125, 370, 100, 30);
		remainLabel.setBounds(120, 430, 100, 30);

		// 라벨 폰트
		titleLabel.setFont(new Font("한컴 말랑말랑 Regular", Font.BOLD, 30));
		totalLabel.setFont(new Font("한컴 말랑말랑 Regular", Font.BOLD, 16));
		usingLabel.setFont(new Font("한컴 말랑말랑 Regular", Font.BOLD, 16));
		remainLabel.setFont(new Font("한컴 말랑말랑 Regular", Font.BOLD, 16));

		// 라벨 색상
		titleLabel.setForeground(Color.WHITE);
		totalLabel.setForeground(Color.WHITE);
		usingLabel.setForeground(Color.WHITE);
		remainLabel.setForeground(Color.WHITE);


		// 프레임에 라벨과 텍스트필드 추가
		add(titleLabel);
		add(totalLabel);
		add(totalCntTf);
		add(usingLabel);
		add(usingCntTf);
		add(remainLabel);
		add(remainCntTf);
		add(exitLb);
		add(checkOutBtn);

		for (int i = 0; i < labels.length; i++) {			
			add(labels[i]);
		}

		// 프레임에 패널을 추가
		p2.add(scrollPane);
		add(p1);
		add(p2);
		add(p3);
		add(mainPanel);

		// 기본세팅
		setBounds(0, 0, 1200, 800);
		setLayout(null);
		setVisible(true);
		//		setResizable(false);// 창 크기 고정
		//		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}



	public void open() {
		new ReadingRoomMgmt();
	}

	public static void main(String[] args) {
		new ReadingRoomMgmt();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}