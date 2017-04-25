package Heaps;

import java.util.Scanner;
import java.util.*;

public class RunningMedian {

	public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        int[] a = new int[n];
//        for(int a_i=0; a_i < n; a_i++){
//            a[a_i] = in.nextInt();
//        }
     
        int[] array = {12, 4, 5, 3, 8, 7};
        for(int i = 0; i < array.length; i ++) {
        	
    		MinIntHeap heap = new MinIntHeap();

    		int[] sortedArray = new int[i + 1];
    		for(int j = 0; j < i + 1; j++) {
            	heap.add(array[j]);
    		}
    		
    		for(int j = 0; j < i + 1; j++) {
            	int nextInt = heap.poll();
            	sortedArray[j] = nextInt;
    		}

    		if(sortedArray.length % 2 == 0) {
				int middleIndex1 = sortedArray.length/2 - 1;
				int middleIndex2 = sortedArray.length/2;

				double average = (sortedArray[middleIndex1] + sortedArray[middleIndex2]) / 2.0;
				
				System.out.println(average);
				
			} else {
				int middle = Math.floorDiv(sortedArray.length, 2);
				double median = sortedArray[middle];
				System.out.println(median);
			}
        }
    }
		
	private static class MinIntHeap {
		private int capacity = 10;
		private int size = 0;
		private int[] items = new int[capacity];
		
		private int getLeftChildIndex(int parentIndex) {return parentIndex * 2 + 1;};
		private int getRightChildIndex(int parentIndex) {return parentIndex * 2 + 2;};
		private int getParentIndex(int childIndex) {return (childIndex - 1) / 2;};

		private boolean hasLeftChild(int index) {return getLeftChildIndex(index) < size;};
		private boolean hasRightChild(int index) {return getRightChildIndex(index) < size;};
		private boolean hasParent(int index) {return getParentIndex(index) >= 0;};
		
		private int leftChild(int index) {return items[getLeftChildIndex(index)];};
		private int rightChild(int index) {return items[getRightChildIndex(index)];};
		private int parent(int index) {return items[getParentIndex(index)];};
		
		private void swap(int index1, int index2) {
			int tempItem1 = items[index1];
			items[index1] = items[index2];
			items[index2] = tempItem1;
		}
		
		private void ensureExtraCapacity() {
			if (size == capacity) {
				items = Arrays.copyOf(items, 2 * capacity);
				capacity *= 2;
			}
		}
		
		public int peek() {
			if (size == 0) throw new IllegalStateException();
			return items[0];
		}
		
		// removed minimum element
		public int poll() {
			if (size == 0) throw new IllegalStateException();

			int temp = items[0];
			items[0] = items[size - 1];
			size --;
			heapifyDown();
			return temp;
		}
		
		public void add(int item) {
			ensureExtraCapacity();
			items[size] = item;
			size ++;
			heapifyUp();
		}
		
		
		// bubble up
		private void heapifyUp() {
			int index = size - 1;
			while(hasParent(index) && items[index] < parent(index)) {
				swap(index, getParentIndex(index));
				index = getParentIndex(index);
			}
		}
		
		// bubble down
		private void heapifyDown() {
			int index = 0;
			// only need to check left child, if there's no left child, then there's no right child
			while(hasLeftChild(index)) {
				int smallerChildIndex = getLeftChildIndex(index);
				if(hasRightChild(index) && rightChild(index) < leftChild(index)) {
					smallerChildIndex = getRightChildIndex(index);
				}
				
				if(items[index] < items[smallerChildIndex]) {
					break;
				} else {
					swap(index, smallerChildIndex);
				}
				
				index = smallerChildIndex;

			}
		}
		
		 public void printArray() {
			 for(int i : items) {
				 System.out.println(i);
			 }
		 }

	}

}
