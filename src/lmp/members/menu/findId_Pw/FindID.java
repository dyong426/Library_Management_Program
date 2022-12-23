package lmp.members.menu.findId_Pw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import lmp.db.dao.MemberDao;
import lmp.db.vo.MemberVO;

public class FindID extends JFrame {

   private JFrame frame;

   private Font font = new Font("한컴 말랑말랑 Regular", Font.BOLD, 15);
   private JTextField textField;
   private JTextField textField_1;
   private JTextField textField_2;
   private JTextField textField_3;
   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
            	FindID window = new FindID();
               window.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the application.
    */
   public FindID() {
      initialize();
      setVisible(true);
   }

   /**
    * Initialize the contents of the frame.
    */
   private void initialize() {
      setBounds(-58, -71, 400, 460);
      setResizable(false);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setLocationRelativeTo(null);
      setAlwaysOnTop(true);
      
      
      JPanel panel = new JPanel();
      getContentPane().add(panel, BorderLayout.CENTER);
      panel.setLayout(null);
      panel.setBackground(new Color(186, 206, 194));
      
      JButton btnNewButton = new JButton("다음");
      btnNewButton.setFont(font);
      btnNewButton.setBounds(260, 350, 100, 50);
      btnNewButton.setBackground(new Color(144, 180, 148));
      btnNewButton.setFocusPainted(false);
      panel.add(btnNewButton);
      
      JRadioButton rdbtnNewRadioButton = new JRadioButton("등록된 휴대폰으로 찾기");
      rdbtnNewRadioButton.setFont(font);
      rdbtnNewRadioButton.setBounds(40, 60, 200, 30);
      rdbtnNewRadioButton.setOpaque(false);
      rdbtnNewRadioButton.setSelected(true);
      panel.add(rdbtnNewRadioButton);
      
      rdbtnNewRadioButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			textField.requestFocus();
		}
      });
      
      JPanel panel_1 = new JPanel();
      panel_1.setBorder(new LineBorder(Color.BLACK));
      panel_1.setOpaque(false);
      panel_1.setBounds(30, 90, 330, 100);
      panel.add(panel_1);
      panel_1.setLayout(null);
      
      textField = new JTextField();
      textField.setBounds(100, 20, 210, 30);
      panel_1.add(textField);
      textField.setColumns(10);
      
      textField_1 = new JTextField();
      textField_1.setColumns(10);
      textField_1.setBounds(100, 55, 210, 30);
      textField_1.setText("\"-\"를 포함해 입력해주세요.");
      textField_1.addFocusListener(new FocusAdapter() {
    	@Override
    	public void focusGained(FocusEvent e) {
    		textField_1.setText("");
    	}
      });
      panel_1.add(textField_1);
      
      JLabel lblNewLabel = new JLabel("이름");
      lblNewLabel.setFont(font);
      lblNewLabel.setBounds(20, 20, 50, 30);
      panel_1.add(lblNewLabel);
      
      JLabel lblNewLabel_1 = new JLabel("휴대폰 번호");
      lblNewLabel_1.setFont(font);
      lblNewLabel_1.setBounds(20, 55, 210, 30);
      panel_1.add(lblNewLabel_1);
      
      JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("등록된 이메일로 찾기");
      rdbtnNewRadioButton_1.setFont(font);
      rdbtnNewRadioButton_1.setBounds(40, 200, 200, 30);
      rdbtnNewRadioButton_1.setOpaque(false);
      panel.add(rdbtnNewRadioButton_1);
      
      rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			textField_2.requestFocus();
		}
      });
      
      
      ButtonGroup btnGroup = new ButtonGroup();
      btnGroup.add(rdbtnNewRadioButton);
      btnGroup.add(rdbtnNewRadioButton_1);
      
      JPanel panel_1_1 = new JPanel();
      panel_1_1.setOpaque(false);
      panel_1_1.setBorder(new LineBorder(Color.BLACK));
      panel_1_1.setBounds(30, 230, 330, 100);
      panel.add(panel_1_1);
      panel_1_1.setLayout(null);
      
      textField_2 = new JTextField();
      textField_2.setFont(font);
      textField_2.setColumns(10);
      textField_2.setBounds(100, 20, 210, 30);
      panel_1_1.add(textField_2);
      
      textField_3 = new JTextField();
      textField_3.setFont(font);
      textField_3.setColumns(10);
      textField_3.setBounds(100, 55, 210, 30);
      panel_1_1.add(textField_3);
      
      JLabel lblNewLabel_2 = new JLabel("이름");
      lblNewLabel_2.setFont(font);
      lblNewLabel_2.setBounds(20, 20, 50, 30);
      panel_1_1.add(lblNewLabel_2);
      
      JLabel lblNewLabel_3 = new JLabel("이메일");
      lblNewLabel_3.setFont(font);
      lblNewLabel_3.setBounds(20, 55, 210, 30);
      panel_1_1.add(lblNewLabel_3);
      
      JLabel lblNewLabel_4 = new JLabel("아이디 찾기");
      lblNewLabel_4.setFont(new Font("한컴 말랑말랑 Regular", Font.BOLD, 25));
      lblNewLabel_4.setBounds(130, 0, 200, 50);
      panel.add(lblNewLabel_4);
      
      
      btnNewButton.addActionListener(new ActionListener() {
  		@Override
  		public void actionPerformed(ActionEvent e) {
  			if (rdbtnNewRadioButton.isSelected()) {
  				System.out.println("버튼 선택 확인");
  				MemberDao memDao = new MemberDao();
  				ArrayList<MemberVO> memVo = new ArrayList<>();
  				
  				try {
					memVo.addAll(memDao.get(2, textField.getText()));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
  				if (memVo.size() != 0) {
  					System.out.println("회원 이름 존재");
  					for (MemberVO mem : memVo) {
  						System.out.println("for문 도는중");
  						if (mem.getPhone().equals(textField_1.getText())) {
  							JOptionPane.showMessageDialog(null, "아이디 : " + mem.getId());
  							return;
  						}
  					}
  					System.out.println("for문 끝");
  				} else {
  					System.out.println("배열 길이 1");
  					JOptionPane.showMessageDialog(null, "일치하는 정보가 없습니다.");
  				}
  				System.out.println("배열 길이 0");
  			}
  		}
      });
      
   }
   
}
