package LinkedLists;

import java.util.HashSet;

public class LinkedListsPlayground {

	public static void main(String[] args) {
		LinkedListInt ll = new LinkedListInt(5);
		ll.head.appendToTail(9);
		ll.head.appendToTail(9);
		ll.head.appendToTail(7);
		ll.head.appendToTail(9);
		ll.head.appendToTail(7);
		ll.head.appendToTail(2);
		ll.head.appendToTail(1);
		ll.head.appendToTail(11);

		
		ll.printList();
		
		removeDupsNoBuffer(ll);
		
		System.out.println();
		ll.printList();

	}
	
	public static void removeDups(LinkedListInt ll) {
		HashSet<Integer> hs = new HashSet<Integer>();
		if (ll.head == null || ll.head.next == null) return;
	
		Node prev = ll.head;
		Node n = prev.next;
		
		hs.add(ll.head.data);

		while(n != null) {
			if (hs.contains(n.data)) {
				deleteNode(prev, n);
			} else {
				hs.add(n.data);
				prev = n;
			}
			
			n = n.next;
		}
	}
	
	public static void removeDupsNoBuffer(LinkedListInt ll) {
		if (ll.head == null || ll.head.next == null) return;
	
		Node current = ll.head;
		Node runner = ll.head.next;
		Node prev = ll.head;
		
		while(current.next.next != null) {
			if (runner == null) {
				current = current.next;
				runner = current.next;
				prev = current;
			}
			if (runner.data == current.data) {
				deleteNode(prev, runner);
			} else {
				prev = runner; 
			}
			
			runner = runner.next;
		}
	}
	
	public static void deleteNode(Node prev, Node n) {
		prev.next = n.next;
	}
	
	public static class LinkedListInt {
		public Node head = null;
		
		public LinkedListInt(int data) {
			head = new Node(data);
		}
		
		public void printList() {
			Node n = head;
			while(n != null) {
				System.out.println(n.data);
				n = n.next;
			}
		}
		
	}
	
	public static class Node {
		Node next = null;
		int data;
		
		public Node(int data) {
			this.data = data;
		}
		
		public void appendToTail(int d) {
			Node end = new Node(d);
			Node n = this;
			
			while(n.next != null) {
				n = n.next;
			}
			
			n.next = end;
			
		}
	}

	

}

