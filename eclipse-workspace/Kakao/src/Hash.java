import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Hash {

	public static void main(String[] args) {

	}

	class Solution {
		
		public String[] solution(String[] record) {
	    	
	    	HashMap<String, String> users = new HashMap<String, String>(); //유저<아이디, 닉네임>
	    	ArrayList<String> list = new ArrayList<String>();
	    	
	    	ArrayList<String> commend = new ArrayList<String>();	    	
            
	    	for (String str : record) {

                String[] recordArray = str.split(" ");

                String state = recordArray[0]; // 입장/퇴장/닉네임 변경 상태
                String id	 = recordArray[1]; // 아이디
                
	            if (recordArray.length == 3) { // 입장 or 닉네임.
	            	
	                String name	= recordArray[2];
	
	                users.put(id, name); // 아이디에 닉네임 저장
	                
	                if(!state.equals("Change")) {
	                	list.add(state + " " + id);	// list에 상태 + 아이디 추가 정렬
	                }
	            }
	            
	            String[] answer = 
	            
	            
            }
		}
	}

