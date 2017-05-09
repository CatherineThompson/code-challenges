package Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;

public class ArrayListPlayground {

	public static void main(String[] args) {
		System.out.println(compression("aabccccaaax"));

	}
	
	public static String compression(String str) {
		int count = 1;
		char currentChar = str.charAt(0);
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i < str.length(); i++) {
			if(currentChar == str.charAt(i)) {
				count++;
			} else {
				sb.append(currentChar);
				sb.append(count);
				currentChar = str.charAt(i);
				count = 1;
			}
			
			if(i == str.length() - 1) {
				sb.append(currentChar);
				sb.append(count);
			}
		}
		
		if(sb.length() > str.length()) {
			return str;
		}
		
		return sb.toString();
	}
	
	public static boolean oneAway(String str1, String str2) {
		if(str1.length() == str2.length()) return replaceCheck(str1, str2);
		if(Math.abs(str1.length() - str2.length()) == 1) return insertRemoveCheck(str1, str2);
		return false;
	}
	
	private static boolean insertRemoveCheck(String str1, String str2) {
		Map<Character, Integer> table = new HashMap<Character, Integer>();
		String longerStr = str1;
		String shorterStr = str2;
		if(str2.length() > str1.length()) {
			longerStr = str2;
			shorterStr = str1;
		}
		for(char c : longerStr.toCharArray()) {
			if(table.containsKey(c)) {
				int newCount = table.get(c) + 1;
				table.put(c,  newCount);
			} else {
				table.put(c, 1);
			}
		}
		for(char c : shorterStr.toCharArray()) {
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
		
		return table.size() == 1;
	}
	
	private static boolean replaceCheck(String str1, String str2) {
		int diffCount = 0;
		for(int i = 0; i < str1.length(); i++) {
			if (str1.charAt(i) != str2.charAt(i)) {
				diffCount++;
			}
		}
		
		return diffCount <= 1;
	}
	
	public static boolean palindromePerm(String str) {
		String lowerStr = str.toLowerCase();
		HashSet<Character> set = new HashSet<Character>();
		for(char c : lowerStr.toCharArray()) {
			if(c != ' ') {
				if(set.contains(c)) {
					set.remove(c);
				} else {
					set.add(c);
				}
			}
		}

		return set.size() <= 1;
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
