package net.validcat.sort;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import net.validcat.utils.Utils;

public class Quick {
	private static int[] array; 
	
	public void sort(int[] a) {
		partition(0, a.length-1);
	}
	
	private void partition(int l, int r) {
		if (l >= r) return;
		int i = l+1;
		int j=l+1;
		for (int p = getMedianOfThreeAsPivot(l, r); j <= r; j++) 
			if (array[j] < p) swap(i++, j); // array[j] > p do nothing
		swap(l, i-1);
		partition(l, i-2);
		partition(i, j-1);
	}
	
	@SuppressWarnings("unused")
	private int getLastElementAsPivot(int l, int r) {
		swap(l, r);
		return array[l];
	}

	@SuppressWarnings("unused")
	@Deprecated
	private int getRandomElementAsPivot(int l, int r) { //TODO Fix it!!!!
		Random random = new Random();
		return array[(int)(((long)(((long)r - (long)l + 1) * random.nextDouble())) + l)];  
	}
	
	@SuppressWarnings("unused")
	private int getFirstElementAsPivot(int l, int r) {
		return array[l];
	}
	
	private int getMedianOfThreeAsPivot(int l, int r) {
		int pivotIndex = -1;
		int mid = ((r - l) / 2) + l;
		int b[] = {array[l], array[mid], array[r]};

		Arrays.sort(b);
		if (b[1] == array[l]) 		 pivotIndex = l;
		else if (b[1] == array[mid]) pivotIndex = mid;
		else 						 pivotIndex = r;
		swap(l, pivotIndex);

		return array[l];
	}
	
	private void swap(int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public static void main(String[] args) throws IOException {
		Quick qs = new Quick();
		array = Utils.readArrayFromFile("QuickSort.txt", 10000);
		qs.sort(array);
		System.out.println(Arrays.toString(array));
	}

}
