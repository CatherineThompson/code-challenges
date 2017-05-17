package StacksAndQueues;

public class StacksAndQueuesPlayground {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private static class Stack<T> {
		private static class Node<T> {
			private T data;
			private Node<T> next;
			
			public Node(T item) {
				this.data = item;
			}
		}
		
		private Node<T> top;
		
		public T pop() {
			if (this.isEmpty()) throw new Error();
			
			T poppedItem = top.data;
			top = top.next;
			
			return poppedItem;
		}
		
		public void push(T item) {
			Node<T> newNode = new Node<T>(item);
			newNode.next = top;
			top = newNode;
		}
		
		public T peak() {
			if (this.isEmpty()) throw new Error();
			return top.data;
		}
		
		public boolean isEmpty() {
			return this.top == null;
		}
	}
	
	private static class Queue<T> {
		private static class Node<T> {
			private T data;
			private Node<T> next; 
			
			public Node(T item) {
				this.data = item;
			}
		}
		
		private Node<T> first;
		private Node<T> last;
		
		public void add(T item) {
			Node newNode = new Node(item);

			if(this.isEmpty()) {
				first = newNode;
			} else {
				last.next = newNode;
			}
			
			last = newNode;	
		}
		
		public T remove() {
			if(this.isEmpty()) throw new Error();
			
			T data = first.data;
			first = first.next;
			
			if(this.isEmpty()) {
				last = null;
			}
			
			return data;
		}
		
		public T peek() {
			if(this.isEmpty()) throw new Error();
			return first.data;
		}
		
		public boolean isEmpty() {
			return first == null;
		}
	}
	
	

}
