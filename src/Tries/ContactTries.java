package Tries;

import java.util.HashMap;
import java.util.Scanner;

//Using tries to manage a contacts directory. I need to be able to add
//a contact to the list and find the number of contacts that begin
//with the given string.
public class ContactTries {
	private static int count;

	public static void main(String[] args) {
		Node root = new Node('*');
		add("hack", root);
		add("hackerrank", root);
		find("hac", root);
		find("hak", root);
		
//		Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        Node root = new Node('*');
//
//        
//        for(int a0 = 0; a0 < n; a0++){
//            String op = in.next();
//            String contact = in.next();
//            if (op.equals("add")) {
//                add(contact, root);
//            } else if (op.equals("find")) {
//                find(contact, root);
//            }
//        }
	}

	public static void add(String s, Node root) {
		Node currentNode = root;
		char[] sArray = s.toCharArray();

		for (int i = 0; i < sArray.length; i++) {
			Node addedNode = currentNode.addChild(sArray[i]);
			currentNode = addedNode;			
		}
		
		currentNode.addChild('*');
	}

	public static void find(String s, Node root) {
		Node bottomNode = findBottomOfTrie(s, root);
		count = 0;
		if(bottomNode.data != '!') {
			traverseTrieCount(bottomNode);
		} 
		
		System.out.println(count);
	}
	
	private static Node findBottomOfTrie(String s, Node n) {
		// turn prefix string into char Array
		char[] sArray = s.toCharArray();
		
		// if the parent node contains the next letter of the prefix string
		// travel further down the trie with recursion
		if(n.children.containsKey(sArray[0])) {
			// if the prefix string is the length of one, the bottom of the trie is found
			if (s.length() == 1) {
				return n.children.get(sArray[0]);
			}
			
			// cut off the first letter of the s for the next recursion iteration
			String subString = s.substring(1);
			
			// recurse with the matching child node
			return findBottomOfTrie(subString, n.children.get(sArray[0]));
		} else {
			// there isn't a matching child node, so no words match the prefix
			return new Node('!');
		}
	}
	
	private static void traverseTrieCount(Node n) {
		if(n.data == '*') {
			count ++;
		}
		
		if (n.children != null) {
			for (Node node : n.children.values()) {
				traverseTrieCount(node);
			}
		}
	}
	

//	private static void traverse(Node root) {
//		System.out.println("%" + root.data);
//		for (Character c : root.children.keySet()) {
//			System.out.println(c);
//		}
//		System.out.println("_________");
//
//
//		if (root.children != null) {
//			for (Node n : root.children.values()) {
//				traverse(n);
//			}
//		}
//	}
	
	private static class Node {
		public char data;
	    public HashMap<Character, Node> children;
	    
	    public Node(char data) {
	    	children = new HashMap<Character, Node>();
	    	this.data = data;
	    }
	    
	    public Node addChild(char data) {
	    	if (this.children.containsKey(data)) {
	    		return this.children.get(data);
	    	} else {
	    		Node newNode = new Node(data);
	        	this.children.put(data, newNode);
	        	return newNode;
	    	}
	    }
	    
	}

}
