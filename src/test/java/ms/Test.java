package ms;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class Test {
	public static void main(String[] args) {
		long l =  Long.parseLong("1475913221496");
		System.out.println((new Date().getTime() - l)/1000);
	}

}
