package gymCustomerManager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import gymCustomerManager.frame.MyFrame;
import gymCustomerManager.model.vo.MemberVO;

public class MemberInsert extends MemberManagements implements ActionListener {

	private static final long serialVersionUID = 1L;

	MyFrame frame = new MyFrame();

	private JTextField code;
	private JTextField name;
	private JTextField birth;
	private JTextField phone;
	private JTextField address;
	private JTextField regDate;
	private JTextField expDate;

	private JButton bt1 = new JButton("등록");
	private JButton bt2 = new JButton("취소");

	private JRadioButton female;
	private JRadioButton male;
	private ButtonGroup gendergroup = new ButtonGroup();

	public void memberInsert() {

		frame.setTitle("회원등록");
		frame.setSize(550, 450);
		frame.setLocation(500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		// 회원 가입 라벨
		JLabel mCode = new JLabel("회원 코드 :");
		JLabel mName = new JLabel("회원 이름 :");
		JLabel mBirth = new JLabel("회원 생년월일 :");
		JLabel mGender = new JLabel("회원 성별 :");
		JLabel mPhone = new JLabel("회원 전화번호 :");
		JLabel mAddress = new JLabel("회원 주소 :");
		JLabel mRegDate = new JLabel("등록 날짜 :");
		JLabel mExpDate = new JLabel("만기 날짜 :");
		JLabel phoneLabel = new JLabel("('-'없이 입력해주세요)");
		JLabel birthLabel = new JLabel("(ex : 19900101)");
		JLabel regDateLabel = new JLabel("(ex : 20200101)");
		JLabel expDateLabel = new JLabel("(ex : 20200101)");

		// 라벨 위치 지정
		mCode.setBounds(40, 10, 200, 40);
		mName.setBounds(40, 50, 200, 23);
		mBirth.setBounds(40, 90, 200, 23);
		birthLabel.setBounds(370, 90, 200, 23);
		mGender.setBounds(40, 130, 200, 23);
		mPhone.setBounds(40, 170, 200, 23);
		phoneLabel.setBounds(370, 170, 200, 23);
		mAddress.setBounds(40, 210, 200, 23);
		mRegDate.setBounds(40, 250, 200, 23);
		regDateLabel.setBounds(370, 250, 200, 23);
		mExpDate.setBounds(40, 290, 200, 23);
		expDateLabel.setBounds(370, 290, 200, 23);

		// TextField 초기화
		code = new JTextField();
		name = new JTextField();
		birth = new JTextField();
		phone = new JTextField();
		address = new JTextField();
		regDate = new JTextField();
		expDate = new JTextField();

		// RadioButton 초기화
		male = new JRadioButton("남");
		female = new JRadioButton("여");

		// 입력 글 갯수 제한
		code.setColumns(10);
		name.setColumns(10);
		birth.setColumns(10);
		phone.setColumns(10);
		address.setColumns(30);
		regDate.setColumns(10);
		expDate.setColumns(10);

		// TextField 및 라디오 버튼 위치 지정
		code.setBounds(160, 16, 200, 23);
		name.setBounds(160, 50, 200, 23);
		birth.setBounds(160, 90, 200, 23);
		male.setBounds(160, 130, 40, 23);
		female.setBounds(200, 130, 40, 23);
		phone.setBounds(160, 170, 200, 23);
		address.setBounds(160, 210, 200, 23);
		regDate.setBounds(160, 250, 200, 23);
		expDate.setBounds(160, 290, 200, 23);

		// MemberCode 제한
		if (list.size() != 0) {
			code.setText((Integer.parseInt(list.get(list.size() - 1).getCode()) + 1) + "");
		} else {
			code.setText(1001 + "");
		}

		code.setEditable(false);

		// frame에 Label 넣기
		frame.getContentPane().add(mCode);
		frame.getContentPane().add(mName);
		frame.getContentPane().add(mBirth);
		frame.getContentPane().add(mPhone);
		frame.getContentPane().add(mAddress);
		frame.getContentPane().add(mRegDate);
		frame.getContentPane().add(mExpDate);
		frame.getContentPane().add(mGender);
		frame.getContentPane().add(phoneLabel);
		frame.getContentPane().add(birthLabel);
		frame.getContentPane().add(regDateLabel);
		frame.getContentPane().add(expDateLabel);

		gendergroup.add(male);
		gendergroup.add(female);
		
		// frame에 TextField 넣기
		frame.getContentPane().add(code);
		frame.getContentPane().add(name);
		frame.getContentPane().add(birth);
		frame.getContentPane().add(male);
		frame.getContentPane().add(female);
		frame.getContentPane().add(phone);
		frame.getContentPane().add(address);
		frame.getContentPane().add(regDate);
		frame.getContentPane().add(expDate);	


		bt1.setBounds(150, 350, 80, 30);
		bt2.setBounds(300, 350, 80, 30);

		frame.getContentPane().add(bt1);
		frame.getContentPane().add(bt2);

		frame.setVisible(true);

		bt1.addActionListener(this);
		bt2.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {
		String memGender = null;

		if (e.getSource() == bt1) {
			String memCode = code.getText();
			String memName = name.getText();
			String memBirth = birth.getText();
			if (male.isSelected()) {
				memGender = "남자";
			} else if (female.isSelected()) {
				memGender = "여자";
			}
			String memPhone = phone.getText();
			String memAddress = address.getText();
			String memRegDate = regDate.getText();
			String memExpDate = expDate.getText();
			
			// 회원 가입 유효성 검증
			if (memCode.equals("")) {
				JOptionPane.showMessageDialog(this, "회원 코드을 입력해 주세요", "메시지", JOptionPane.INFORMATION_MESSAGE);
			} else if (memName.equals("")) {
				JOptionPane.showMessageDialog(this, "회원 이름을 입력해 주세요", "메시지", JOptionPane.INFORMATION_MESSAGE);
			} else if (memBirth.equals("")) {
				JOptionPane.showMessageDialog(this, "회원 생년 월일을 입력해 주세요", "메시지", JOptionPane.INFORMATION_MESSAGE);
			} else if (memGender.equals("")) {
				JOptionPane.showMessageDialog(this, "회원 성별를 입력해 주세요", "메시지", JOptionPane.INFORMATION_MESSAGE);
			} else if (memPhone.equals("")) {
				JOptionPane.showMessageDialog(this, "회원 전화번호를 입력해 주세요", "메시지", JOptionPane.INFORMATION_MESSAGE);
			} else if (memAddress.equals("")) {
				JOptionPane.showMessageDialog(this, "회원 주소를 입력해 주세요", "메시지", JOptionPane.INFORMATION_MESSAGE);
			} else if (memRegDate.equals("")) {
				JOptionPane.showMessageDialog(this, "등록 날짜를 입력해 주세요", "메시지", JOptionPane.INFORMATION_MESSAGE);
			} else if (memExpDate.equals("")) {
				JOptionPane.showMessageDialog(this, "만기 날짜를 입력해 주세요", "메시지", JOptionPane.INFORMATION_MESSAGE);
			} else {
				
				// 회원 등록 타입 유효성 검증
				if (!integerOrNot(memCode)) {
					JOptionPane.showMessageDialog(this, "회원 코드는 문자를 입력할 수 없습니다.", "메시지",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (!integerOrNot(memBirth)) {
					JOptionPane.showMessageDialog(this, "회원 생년월일은 문자를 입력할 수 없습니다.", "메시지",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (!integerOrNot(memPhone)) {
					JOptionPane.showMessageDialog(this, "전화번호는 문자를 입력할 수 없습니다.", "메시지",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (!(memPhone.substring(0, 2).equals("01")
						&& (memPhone.length() == 10 || memPhone.length() == 11))) {
					JOptionPane.showMessageDialog(this, "잘못된 전화번호를 입력하였습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
				} else if (!integerOrNot(memRegDate)) {
					JOptionPane.showMessageDialog(this, "등록 날짜는 문자를 입력할 수 없습니다.", "메시지",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (!integerOrNot(memExpDate)) {
					JOptionPane.showMessageDialog(this, "만기 날짜는 문자를 입력할 수 없습니다.", "메시지",
							JOptionPane.INFORMATION_MESSAGE);

					// 회원 등록 글자 수 유효성 검증
				} else if (memName.length() > 10) {
					JOptionPane.showMessageDialog(this, "이름은 10자를 넘을 수 없습니다", "메시지", JOptionPane.INFORMATION_MESSAGE);
				} else if (memBirth.length() != 8) {
					JOptionPane.showMessageDialog(this, "생년월일은 8자(ex : 19970101) ", "메시지",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (memAddress.length() > 30) {
					JOptionPane.showMessageDialog(this, "주소는 30자를 넘을 수 없습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
				} else if (memRegDate.length() != 8) {
					JOptionPane.showMessageDialog(this, "등록날짜는 8자(ex : 20200101)", "메시지",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (memExpDate.length() != 8) {
					JOptionPane.showMessageDialog(this, "만기날짜는 8자(ex : 20200101)", "메시지",
							JOptionPane.INFORMATION_MESSAGE);
					
					// 회원 등록 확인 메세지
				} else {
					int check = JOptionPane.showConfirmDialog(this,
							"입력한 내용이 맞습니까?\n" + "회원 코드 : " + memCode + "\n회원 이름 : " + memName + "\n회원 생년월일 : "
									+ memBirth + "\n회원 성별 : " + memGender + "\n회원 전화번호 : " + memPhone + "\n회원 주소 : "
									+ memAddress + "\n등록 날짜 : " + memRegDate + "\n만기 날짜 : " + memExpDate,
							"메시지", JOptionPane.INFORMATION_MESSAGE);
					if (check == 0) {
						MemberVO m = new MemberVO();
						m.setCode(memCode);
						m.setName(memName);
						m.setBirth(memBirth);
						m.setGender(memGender);
						m.setPhone(memPhone);
						m.setAddress(memAddress);
						m.setRegDate(memRegDate);
						m.setExpDate(memExpDate);
						list.add(m);
						JOptionPane.showMessageDialog(this, "회원이 등록되었습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
						fileSave();
						int check2 = JOptionPane.showConfirmDialog(this, "계속 입력하시겠습니까?");
						if (check2 == 0) {
							code.setText(Integer.parseInt(list.get(list.size() - 1).getCode()) + 1 + "");
							name.setText(null);
							birth.setText(null);
							gendergroup.clearSelection();
							phone.setText(null);
							address.setText(null);
							regDate.setText(null);
							expDate.setText(null);

						} else if (check2 == 1) {
							frame.setVisible(false);
							super.MmStart();
						}
					}
				}
			}
		} else if (e.getSource() == bt2) {
			frame.dispose();
			super.MmStart();
		}
	}

	public boolean integerOrNot(String strData) { // 입력값이 숫자인지 문자인지 판별 :
		char[] charData = strData.toCharArray();
		boolean check = true;
		while (check) {
			for (int i = 0; i < charData.length; i++) {
				if (!Character.isDigit(charData[i])) {
					check = !check;
					break;
				}
			}
			break;
		}
		return check;
	}
}
