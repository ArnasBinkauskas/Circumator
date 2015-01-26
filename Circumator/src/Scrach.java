import java.util.HashMap;


public class Scrach {
	
	public static void main(String args[]){
		HashMap<String, Integer> m = new HashMap<String, Integer>();
		m.putIfAbsent("this", 88);
		System.out.println(m.get("this"));
		m.put("this", 99);
		System.out.println(m.get("this"));
	}

}
