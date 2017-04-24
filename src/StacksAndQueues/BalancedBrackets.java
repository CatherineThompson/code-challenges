package StacksAndQueues;

import java.util.Scanner;

public class BalancedBrackets {
  
    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int t = in.nextInt();
//        for (int a0 = 0; a0 < t; a0++) {
//            String expression = in.next();
//            System.out.println( (isBalanced(expression)) ? "YES" : "NO" );
//        }
    	
    	System.out.println(isBalanced("{}]"));
    	System.out.println(isBalanced("{[(])}"));
    	System.out.println(isBalanced("{{[[(())]]}}"));
    }
    
    public static boolean isBalanced(String expression) {
        Stack stack = new Stack();
        for(char c : expression.toCharArray()) {
        	if(c == '{' || c == '[' || c == '(') {
        		stack.push(c);
        	} else {
        		if (!stack.isEmpty()) {
        			char poppedElement = stack.peek();
            		if (c == '}' && poppedElement != '{') {
            			return false;
            		} else if (c == ']' && poppedElement != '[') {
            			return false;
            		} else if (c == ')' && poppedElement != '(') {
            			return false;
            		} else {
            			stack.pop();
            		}
        		} else return false;
        		
        	}
        }
        
        return stack.isEmpty();
                
    }
    
    private static class Stack {
    	private Node top;
    	
    	public boolean isEmpty() {
    		return top == null;
    	}
    	
    	public char peek() {
    		return top.element;
    	}
    	
    	public void push(char element) {
    		Node newNode = new Node(element);
    		newNode.next = this.top;
    		this.top = newNode;
    	}
    	
    	public char pop() {
    		char temp = top.element;
    		top = top.next;
    		return temp;
	}
    	
    	private class Node {
        	private char element;
        	private Node next;
        	
        	public Node(char element) {
        		this.element = element;
        	}
        	
        }
    }
    
    
    
}
