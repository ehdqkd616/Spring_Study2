import java.util.Arrays;
import java.util.HashMap;

public class Hash_Marathon {
	
	class Solution {
	    public String solution(String[] participant, String[] completion) {
	        
	    	String answer = "";
	    	boolean complete;
		    	
	    	for(int i=0; i < participant.length; i++) {
		    		
	    		complete = true;
		    		
		    	for(int j=0; j < completion.length; j++) {
		    		if(participant[i].equals(completion[j])){
		    			completion[j] = completion[j]+"check";
		    			complete = false;
		    			break;
		    		}
			    		
		    	}
			    	
		    	if(complete) {
		    		answer = participant[i];
		    	}
	    	}    
	        return answer;
	    }
	}

	
	class Solution2 {
	    public String solution(String[] participant, String[] completion) {
	        String answer = "";
	        String temp = "";
	        
	        Arrays.sort(participant);
	        Arrays.sort(completion);
	        
	        int i = 0;
	        
	        while(i < completion.length){
	            if(!completion[i].equals(participant[i])){
	                temp = participant[i];
	                break;
	            }else{
	                i++;
	            }
	        }
	        
	        if(!temp.equals("")){
	            answer = temp;
	        }else{
	            answer = participant[participant.length-1];
	        }
	        
	        return answer;
	    }
	}
	

}
