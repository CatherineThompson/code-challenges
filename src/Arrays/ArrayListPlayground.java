package Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;

public class ArrayListPlayground {

	public static void main(String[] args) {
		String str = "Mr John Smith      ";
		char[] charArray = str.toCharArray();
		URLify(charArray, 13);
		System.out.println(new String(charArray));

	}
	
	public static void URLify(char[] input, int trueLength) {
		int spaceCount = 0;
		
		for(int i = 0; i < trueLength; i++) {
			if(input[i] == ' ') spaceCount++;
		}
				
		if(spaceCount == 0) return;
		
		for(int i = trueLength - 1; i >= 0; i--) {
			if(input[i] == ' ') {
				input[i + 2 * spaceCount] = '0';
				input[i + 2 * spaceCount - 1] = '2';
				input[i + 2 * spaceCount - 2] = '%';
				spaceCount--;
			} else {
				input[i + 2 * spaceCount] = input[i];
			}
		}		
	}

	
	// runtime: O(N), space: O(1) uses array of length 256
	public static boolean uniqueChars(String str) {
		if (str.length() > 256) {
			return false;
		}
		
		boolean[] inString = new boolean[256];
		
		for(int i = 0; i < str.length(); i++) {
			int ch = str.charAt(i);

			if (inString[ch]) {
				return false;
			}
			
			inString[ch] = true;
		}
		
		return true;
	}
	
	public static boolean isPermutation(String str1, String str2) {
		if(str1.length() != str2.length()) {
			return false;
		}
		
		HashMap<Character, Integer> table = new HashMap<Character, Integer>();
		for(char c : str2.toCharArray()) {
			if(table.containsKey(c)) {
				int newCount = table.get(c) + 1;
				table.put(c, newCount);
			} else {
				table.put(c, 1);
			}
		}
		
		for(char c : str1.toCharArray()) {
			if(table.containsKey(c)) {
				if(table.get(c) > 1) {
					int newCount = table.get(c) - 1;
					table.put(c, newCount);
				} else {
					table.remove(c);
				}
			} else {
				return false;
			}
		}
		
		return table.isEmpty();
	}

}
