package LinkedLists;

import java.util.HashSet;

public class LinkedListsPlayground {

	public static void main(String[] args) {
//		LinkedListInt ll = new LinkedListInt(10);
//		ll.head.appendToTail(9);
//		ll.head.appendToTail(9);
//		ll.head.appendToTail(7);
//		ll.head.appendToTail(9);
//		ll.head.appendToTail(1);
//		ll.head.appendToTail(1);
//		ll.head.appendToTail(7);
//		ll.head.appendToTail(2);
//		ll.head.appendToTail(1);
//		ll.head.appendToTail(1);
//		ll.head.appendToTail(11);
		
//		ll.head.appendToTail(5);
//		ll.head.appendToTail(8);
//		ll.head.appendToTail(5);
//		ll.head.appendToTail(10);
//		ll.head.appendToTail(2);
//		ll.head.appendToTail(1);
	
//		ll.printList();		
//		System.out.println();
//		partition(7, ll);
//		ll.printList();
		
		LinkedListInt l1 = new LinkedListInt(7);
		l1.head.appendToTail(1);
		l1.head.appendToTail(6);
		
		LinkedListInt l2 = new LinkedListInt(5);
		l2.head.appendToTail(9);
		l2.head.appendToTail(2);
		l2.head.appendToTail(1);
		l2.head.appendToTail(5);

		
		LinkedListInt sum = sumsLists(l1, l2);
		
		sum.printList();


	}
	
	public static LinkedListInt sumsLists(LinkedListInt l1, LinkedListInt l2) {
		int carry = 0;
		Node currentNode1 = l1.head;
		Node currentNode2 = l2.head;
		LinkedListInt newList = new LinkedListInt();
		Node newNode = null;
		
		while(currentNode1 != null || currentNode2 != null) {
			if(currentNode1 != null && currentNode2 != null) {
				int sum = currentNode1.data + currentNode2.data + carry;
				if (sum > 9) {
					carry = 1;
					sum -=10;
				} else {
					carry = 0;
				}
				
				if(newList.head == null) {
					newList.head = new Node(sum);
					newNode = newList.head;
				} else {
					newNode.next = new Node(sum);
					newNode = newNode.next;
				}
				
				currentNode1 = currentNode1.next;
				currentNode2 = currentNode2.next;
			} else {
				Node leftOver = currentNode1;
				if (currentNode1 == null) {
					leftOver = currentNode2;
				}
				while(leftOver != null) {
					newNode.next = new Node(leftOver.data);
					newNode = newNode.next;
					leftOver = leftOver.next;
				}
				currentNode1 = null;
				currentNode2 = null;
			}
		}
		
		return newList;
	}
	
	public static void partition(int partition, LinkedListInt ll) {
		Node leftPartTail = null;
		Node rightPartHead = null;
		
		Node currentNode = ll.head;
		while(currentNode != null) {
			Node next = currentNode.next;
			if(currentNode.data < partition) {
				if(leftPartTail == null) {
					leftPartTail = currentNode;
					ll.head = currentNode;
				} else {
					leftPartTail.next = currentNode;
					leftPartTail = currentNode;
				}
			} else {
				if(rightPartHead == null) {
					rightPartHead = currentNode;
					rightPartHead.next = null;
				} else {
					currentNode.next = rightPartHead;
					rightPartHead = currentNode;
				}
			}
			currentNode = next;
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
		
		public LinkedListInt() {
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


