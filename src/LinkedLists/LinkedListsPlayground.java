package LinkedLists;

import java.util.HashSet;

public class LinkedListsPlayground {

	public static void main(String[] args) {
		LinkedListInt ll = new LinkedListInt(10);
		ll.head.appendToTail(9);
		ll.head.appendToTail(9);
		ll.head.appendToTail(7);
		ll.head.appendToTail(9);
		ll.head.appendToTail(1);
		ll.head.appendToTail(1);
		ll.head.appendToTail(7);
		ll.head.appendToTail(2);
		ll.head.appendToTail(1);
		ll.head.appendToTail(1);
		ll.head.appendToTail(11);
		
//		ll.head.appendToTail(5);
//		ll.head.appendToTail(8);
//		ll.head.appendToTail(5);
//		ll.head.appendToTail(10);
//		ll.head.appendToTail(2);
//		ll.head.appendToTail(1);



		
		ll.printList();
		
		System.out.println();
		
		partition(7, ll);
		
		ll.printList();

	}
	
	public static void partition(int partition, LinkedListInt ll) {
		Node leftPartTail = null;
		Node rightPartHead = null;
		
		Node currentNode = ll.head;
		while(currentNode != null) {
			if(currentNode.data < partition) {

				if(leftPartTail == null) {
					leftPartTail = currentNode;
					ll.head = currentNode;
				} else {
					leftPartTail.next = currentNode;
					leftPartTail = currentNode;
				}
				currentNode = currentNode.next;
			} else {
				if(rightPartHead == null) {
					rightPartHead = currentNode;
					currentNode = currentNode.next;
					rightPartHead.next = null;
				} else {
					Node temp = currentNode.next;
					currentNode.next = rightPartHead;
					rightPartHead = currentNode;
					currentNode = temp;
				}
			}
		}
		
		
		
		leftPartTail.next = rightPartHead;
		
	}

	
	
	
	public static void deleteMiddleNode(Node n) {
		Node currentNode = n;
		while(currentNode.next.next != null) {
			currentNode.data = currentNode.next.data;
			currentNode = currentNode.next;
		}
		currentNode.data = currentNode.next.data;
		currentNode.next = null;
	}
	
	public static void deleteMiddleNode2(Node n) {
		Node prevNode = n;
		Node deleteNode = n.next;

		prevNode.data = deleteNode.data;
		prevNode.next = deleteNode.next;
	}
	
	public static class Count {
		public int value = 0;
	}
	
	public static Node kthToLast(LinkedListInt ll, int k) {
		Node runner = ll.head;
		Node kbehind = ll.head;
		int runnerIndex = 0;
		
		while(runner.next != null) {
			runner = runner.next;
			runnerIndex++;
			if(runnerIndex > k) {
				kbehind = kbehind.next;
			}
		}
		
		if(runnerIndex < k) {
			throw new Error("too big");
		}
		
		return kbehind;
	}
	
	public static Node kthToLastRecursive(Node currentNode, int k) {
		Count count = new Count();
		return kthToLastRecursive(currentNode, k, count);
	}
	
	public static Node kthToLastRecursive(Node currentNode, int k, Count count) {
		if(currentNode == null) {
			return null;
		}
		Node n = kthToLastRecursive(currentNode.next, k, count);
		count.value++;
		
		if(count.value == k) {
			return currentNode;
		}
		
		return n;
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


