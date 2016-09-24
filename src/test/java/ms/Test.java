package ms;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class Test {
	public static void main(String[] args) {
		String aString = "11";
		String bString = "11";
		HashMap<String, String> map = new HashMap<String, String>();
		map.put(aString, aString);
		map.put(bString, bString);
		Set<Entry<String, String>> setsEntries = map.entrySet();
		Iterator<Entry<String, String>> iterator = setsEntries.iterator();
		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
		
	}

}
