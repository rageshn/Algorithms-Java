package algorithms.sorting;

/*
 * Algorithm:
 * ----------
 * 
 *  C = output array (length = n)
 *	A = First sorted array (length = n/2)
 *	B = Second sorted array (length = n/2)
 *
 *  set i = 1 and j = 1
 *  
 *  for k = 1 to n
 *    	if A[i] < B[j]
 *       	C[k] = A[i]
 *       	i++
 *   	elif B[j] < A[i]
 *        	C[k] = B[j]
 *        	j++
 *	end
 */

public class Mergesort {
	
	
	public void merge(int[] array) {
		int arrLength = array.length;
		if(arrLength > 1) {
			int mid = arrLength/2;
			int index = 0;
			int[] first_half = new int[mid];
			int[] second_half = new int[arrLength-mid];
			for(int i=0; i< mid; i++)
				first_half[i] = array[i];
			for(int j=mid; j<arrLength; j++)
			{
				second_half[index] = array[j];
				index++;
			}
			
			merge(first_half);
			merge(second_half);
			
			int i = 0; 
			int j = 0;
			int k = 0;
			
			while(i < first_half.length && j < second_half.length) 
			{
				if(first_half[i] < second_half[j])
				{
					array[k] = first_half[i];
					i++;
				}
				else {
					array[k] = second_half[j];
					j++;
				}
				k++;
			}
			
			while(i < first_half.length)
			{
				array[k] = first_half[i];
				i++;
				k++;
			}
			
			while(j<second_half.length) 
			{
				array[k] = second_half[j];
				j++;
				k++;
			}
			
		}
		System.out.println("Merging: ");
		for(int x : array)
			System.out.print(x);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Mergesort m = new Mergesort();
		int[] arrayToSort = new int[] {4,7,2,1,8,0,6,3,5,9};
		m.merge(arrayToSort);
	}

}
