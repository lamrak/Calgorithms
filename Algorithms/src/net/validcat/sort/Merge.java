package net.validcat.sort;

import java.io.IOException;
import java.util.Arrays;

public class Merge {
	private static int inversion;
	
	public static int[] sort(int[] input){
		if (input.length <= 1) return input;
		
		int right[] = sort(Arrays.copyOfRange(input, input.length/2, input.length));
		int left[] = sort(Arrays.copyOfRange(input, 0, input.length/2));
		
		return merge(new int[input.length], left, right);
	}
	
	private static int[] merge(int[] output, int left[], int right[]){
		for (int i = 0, j = 0, k = 0; k < output.length; k++) {
			if (i == left.length) {
				output[k] = right[j++];
				continue;
			} 
			if (j == right.length) {
				output[k] = left[i++];
				continue;
			}
			if (left[i] < right[j]) output[k] = left[i++];
			else {
				inversion += left.length - i;
				output[k] = right[j++];
			}
		}
		
		return output;
	}
	
	public static int getInversion() {
		return inversion;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		int[] a = {5, 3, 8, 9, 1, 7, 0, 2, 6, 4};
		System.out.println(Arrays.toString(Merge.sort(a)));
		System.out.println(Merge.getInversion());
	}

	
}
