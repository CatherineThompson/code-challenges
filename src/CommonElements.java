import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// This original algorithm takes in two arrays and returns a list of common elements
// This has a Big O time of (A*B) in the worst case and A in the best
// Using a hashSet allows me to check if an element is contained within the 
// entire hashSet (since it returns a boolean) and therefore only use one for loop
// but since the arrays are int[] types and not Integer[] types, I'd have to convert
// array2 to Integer[] types, which takes another loop. O time is still better (A+B)
public class CommonElements {

	public static void main(String[] args) {
		int[] array1 = {1, 2, 5, 7, 8};
		int[] array2 = {3, 4, 5, 7, 10};
		
		System.out.println(FindCommonElements(array1, array2));
	}
		
	public static List<Integer> FindCommonElements(int[] array1, int[] array2) {
		List<Integer> arrayCommon = new ArrayList<>();
				
		// convert array2 from type int[] to type Integer[]
		// adds B to big O
		Integer[] newArray2 = new Integer[array2.length];
		int i = 0;
		for (int value : array2) {
		    newArray2[i++] = Integer.valueOf(value);
		}
		
		// HashSet creation is constant O(1)
		Set<Integer> array2Set = new HashSet<Integer>(Arrays.asList(newArray2));
		
		// Adds A to big O, contains check is constant O(1)
		for(int num : array1) {
			boolean common = array2Set.contains(num);
			if (common) {
				arrayCommon.add(num);
			}
		}
		
		// original method had big O of (A*B)
//		for(int i : array1) {
//			for(int j : array2) {
//				if(i < j) {
//					break;
//				} 
//				if(i == j) {
//					arrayCommon.add(i);
//				}
//			}
//		}

    	return arrayCommon;
    }

}
