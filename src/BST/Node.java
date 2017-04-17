package BST;

public class Node {
	public int data;
    public Node left;
    public Node right;
    
    public Node(int data) {
    	this.data = data;
    }
    
    public void setLeft(Node node) {
    	this.left = node;
    }
    
    public void setRight(Node node) {
    	this.right = node;
    }
}
