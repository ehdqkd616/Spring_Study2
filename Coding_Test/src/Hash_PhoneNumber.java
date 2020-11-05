import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Hash_PhoneNumber {

	class Solution {
	    public boolean solution(String[] phone_book) {
	    	
//	    	전화번호부에 적힌 전화번호 중, 한 번호가 다른 번호의 접두어인 경우가 있는지 확인하려 합니다.
//	    	전화번호가 다음과 같을 경우, 구조대 전화번호는 영석이의 전화번호의 접두사입니다.
//
//	    	구조대 : 119
//	    	박준영 : 97 674 223
//	    	지영석 : 11 9552 4421
//	    	전화번호부에 적힌 전화번호를 담은 배열 phone_book 이 solution 함수의 매개변수로 주어질 때, 어떤 번호가 다른 번호의 접두어인 경우가 있으면 false를 그렇지 않으면 true를 return 하도록 solution 함수를 작성해주세요.
//
//	    	제한 사항
//	    	phone_book의 길이는 1 이상 1,000,000 이하입니다.
//	    	각 전화번호의 길이는 1 이상 20 이하입니다.
	    	
//	    	
		    boolean answer = true;
		    Arrays.sort(phone_book); // 전화번호 정렬
		    Map<String, String> map = new HashMap<String, String>(); 
		    
		    for(int i=0; i<phone_book.length; i++) {
		    	map.put(phone_book[i], "check"); // 전화번호에 value 저장
		    }
		    
		    for(String phone : phone_book) {
		    	for(int j=0; j<phone.length(); j++) {
		    		if(map.containsKey(phone.substring(0, j))) { // 전화번호중 앞자리 일부가 value를 가지고 있으면 그 앞자리 일부가 누군가의 전화번호인 것이 확인됨. 즉, 그 번호는 접두어가 된다.
		    			answer = false;
		    		}
		    	}
		    }
			return answer;
	    }
	}
}
