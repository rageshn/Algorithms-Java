package algorithms.sorting;

/*
 * Algorithm:
 * -----------
 * 1. Choose a random element as pivot
 * 2. Partition the items is array w.r.t. pivot, so that all elements in left are smaller than pivot and elements to right is greater than pivot.
 * 3. Recursively loop the two halves and do the same operation.
 * 
 * Note: Partitioning puts the pivot in the correct index where it should be in the final array.
 * 
 * Quicksort(A, l, r)
 * 		if l < r
 * 			Partition A around Pivot
 * 			Recursively sort the first half
 * 			Recursively sort the second half
 * 
 * Partition (A, l ,r)
 * 		i = l - 1
 * 		P = A[r]
 * 		for j = l to r:
 * 			if A[j] > P
 * 				do nothing
 * 			if A[j] < P
 * 				i++
 * 				Swap A[j] and A[i]
 * 		Swap A[i+1] and A[r]
 * 		return i+1
 * 
 * Time complexity: O(nlog(n))
 * Space complexity: O(log(n))
 * 
 */

public class Quicksort {
	
	public void quicksort(int[] array, int low, int high) {
		if(low < high) {
			int p = partition(array, low, high);
			quicksort(array, low, p-1);
			quicksort(array, p+1, high);
		}
	}
	
	public int partition(int[] array, int low, int high) {
		int i = low - 1;
		int P = array[high];
		for(int j = low; j < high; j++) {
			if(array[j] < P) {
				i++;
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
			}
		}
		int temp1 = array[i+1];
		array[i+1] = array[high];
		array[high] = temp1;
		
		return i+1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Quicksort sq = new Quicksort();
		int[] arrayToSort = new int[] {5,7,2,3,9,0,1,8,4,6};
		sq.quicksort(arrayToSort, 0, arrayToSort.length-1);
		for(int i=0;i<arrayToSort.length;i++) {
			System.out.println(arrayToSort[i]);
		}

	}

}
