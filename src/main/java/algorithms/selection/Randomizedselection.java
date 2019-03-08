package algorithms.selection;

/*
 * 
 * Also known as Quick Select algorithm.
 * 
 * Goal: In an array with n distinct elements, return the k'th order statistics i.e., k'th smallest element in an array.
 * 
 * Algorithm:
 * -----------
 * RandomizedSelection(array A, left, right, statistics i)
 *    if left = right
 *       return A[1eft]
 *    Choose pivot P from A uniformly at random
 *    Partition A around P
 *       Let j = new index of P
 *    if j == i
 *       return A[P]
 *    else if i < j
 *       right := j-1
 *       return RandomizedSelection(1st part of A, left, right, i)
 *    else
 *       left := j+1
 *       return RandomizedSelection(2nd part of A, left, right, i - P + left - 1)
 *
 *
 * Partition(array A, left, right)
 *    pivot = arr[right]
 *    i = left - 1
 *    for j = left to right
 *       if A[j] < pivot
 *           swap(A[i], A[j])
 *           i++
 *    swap(A[i+1], A[r])
 *    return i+1 
 * 
 * 
 * Time Complexity: O(n)
 * Worst case: O(n^2)
 * 
 */

public class Randomizedselection {
	
	public int partition(int[] A, int leftIndex, int rightIndex) {
		int pivot = A[rightIndex];
		int i = leftIndex - 1;
		for(int j = leftIndex; j<rightIndex; j++) {
			if(A[j] < pivot) {
				i++;
				int temp = A[i];
				A[i] = A[j];
				A[j] = temp;
			}
		}
		int temp1 = A[i+1];
		A[i+1] = A[rightIndex];
		A[rightIndex] = temp1;
		return i+1;
	}
	
	public int RandomizedSelection(int[] A, int leftIndex, int rightIndex, int k) {
		if(leftIndex == rightIndex) {
			return A[leftIndex];
		}
		int pivot = partition(A, leftIndex, rightIndex);
		if(pivot == k) {
			return A[pivot];
		}
		else if(k < pivot) {
			int right = pivot -1;
			return RandomizedSelection(A, leftIndex, right, k);
		}
		else {
			int left = pivot + 1;
			return RandomizedSelection(A, left, rightIndex, k - pivot + left - 1);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] inputArray = new int[] {7,8,4,1,6,0,5,9,2,3};
		int k = 15;
		int length = inputArray.length;
		if(k > length) {
			System.out.println("Index should be less than the array size");
			System.exit(0);
		}
		Randomizedselection RSelect = new Randomizedselection();
		int value = RSelect.RandomizedSelection(inputArray, 0, length-1, k);
		System.out.println(value);

	}

}
