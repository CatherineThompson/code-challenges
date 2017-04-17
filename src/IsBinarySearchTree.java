import java.util.ArrayList;

//	The Node class is defined as follows:
//	    class Node {
//	        int data;
//	        Node left;
//	        Node right;
//	     }

// Since this method traverses a binary tree O(n), adds values in an array
// and loops through the array to check that it is in order O(n)
// the over all O(n)
// This method takes in a binary tree and decides if it is a binary search tree.
public class IsBinarySearchTree {

	public static void main(String[] args) {
		// tree 1 should return true
		Node root = new Node(4);
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n5 = new Node(5);
		Node n6 = new Node(6);
		Node n7 = new Node(7);

		root.setLeft(n2);
		root.setRight(n6);

		n2.setLeft(n1);
		n2.setRight(n3);

		n6.setLeft(n5);
		n6.setRight(n7);

		// tree 2 should return false
		Node root2 = new Node(8);
		Node o1 = new Node(1);
		Node o2 = new Node(2);
		Node o4 = new Node(4);
		Node o11 = new Node(11);
		Node o12 = new Node(12);
		Node o13 = new Node(13);

		root2.setLeft(o4);
		root2.setRight(o11);

		o4.setLeft(o1);
		o1.setRight(o2);

		o11.setLeft(o12);
		o11.setRight(o13);

		System.out.println(checkBST(root)); // true
		System.out.println(checkBST(root2)); // false

		// corner case single node ~ should return true
		System.out.println(checkBST(o13));

	}

	public static boolean checkBST(Node root) {
		ArrayList<Integer> array = new ArrayList<Integer>();

		traverseBT(root, array);
		
		return isSorted(array);
	}

	private static void traverseBT(Node root, ArrayList<Integer> array) {
		if (root.left != null) {
			traverseBT(root.left, array);
		}
		array.add(root.data);
		if (root.right != null) {
			traverseBT(root.right, array);
		}
	}
	
	private static boolean isSorted(ArrayList<Integer> array) {
		for (int i = 0; i < array.size() - 1; i++) {
			if (array.get(i) >= array.get(i+1)) {
				return false;
			}
		}
		
		return true;
	}

}
