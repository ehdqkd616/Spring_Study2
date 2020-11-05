
public class First {

	//아이디가 들어올 때 아이디 추천하게끔 - 정규표현식 사용
	//아이디의 길이는 3자이상 15자 이하
	//아이디는 알파벳 소문자, 숫자, - _ . 만 사용 가능.
	//. 는 처음과 끝에 사용할 수 없으며 연속 불가.

	public static void main(String[] args) {
		String id = "...!@BaT#*..y.abcdefghijklm.";
		String id2 = "=.=";
		String id3 = "z-+.^.";
		solution(id2);
	}

	public static String solution(String new_id) {
		new_id = new_id.toLowerCase();
		String dummy = null;
		dummy = new_id.replaceAll("[^a-z0-9-_.]", "");
		dummy = dummy.replaceAll("\\.{2,}", ".");
		if(!(dummy.length()==0)) {
			if(dummy.charAt(0)=='.') {
				dummy = dummy.substring(1,dummy.length());
			}
		}
		if(!(dummy.length()==0)) {
			if(dummy.charAt(dummy.length()-1)=='.') {
				dummy = dummy.substring(0,dummy.length()-1);
			}
		}
		if(dummy.equals("")) {
			dummy = "aaa";
			return dummy;
		}
		if(dummy.length()>15) {
			dummy = dummy.substring(0,15);
			if(dummy.charAt(dummy.length()-1)=='.') {
				dummy = dummy.substring(0,dummy.length()-1);
			}
		}
		if(dummy.length()<3) {
			while(dummy.length()<3) {
				dummy = dummy + dummy.charAt(dummy.length()-1);
			}
		}
		return dummy;
	}

}
