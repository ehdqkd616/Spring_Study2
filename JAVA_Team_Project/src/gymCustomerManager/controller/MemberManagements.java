package gymCustomerManager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import gymCustomerManager.frame.MyFrame;
import gymCustomerManager.model.vo.MemberVO;



public class MemberManagements extends MyFrame{
	
	private static final long serialVersionUID = 1L;
	
	static ArrayList<MemberVO> list = new ArrayList<MemberVO>();
	static JTextArea searchTextArea = new JTextArea();
	static JTextArea tableTextArea = new JTextArea();
	static int memberSearchCheck=0;
	
	MyFrame frame = new MyFrame();
	
	public MemberManagements() { // 생성자

		JLabel lblNewLabel = new JLabel(new ImageIcon("./src/main.jpg"));
		lblNewLabel.setBounds(22, 10, 431, 213);
		frame.getContentPane().add(lblNewLabel);
		fileLoad();
	}
	
	
	public void MmStart(){
		
		frame.setTitle("헬스장 회원관리 프로그램");
		frame.setSize(500, 360);
		frame.setLocation(500, 300);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		JButton bt1 = new JButton("회원등록");
		
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MemberInsert mi = new MemberInsert();
				mi.memberInsert();
			}
		});
		bt1.setBounds(22, 232, 133, 49);
		frame.getContentPane().add(bt1);
		
		JButton bt2 = new JButton("회원수정");
		bt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MemberUpdate mu = new MemberUpdate();
				mu.memberUpdate();
			}
		});
		bt2.setBounds(174, 232, 133, 49);
		frame.getContentPane().add(bt2);
		
		JButton bt3 = new JButton("회원조회");
		bt3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MemberSearch ms = new MemberSearch();
				ms.memberSearch();
			}
		});
		bt3.setBounds(324, 232, 133, 49);
		frame.getContentPane().add(bt3);
		
		
		frame.setVisible(true);
		
	}
	
	public void fileLoad(){
		FileInputStream fi = null;
		InputStreamReader isr = null;
		BufferedReader bfr = null;
		StringTokenizer st = null;	
		try{
				list.clear();
				fi = new FileInputStream("CustomerManager.txt");
				isr = new InputStreamReader(fi);
				bfr = new BufferedReader(isr);
				String str = null;
				while((str = bfr.readLine())!= null){
					MemberVO m = new MemberVO();
					st = new StringTokenizer(str,",");
					m.setCode(st.nextToken());
					m.setName(st.nextToken());
					m.setBirth(st.nextToken());
					m.setGender(st.nextToken());
					m.setPhone(st.nextToken());
					m.setAddress(st.nextToken());
					m.setRegDate(st.nextToken());
					m.setExpDate(st.nextToken());
					list.add(m);
				}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try{
				fi.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public void fileSave(){
		 FileWriter fw = null;
		 try{
		   fw = new FileWriter("CustomerManager.txt");
		   for(int i=0; i<list.size(); i++){
			   fw.write(list.get(i).getCode());
			   fw.write(",");
			   fw.write(list.get(i).getName());
			   fw.write(",");
			   fw.write(list.get(i).getBirth());
			   fw.write(",");
			   fw.write(list.get(i).getGender());
			   fw.write(",");
			   fw.write(list.get(i).getPhone());
			   fw.write(",");
			   fw.write(list.get(i).getAddress());
			   fw.write(",");
			   fw.write(list.get(i).getRegDate());
			   fw.write(",");
			   fw.write(list.get(i).getExpDate());
			   fw.write("\r\n");
		   }       
	    }catch (Exception e) {
	    	e.printStackTrace();
	   }finally{
			try{
				fw.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
}

