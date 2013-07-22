package net.validcat.quicksort;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import net.validcat.utils.Utils;

public class QuickSort {
	private static long comparations = 0;
	private static int[] array = new int[10000]; 

	/**
	 * QuickSort algorithms
	 * @param no input params
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		QuickSort qs = new QuickSort();
		array = Utils.readArrayFromFile("D:\\odobrunov\\Algorithms\\QuickSort.txt", 10000);
		qs.partition(0, array.length-1);
		System.out.println(String.valueOf(comparations));
		System.out.println(Arrays.toString(array));
	}
	
	private void partition(int l, int r) {
		if (l >= r) {
			return;
		}
		comparations += r-l;
		int p = getMedianOfThreeAsPivot(l, r);
		int i = l+1;
		int j=l+1;
		for (; j <= r; j++) {
			if (array[j] < p) { // array[j] > p do nothing
				swap(i, j);
				i++;
			}
		}
		swap(l, i-1);
		partition(l, i-2);
		partition(i, j-1);
	}
	
	public int getLastElementAsPivot(int l, int r) {
		swap(l, r);
		return array[l];
	}
	
	public int getRandomElementAsPivot(int l, int r) {
		Random random = new Random();
		return array[(int)(((long)(((long)r - (long)l + 1) * random.nextDouble())) + l)];  
	}
	
	public int getFirstElementAsPivot(int l, int r) {
		return array[l];
	}
	
	public int getMedianOfThreeAsPivot(int l, int r) {
		int pivotIndex = -1;
		int mid = ((r - l) / 2) + l;
		int b[] = new int[3];
		b[0] = array[l];
		b[1] = array[mid];
		b[2] = array[r];

		Arrays.sort(b);
		if (b[1] == array[l]) {
			pivotIndex = l;
		} else if (b[1] == array[mid]) {
			pivotIndex = mid;
		} else {
			pivotIndex = r;
		}
		swap(l, pivotIndex);

		return array[l];
	}
	
	private void swap(int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

}
