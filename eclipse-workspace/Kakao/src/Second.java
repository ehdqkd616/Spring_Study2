import java.util.*;

public class Second {

	//코스요리 조합 완성 - 맵 사용
	//손님은 2명부터 20명까지 - 각 요리 개수는 2개에서 10개, 요리 알파벳 중복 X
	//코스는 2에서부터 10까지 - 중복 X
	//리턴도 오름차순 - 여러개인 경우 여러개 전부 리턴, 무조건 리턴할 수 있게 줌
	//"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH" / [2,3,4]
	//위의 정답은 2개짜리는 AC, 3개짜리는 CDE, 4개짜리는 ACDE,BCFG 이지만
	//오름차순 정렬을 해야 하기 때문에 AC, ACDE, BCFG, CDE 순으로 나옴.
	static HashMap<String, Integer> recommand = new HashMap<>();

	public static void main(String[] args) {
		String[] c = {"ABCFG","AC","CDE","ACDE","BCFG","ACDEH"};
		String[] a = {"XYZ","XWY","XWA"};
		int[] b = {2,3,4};
		System.out.println(Arrays.toString(solution(a,b)));
	}

	public static String[] solution(String[] orders, int[] course) {
		String[] newOrders = new String[orders.length];
		for(int i=0; i<orders.length; i++) {
			char[] a = orders[i].toCharArray();
			Arrays.sort(a);
			newOrders[i] = new String(a);
		}
		for(String order : newOrders) {
			int n = 0;
			if(order.length()<course[course.length-1]) {
				n = order.length();
			}else {
				n = course[course.length-1];
			}
			for(int i=2; i<=n; i++) {
				makeCombi(order, i, 0, new char[order.length()-1]);
			}
		}
		ArrayList<String[]> answerList = new ArrayList<>();
		ArrayList<String> answer = new ArrayList<>();
		for(int i : course) {
			LinkedHashMap<String, Integer> dummy = divideMap(recommand, i);
			dummy = sortByValue(dummy);
			if(dummy.size()>0) {
				answerList.add(getResult(dummy));
			}
		}
		for(String[] results : answerList) {
			for(String result : results) {
				if(recommand.get(result)>=2) {
					answer.add(result);
				}
			}
		}
		Collections.sort(answer);
		return answer.toArray(new String[answer.size()]);
	}

	public static void makeCombi(String src, int r, int n, char[] temp) {
		if(src.length()==r) {
			recommand.put(src, recommand.getOrDefault(src, 0)+1);
			return;
		}
		if(r==0) {
			String dummy = new String(temp);
			recommand.put(dummy.trim(), recommand.getOrDefault(dummy.trim(), 0)+1);
		}else if(n == src.length()) {
			return;
		}else {
			temp[src.length()-1-r] = src.charAt(n);
			makeCombi(src, r-1, n+1, temp);
			makeCombi(src, r, n+1, temp);
		}
	}

	public static LinkedHashMap<String, Integer> sortByValue(Map<String, Integer> map) {
		List<Map.Entry<String, Integer>> entries = new LinkedList<>(map.entrySet());
		Collections.sort(entries, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));

		LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
		for (Map.Entry<String, Integer> entry : entries) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

	public static LinkedHashMap<String, Integer> divideMap(Map<String,Integer> map, int cnt){
		LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
		for(String key : map.keySet()) {
			if(key.length()==cnt) {
				result.put(key, map.get(key));
			}
		}
		return result;
	}

	public static String[] getResult(Map<String,Integer> map) {
		Set<Map.Entry<String,Integer>> set = map.entrySet();
		Iterator<Map.Entry<String, Integer>> iter = set.iterator();
		Map.Entry<String, Integer> result = iter.next();
		ArrayList<String> list = new ArrayList<>();
		list.add(result.getKey());
		while(iter.hasNext()) {
			Map.Entry<String, Integer> dummy = iter.next();
			if(dummy.getValue()==result.getValue()) {
				list.add(dummy.getKey());
			}
			if(dummy.getValue()<result.getValue()) {
				break;
			}
		}
		return list.toArray(new String[list.size()]);
	}

}
