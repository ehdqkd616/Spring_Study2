package gymCustomerManager.controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import gymCustomerManager.frame.MyFrame;

import javax.swing.JScrollPane;

public class MemberAllSearch extends MemberManagements implements ActionListener, MouseListener{
	
	private static final long serialVersionUID = 1L;
	
	MyFrame frame = new MyFrame();
	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel dtm;
	private DefaultTableCellRenderer dtcr;
	private TableColumnModel tcm;
	
	private JButton uButton = new JButton("수정");
	private JButton dButton = new JButton("삭제");
	private JButton rButton = new JButton("새로 고침");
	
	private String[] column = { "코드", "이름", "생년월일", "성별", "전화번호", "주소", "등록날짜", "만기날짜" };
	private Object[][] memArray = new Object[list.size()][8];
	
	private int selectRow;
	
	
	public void memberAllSearch() {
		
		selectRow = -1;
		memberSearchCheck = 2;
		
		table = null;
		tableTextArea.setText("");
		tableTextArea.setEditable(false);
		
		frame.setTitle("전체회원");
		frame.setSize(1100, 450);
		frame.setLocation(350, 250);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		
		for (int i = 0; i < list.size(); i++) {
			memArray[i][0] = list.get(i).getCode();
			memArray[i][1] = list.get(i).getName();
			memArray[i][2] = list.get(i).getBirth().substring(0, 4) +"-"+ list.get(i).getBirth().substring(4, 6) +"-"+ list.get(i).getBirth().substring(6, 8); 
			memArray[i][3] = list.get(i).getGender();
			memArray[i][4] = (list.get(i).getPhone().length()) == 11 ? 
					list.get(i).getPhone().substring(0, 3) +"-"+ list.get(i).getPhone().substring(3, 7) +"-"+ list.get(i).getPhone().substring(7, 11) : 
					list.get(i).getPhone().substring(0, 3) +"-"+ list.get(i).getPhone().substring(3, 6) +"-"+ list.get(i).getPhone().substring(6, 10);
			memArray[i][5] = list.get(i).getAddress();
			memArray[i][6] = list.get(i).getRegDate().substring(0, 4) +"."+ list.get(i).getRegDate().substring(4, 6) +"."+ list.get(i).getRegDate().substring(6, 8); ;
			memArray[i][7] = list.get(i).getExpDate().substring(0, 4) +"."+ list.get(i).getExpDate().substring(4, 6) +"."+ list.get(i).getExpDate().substring(6, 8); ;
		}
		
		
		dtm = new DefaultTableModel(memArray, column) {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		table = new JTable(dtm); 

		table.getColumn("코드").setPreferredWidth(70);
		table.getColumn("이름").setPreferredWidth(100);
		table.getColumn("생년월일").setPreferredWidth(130);
		table.getColumn("성별").setPreferredWidth(50);
		table.getColumn("전화번호").setPreferredWidth(150);
		table.getColumn("주소").setPreferredWidth(300);
		table.getColumn("등록날짜").setPreferredWidth(130);
		table.getColumn("만기날짜").setPreferredWidth(130);
		
		dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		tcm = table.getColumnModel();
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}
		
		scrollPane = new JScrollPane(table);
		scrollPane.setSize(700, 387);
		scrollPane.setLocation(12, 10);
		scrollPane.setPreferredSize(new Dimension(369, 203));

		frame.getContentPane().add(scrollPane);
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false); // 이동 불가
		table.getTableHeader().setResizingAllowed(false); // 크기 조절 불가	
		
		
		tableTextArea.setBounds(750, 30, 300, 300);
		frame.getContentPane().add(tableTextArea);
		
		uButton.setBounds(750, 350, 95, 32);
		dButton.setBounds(850, 350, 95, 32);
		rButton.setBounds(950, 350, 95, 32);
		
		frame.getContentPane().add(uButton);
		frame.getContentPane().add(dButton);
		frame.getContentPane().add(rButton);
				
		frame.setVisible(true);
		
		table.addMouseListener(this);
		uButton.addActionListener(this);
		dButton.addActionListener(this);
		rButton.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == uButton) {
			if (selectRow == -1) {
				JOptionPane.showMessageDialog(this, "선택된 회원이 없습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
			} else {
				memberSearchCheck = 2;
				MemberUpdateRegister mur = new MemberUpdateRegister();
				mur.memberUpdateRegister(selectRow);
				selectRow = -1;
			}
		} else if (e.getSource() == dButton) {
			if (selectRow == -1) {
				JOptionPane.showMessageDialog(this, "선택된 회원이 없습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
			} else {
				int check3 = JOptionPane.showConfirmDialog(this, "회원을 삭제하시겠습니까?\n", "메시지",
						JOptionPane.INFORMATION_MESSAGE);
				if (check3 == 0) {
					list.remove(selectRow);
					fileSave();
					JOptionPane.showMessageDialog(this, "회원정보가 삭제되었습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
					frame.dispose();
					MemberAllSearch mas = new MemberAllSearch();
					mas.memberAllSearch();
				}
			}
		} else if(e.getSource() == rButton) {
			frame.dispose();
			MemberAllSearch mas = new MemberAllSearch();
			mas.memberAllSearch();
		}
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		
		selectRow = table.getSelectedRow();
		Object[] memArray2 = new Object[8];
		for (int i = 0; i < 8; i++) {
			memArray2[i] = table.getValueAt(selectRow, i);
			if(list.get(selectRow).getPhone().length()==11) {
				tableTextArea.setText(setText1(selectRow));
			} else if(list.get(selectRow).getPhone().length()==10) {
				tableTextArea.setText(setText2(selectRow));
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
	
	
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



