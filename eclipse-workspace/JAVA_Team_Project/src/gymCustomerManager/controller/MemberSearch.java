package gymCustomerManager.controller;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import gymCustomerManager.frame.MyFrame;

public class MemberSearch extends MemberManagements implements ActionListener {

	private static final long serialVersionUID = 1L;

	MyFrame frame = new MyFrame();
	private JButton sButton = new JButton("검색");
	private JButton uButton = new JButton("수정");
	private JButton dButton = new JButton("삭제");
	private JButton sAll = new JButton("전체 회원");
	private JButton sCancel = new JButton("취소");

	private int index = -1;

	public void memberSearch() {
		
		memberSearchCheck = 1;
		
		searchTextArea.setText("");
		searchTextArea.setEditable(false);
		
		frame.setTitle("회원 조회");
		frame.setSize(500, 400);
		frame.setLocation(550, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		JLabel lblNewLabel = new JLabel("회원 조회");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		lblNewLabel.setBounds(354, 3, 80, 32);
		frame.getContentPane().add(lblNewLabel);

		sButton.setBounds(354, 40, 95, 32);
		frame.getContentPane().add(sButton);

		uButton.setBounds(354, 82, 95, 32);
		frame.getContentPane().add(uButton);

		dButton.setBounds(354, 124, 95, 32);
		frame.getContentPane().add(dButton);

		sAll.setBounds(354, 166, 95, 32);
		frame.getContentPane().add(sAll);

		sCancel.setBounds(354, 208, 95, 32);
		frame.getContentPane().add(sCancel);

		searchTextArea.setBounds(12, 9, 331, 300);
		frame.getContentPane().add(searchTextArea);
		
		frame.setVisible(true);

		sButton.addActionListener(this);
		uButton.addActionListener(this);
		dButton.addActionListener(this);
		sAll.addActionListener(this);
		sCancel.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {

		int dataCnt = 1;
		int dataCnt2 = 1;
		int dataCnt3 = 1;

		String[] nameCollection = null;
		String nameCode = null;

		String[] phoneCollection = null;
		String phoneCode = null;

		if (e.getSource() == sButton) {
			String searchData = JOptionPane.showInputDialog("검색할 회원 코드나 이름 또는 전화번호를 입력하세요");
			int cnt = 0;
			int cnt2 = 0;

			if (searchData == null) {
				return;
			}
			if (list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
					if (searchData.equals(list.get(i).getCode())) {
						if (list.get(i).getPhone().length() == 11) {
							searchTextArea.setText(setText1(i));
						} else if (list.get(i).getPhone().length() == 10) {
							searchTextArea.setText(setText2(i));
						}
						index = i;
						break;
					}
					dataCnt++;
				}

				for (int i = 0; i < list.size(); i++) {
					if (searchData.equals(list.get(i).getName())) {
						cnt++;
					}
				}
				if (cnt > 1) {
					nameCollection = new String[cnt];
					int nameCnt = 0;
					for (int i = 0; i < list.size(); i++) {
						if (searchData.equals(list.get(i).getName())) {
							nameCollection[nameCnt] = list.get(i).getCode() + ". " + list.get(i).getName() + " "
									+ list.get(i).getPhone();
							nameCnt++;
						}
					}
					nameCode = (String) JOptionPane.showInputDialog(this, "같은 이름이 존재합니다.\n", "메시지",
							JOptionPane.INFORMATION_MESSAGE, null, nameCollection, nameCollection[0]);
					if (nameCode == null) {
						return;
					}
					nameCode = nameCode.substring(0, 4);
					for (int i = 0; i < list.size(); i++) {
						if ((nameCode.equals(list.get(i).getCode()))) {
							if (list.get(i).getPhone().length() == 11) {
								searchTextArea.setText(setText1(i));
							} else if (list.get(i).getPhone().length() == 10) {
								searchTextArea.setText(setText2(i));
							}
							index = i;
						}
					}
				} else {
					for (int i = 0; i < list.size(); i++) {
						if (searchData.equals(list.get(i).getName())) {
							if (list.get(i).getPhone().length() == 11) {
								searchTextArea.setText(setText1(i));
							} else if (list.get(i).getPhone().length() == 10) {
								searchTextArea.setText(setText2(i));
							}
							index = i;
							break;
						}
						dataCnt2++;
					}
				}

				for (int i = 0; i < list.size(); i++) {
					if (searchData.equals(list.get(i).getPhone())) {
						cnt2++;
					}
				}
				if (cnt2 > 1) {
					phoneCollection = new String[cnt2];
					int phoneCnt = 0;
					for (int i = 0; i < list.size(); i++) {
						if (searchData.equals(list.get(i).getPhone())) {
							phoneCollection[phoneCnt] = list.get(i).getCode() + ". " + list.get(i).getName() + " "
									+ list.get(i).getPhone();
							phoneCnt++;
						}
					}
					phoneCode = (String) JOptionPane.showInputDialog(this, "같은 전화번호가 존재합니다.\n", "메시지",
							JOptionPane.INFORMATION_MESSAGE, null, phoneCollection, phoneCollection[0]);
					if (phoneCode == null) {
						return;
					}
					phoneCode = phoneCode.substring(0, 4);
					for (int i = 0; i < list.size(); i++) {
						if ((phoneCode.equals(list.get(i).getCode()))) {
							if (list.get(i).getPhone().length() == 11) {
								searchTextArea.setText(setText1(i));
							} else if (list.get(i).getPhone().length() == 10) {
								searchTextArea.setText(setText2(i));
							}
							index = i;
						}
					}
				} else {
					for (int i = 0; i < list.size(); i++) {
						if (searchData.equals(list.get(i).getPhone())) {
							if (list.get(i).getPhone().length() == 11) {
								searchTextArea.setText(setText1(i));
							} else if (list.get(i).getPhone().length() == 10) {
								searchTextArea.setText(setText2(i));
							}
							index = i;
							break;
						}
						dataCnt3++;
					}
					if (dataCnt == list.size() + 1 && dataCnt2 == list.size() + 1 && dataCnt3 == list.size() + 1) {
						JOptionPane.showMessageDialog(this, "일치하는 회원이 없습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
						index = -1;
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "등록된 회원이 없습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
				index = -1;
			}
		} else if (e.getSource() == uButton) {
			if (index == -1) {
				JOptionPane.showMessageDialog(this, "선택된 회원이 없습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
			} else {
				memberSearchCheck = 1;
				MemberUpdateRegister mur = new MemberUpdateRegister();
				mur.memberUpdateRegister(index);
			}

		} else if (e.getSource() == dButton){
			
			if (index == -1) {
				JOptionPane.showMessageDialog(this, "선택된 회원이 없습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
			} else {
				int check3 = JOptionPane.showConfirmDialog(this, "회원을 삭제하시겠습니까?\n", "메시지", JOptionPane.INFORMATION_MESSAGE);
				if (check3 == 0) {
					list.remove(index);
					fileSave();
					JOptionPane.showMessageDialog(this, "회원정보가 삭제되었습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
					index = -1;
					searchTextArea.setText("");
				} else if (check3 == 1) {
					
				}
			}
		} else if (e.getSource() == sAll) {
			MemberAllSearch al = new MemberAllSearch();
			al.memberAllSearch();

		} else if (e.getSource() == sCancel) {
			frame.dispose();
			super.MmStart();
		}
	}

	public String setText1(int i) {
		return "회원 코드 : " + list.get(i).getCode() + "\n\n회원 이름 : " + list.get(i).getName() + "\n\n회원 생년월일 : "
				+ list.get(i).getBirth().substring(0, 4) + "-" + list.get(i).getBirth().substring(4, 6) + "-"
				+ list.get(i).getBirth().substring(6, 8) + "\n\n회원 성별 : " + list.get(i).getGender() + "\n\n회원 전화번호 : "
				+ list.get(i).getPhone().substring(0, 3) + "-" + list.get(i).getPhone().substring(3, 7) + "-"
				+ list.get(i).getPhone().substring(7, 11) + "\n\n회원 주소 : " + list.get(i).getAddress() + "\n\n회원 등록날짜 : "
				+ list.get(i).getRegDate().substring(0, 4) + "." + list.get(i).getRegDate().substring(4, 6) + "."
				+ list.get(i).getRegDate().substring(6, 8) + "\n\n회원 만기날짜 : " + list.get(i).getExpDate().substring(0, 4)
				+ "." + list.get(i).getExpDate().substring(4, 6) + "." + list.get(i).getExpDate().substring(6, 8);
	}

	public String setText2(int i) {
		return "회원 코드 : " + list.get(i).getCode() + "\n\n회원 이름 : " + list.get(i).getName() + "\n\n회원 생년월일 : "
				+ list.get(i).getBirth().substring(0, 4) + "-" + list.get(i).getBirth().substring(4, 6) + "-"
				+ list.get(i).getBirth().substring(6, 8) + "\n\n회원 성별 : " + list.get(i).getGender() + "\n\n회원 전화번호 : "
				+ list.get(i).getPhone().substring(0, 3) + "-" + list.get(i).getPhone().substring(3, 6) + "-"
				+ list.get(i).getPhone().substring(6, 10) + "\n\n회원 주소 : " + list.get(i).getAddress() + "\n\n회원 등록날짜 : "
				+ list.get(i).getRegDate().substring(0, 4) + "." + list.get(i).getRegDate().substring(4, 6) + "."
				+ list.get(i).getRegDate().substring(6, 8) + "\n\n회원 만기날짜 : " + list.get(i).getExpDate().substring(0, 4)
				+ "." + list.get(i).getExpDate().substring(4, 6) + "." + list.get(i).getExpDate().substring(6, 8);
	}

}
