/*
 * File: Strings.java
 * ------------------
 * 
 * Handouts #4 
 */


import com.sun.org.apache.bcel.internal.generic.ISUB;

import acm.program.*;



public class Strings extends ConsoleProgram {
	
	public void run() {
		String str = chooseLine();
		//str = removeAllOccurences(str, 't');
		str = altCaps(str);
		println(str);
	}

	public String altCaps(String str) {
		String result = "";
		int counter = 0; 
		for (int i = 0; i <str.length(); i++) {
			if (Character.isLetter(str.charAt(i))) counter ++;
			
			if (counter%2 == 0) {
				result += Character.toUpperCase(str.charAt(i));
			} else {
				result += Character.toLowerCase(str.charAt(i));
			}
		}
		return result;
	}
	public String removeAllOccurences(String str, char ch) {
		while (str.indexOf(ch) != -1) {
			int index = str.indexOf(ch);
			if (index != -1){
				String s1 = str.substring(0, index);
				String s2  = index == str.length() ? "" : str.substring(index+1);
				str = s1 + s2;
			}
		}
		return str;
	}
	
	
	
	
	private String chooseLine(){
		String str = readLine("Enter String to edit");
		return str;
	}
	
	
	
}

