package Tries;

import java.util.HashMap;

// This trie will store strings instead of chars
public class ContactTrieOptimized2 {

	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.add("apple", trie.root);
		trie.add("application", trie.root);
		trie.add("apz", trie.root);
		trie.add("apples", trie.root);

		trie.find("apl");
		// trie.traverse(trie.root);

	}

	private static class TrieNode {
		public String data;
		public HashMap<Character, TrieNode> children;
		public boolean isEnd;

		public TrieNode(String data) {
			children = new HashMap<Character, TrieNode>();
			this.data = data;
			isEnd = false;
		}
	}

	// dont forget to handle case contact "apples" and current node "apple"
	private static class Trie {
		public TrieNode root;
		public int count;

		public Trie() {
			root = new TrieNode("*");
			count = 0;
		}

		public void add(String contact, TrieNode n) {
			TrieNode currentNode = n;
			
			// if the current node has a child with matching first characters
			// grab the child and find where the string differs
			if (currentNode.children.containsKey(contact.charAt(0))) {
				currentNode = currentNode.children.get(contact.charAt(0));

				// example: contact is "apple" and current node data is "ap"
				if(contact.startsWith(currentNode.data)) {
					String nextString = contact.substring(currentNode.data.length());

					add(nextString, currentNode);
				} else if (currentNode.data.startsWith(contact)) { 				// example: contact is "apple" and current node data is "apples"
					// takes the leftover string that differ and adds them 
					// as new child nodes to the current node
					String newString = currentNode.data.substring(contact.length());
					TrieNode newNode = new TrieNode(newString);
					newNode.isEnd = true;
									
					// move children of current node to new substring node
					for (TrieNode child : currentNode.children.values()) {
						newNode.children.put(child.data.charAt(0), child);
					}
					currentNode.children.clear();
							
					currentNode.children.put(newString.charAt(0), newNode);
							
					// change current node data to matching part of contact string
					currentNode.data = currentNode.data.substring(0, contact.length());
					
					return;
				} else { 				// example: contact is "apple" and current node data is "application"
					for (int i = 0; i < contact.length(); i++) {
						if (contact.charAt(i) != currentNode.data.charAt(i)) {
							// takes the leftover strings that differ and adds them 
							// as new child nodes to the current node
							String newString1 = currentNode.data.substring(i);
							String newString2 = contact.substring(i);

							TrieNode newNode1 = new TrieNode(newString1);
							TrieNode newNode2 = new TrieNode(newString2);
							
							if(currentNode.isEnd) {
								newNode1.isEnd = true;
								currentNode.isEnd = false;
							}
							newNode2.isEnd = true;
									
							// move children of current node to new substring node
							for (TrieNode child : currentNode.children.values()) {
								newNode1.children.put(child.data.charAt(0), child);
							}
							currentNode.children.clear();
							
							currentNode.children.put(newString1.charAt(0), newNode1);
							currentNode.children.put(newString2.charAt(0), newNode2);
							
							// change current node data to matching part of contact string
							currentNode.data = currentNode.data.substring(0, i);
							
							return;
						}
					}
				 }	
			} else {
				TrieNode newNode = new TrieNode(contact);
				newNode.isEnd = true;
				currentNode.children.put(contact.charAt(0), newNode);
			}
		}
		
		public void find(String prefix) {
			count = 0;
			TrieNode bottom = findBottomOfTrie(prefix, root);
			findCount(bottom);
			System.out.println(count);
		}

		private TrieNode findBottomOfTrie(String prefix, TrieNode n) {
			TrieNode currentNode = n;

			if (currentNode.children.containsKey(prefix.charAt(0))) {
				currentNode = currentNode.children.get(prefix.charAt(0));

				// example: prefix is "ap" and currentNode is "ap"
				// or prefix is "a" and currentNode is "ap"
				if (currentNode.data.equals(prefix) || currentNode.data.startsWith(prefix)) {
					return currentNode;
				} else if (prefix.startsWith(currentNode.data)) {	// example: prefix is "app" and current node is "ap"
					String newPrefix = prefix.substring(currentNode.data.length());
					return findBottomOfTrie(newPrefix, currentNode);
				}
			}
			
			return new TrieNode(null); 
		}
		
		private void findCount(TrieNode node) {
			if (node.isEnd) {
				count ++;
			}
			
			for (TrieNode n : node.children.values()) {
				findCount(n);
			}
		}

		private void traverse(TrieNode node) {
			System.out.println("%" + node.data);
			System.out.println(node.isEnd);

			System.out.println("children: ");
			for (TrieNode n : node.children.values()) {
				System.out.println(n.data);
			}
			
			System.out.println("_________");

			if (node.children != null) {
				for (TrieNode n : node.children.values()) {
					traverse(n);
				}
			}
		}

	}

}
