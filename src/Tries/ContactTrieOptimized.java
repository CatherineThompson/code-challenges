package Tries;

import java.util.HashMap;
import java.util.Scanner;

//Using tries to manage a contacts directory. I need to be able to add
//a contact to the list and find the number of contacts that begin
//with the given string.
//This trie is a little faster, but now finding prefixes is really slow.
public class ContactTrieOptimized {

	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.addContact("hack");
		trie.addContact("hackerrank");
		trie.addContact("pin");
		trie.find("hac");
		trie.find("hak");
		
//		Scanner in = new Scanner(System.in);
//		int n = in.nextInt();
//		Trie trie = new Trie();
//
//		for(int a0 = 0; a0 < n; a0++) {
//          String op = in.next();
//          String contact = in.next();
//          if (op.equals("add")) {
//              trie.addContact(contact);
//          } else if (op.equals("find")) {
//              trie.find(contact);
//          }
//		}
	}
}

// Each TrieNode contains an array on 26 TrieNodes, one for each
// letter of the alphabet.
class TrieNode {
	public char c;
	public TrieNode[] data;
    public boolean isEnd;
    
    public TrieNode(char c) {
    	this.data = new TrieNode[26];
    	this.c = c;
    }
}

class Trie {
	private TrieNode root;
	private static int count;
	
	public Trie() {
		root = new TrieNode('*');
	}
	
	public void addContact(String contact) {
		TrieNode currentNode = this.root;
		for(int i = 0; i < contact.length(); i++) {
			char c = contact.charAt(i);
			int index = c-'a';
			// creates new nodes in tree if empty, or 
			// travels down trie if node exists
			if(currentNode.data[index] == null) {
				TrieNode newNode = new TrieNode(c);
				currentNode.data[index] = newNode;
				currentNode = newNode;
			} else {
				currentNode = currentNode.data[index];
			}
		}
		currentNode.isEnd = true;
	}
	
	public void find(String prefix) {
		count = 0;
		TrieNode bottomNode = findBottomOfTrie(prefix);
		if (bottomNode != null) {
			traverseTrieCount(bottomNode);
		}
		System.out.println(count);
	}
	
	public TrieNode findBottomOfTrie(String prefix) {
		TrieNode currentNode = this.root;
		for(int i = 0; i < prefix.length(); i++) {
			char c = prefix.charAt(i);
			int index = c - 'a';
			if(currentNode.data[index] != null) {
				currentNode = currentNode.data[index];
			} else {
				return null;
			}
		}
		
		return currentNode;
	}
	
	private static void traverseTrieCount(TrieNode n) {
		if(n.isEnd) {
			count ++;
		}
		for(int i = 0; i < n.data.length; i++) {
			if (n.data[i] != null) {
				traverseTrieCount(n.data[i]);
			}
		}
	}
}

