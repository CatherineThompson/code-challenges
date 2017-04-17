package Arrays;
// This class shifts an array k positions to the left and prints it out
// with a space between each integer. Big O is O(n) after parsing the input
// which is in the form
// n k
// array separated by spaces
// example input
// 5 4
// 1 2 3 4 5
// example output 
// 5 1 2 3 4
public class RotateLeft {

    public static void main(String[] args) {
        // Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);
        int a[] = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = Integer.parseInt(args[a_i + 2]);
        }
        
        // Create an array with a fixed size is necessary in Java
        int[] newArray = new int[n];
        
        for (int i = 0; i < n; i++) {
        	if (i - k >= 0) {
        		newArray[i-k] = a[i];
        	} else {
        		newArray[n + (i-k)] = a[i];
        	}
        }
        
        StringBuilder sb = new StringBuilder();
                
    	for (int i: newArray) {
    		sb.append(i + " ");
    	}
    	
		System.out.println(sb);
        
    }
}