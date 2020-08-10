package gymCustomerManager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import gymCustomerManager.frame.MyFrame;

public class MemberUpdate extends MemberManagements implements ActionListener{

	// index 값 얻기
	// 검색 기능 사용해서 검색 한 다음 같은게 있으면 index 값을 반환
	// 반환된 index값을 가지고 setTextField 하기
	// 회원 수정 버튼 눌렀을때 회원 검색기능 먼저 실행 (index 값을 찾아내는 역할

	private static final long serialVersionUID = 1L;

	MyFrame frame = new MyFrame();
	
	private JButton memberSearch = new JButton("회원 검색");
	private JButton cancel = new JButton("취소");

	private int index = -1;

	public void memberUpdate() {
		
		frame.setTitle("회원 수정");
		frame.setSize(310, 200);
		frame.setLocation(700, 400);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		memberSearch.setBounds(35, 50, 100, 50);
		cancel.setBounds(160, 50, 100, 50);

		frame.getContentPane().add(memberSearch);
		frame.getContentPane().add(cancel);
		
		frame.setVisible(true);
		
		memberSearch.addActionListener(this);
		cancel.addActionListener(this);
		
	}

	public void actionPerformed(ActionEvent e) {
		
		int dataCnt = 1;
		int dataCnt2 = 1;
		int dataCnt3 = 1;
		
		String[] nameCollection = null;
		String nameCode = null;

		String[] phoneCollection = null;
		String phoneCode = null;
		
		if (e.getSource() == memberSearch) {
			String searchData = JOptionPane.showInputDialog("검색할 회원 코드나 이름 또는 전화번호를 입력하세요");
			int cnt = 0;
			int cnt2 = 0;

			if (searchData == null) {
				return;
			}
			if (list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
					if (searchData.equals(list.get(i).getCode())) {
						index = i;
						MemberUpdateRegister mur = new MemberUpdateRegister();
						mur.memberUpdateRegister(index);
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
					int namecnt = 0;
					for (int i = 0; i < list.size(); i++) {
						if (searchData.equals(list.get(i).getName())) {
							nameCollection[namecnt] = list.get(i).getCode() + ". " + list.get(i).getName() + " "
									+ list.get(i).getPhone();
							namecnt++;
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
							index = i;
							MemberUpdateRegister mur = new MemberUpdateRegister();
							mur.memberUpdateRegister(index);
							break;
						}
					}
				} else {
					for (int i = 0; i < list.size(); i++) {
						if (searchData.equals(list.get(i).getName())) {
							index = i;
							MemberUpdateRegister mur = new MemberUpdateRegister();
							mur.memberUpdateRegister(index);
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
							index = i;
							MemberUpdateRegister mur = new MemberUpdateRegister();
							mur.memberUpdateRegister(index);
							break;
						}
					}
				} else {
					for (int i = 0; i < list.size(); i++) {
						if (searchData.equals(list.get(i).getPhone())) {
							index = i;
							MemberUpdateRegister mur = new MemberUpdateRegister();
							mur.memberUpdateRegister(index);
							break;
						}
						dataCnt3++;
					}
				}
				
				if (dataCnt == list.size() + 1 && dataCnt2 == list.size() + 1 && dataCnt3 == list.size() + 1) {
					JOptionPane.showMessageDialog(this, "일치하는 회원이 없습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "등록된 회원이 없습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
			}

		} else if (e.getSource() == cancel) {
			frame.dispose();
			super.MmStart(); 
		}
	}

}
