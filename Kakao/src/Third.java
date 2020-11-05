import java.util.*;

public class Third {

	//코테 항목에 cpp, java, python
	//직군 항목에 backend, frontend
	//경력구분 항목 junior, senior
	//소울푸드 chicken, pizza
	//- 표시는 해당 조건은 고려하지 않겠다는 뜻
	//각 단어는 " " 으로 구분, and 표시로 query 작성
	//뒤의 query 배열의 조건과 맞는 사람들의 숫자를 리턴

	public static void main(String[] args) {
		String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
		String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
		System.out.println(Arrays.toString(solution(info,query)));
	}

	public static int[] solution(String[] info, String[] query) {
		ArrayList<ApplyVO> applyList = new ArrayList<>();
		for(String in : info) {
			StringTokenizer st = new StringTokenizer(in);
			ApplyVO ap = new ApplyVO();
			while(st.hasMoreTokens()) {
				ap.setLang(st.nextToken());
				ap.setJob(st.nextToken().equals("backend") ? true : false);
				ap.setCareer(st.nextToken().equals("junior") ? true : false);
				ap.setFood(st.nextToken().equals("chicken") ? true : false);
				ap.setScore(Integer.parseInt(st.nextToken()));
			}
			applyList.add(ap);
		}
		ArrayList<Integer> answerList = new ArrayList<>();
		for(String qu : query) {
			int qcnt = 0;
			StringTokenizer st = new StringTokenizer(qu);
			String lang = st.nextToken();
			st.nextToken();
			String job = st.nextToken(); 
			st.nextToken();
			String career = st.nextToken();
			st.nextToken();
			String food = st.nextToken();
			String score = st.nextToken();
			for(ApplyVO ap : applyList) {
				boolean l = false;
				boolean j = false;
				boolean c = false;
				boolean f = false;
				boolean s = false;
				if(ap.getLang().equals(lang) || lang.charAt(0)=='-') {
					l = true;
				}
				if(job.charAt(0)=='-') {
					j = true;
				}else {
					if(job.equals("backend")) {
						j = ap.isJob() ? true : false;
					}else {
						j = ap.isJob() ? false : true;
					}
				}
				if(career.charAt(0)=='-') {
					c = true;
				}else {
					if(career.equals("junior")) {
						c = ap.isCareer() ? true : false;
					}else {
						c = ap.isCareer() ? false : true;
					}
				}
				if(food.charAt(0)=='-') {
					f = true;
				}else {
					if(food.equals("chicken")) {
						f = ap.isFood() ? true : false;
					}else {
						f = ap.isFood() ? false : true;
					}
				}
				if(Integer.parseInt(score)<=ap.getScore() || score.charAt(0)=='-') {
					s = true;
				}
				if(l & j & c & f & s) {
					qcnt++;
					
				}
			}
			answerList.add(qcnt);
		}
		int[] answers = new int[answerList.size()];
		for(int i=0; i<answerList.size(); i++) {
			answers[i] = answerList.get(i);
		}
		return answers;
	}

	static class ApplyVO {
		String lang;
		boolean job;
		boolean career;
		boolean food;
		int score;

		public String getLang() {
			return lang;
		}
		public void setLang(String lang) {
			this.lang = lang;
		}
		public boolean isJob() {
			return job;
		}
		public void setJob(boolean job) {
			this.job = job;
		}
		public boolean isCareer() {
			return career;
		}
		public void setCareer(boolean career) {
			this.career = career;
		}
		public boolean isFood() {
			return food;
		}
		public void setFood(boolean food) {
			this.food = food;
		}
		public int getScore() {
			return score;
		}
		public void setScore(int score) {
			this.score = score;
		}
		
		public ApplyVO() {

		}

	}

}
