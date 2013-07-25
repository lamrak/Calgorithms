package net.validcat.mergesort;

import java.io.IOException;
import java.util.Arrays;

import net.validcat.utils.Utils;

public class CountInversions {
	static long result;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CountInversions ms = new CountInversions();
		try {
			int[] inputArrr = Utils.readArrayFromFile("D:\\Mine\\Algorithmes\\IntegerArray.txt", 100000);
			ms.countAndSort(inputArrr);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private int[] countAndSort(int[] input){
		if (input.length <= 1) {
			return input;
		}
		
		int[] right = countAndSort(Arrays.copyOfRange(input, input.length/2, input.length));
		int[] left = countAndSort(Arrays.copyOfRange(input, 0, input.length/2));

		int[] output = countAndMerge(new int[input.length], left, right);

		return output;
	}
	
	private int[] countAndMerge(int[] output, int left[], int right[]){
		int i = 0, j = 0;
		for (int k = 0; k < output.length; k++) {
			if (i == left.length) {
				output[k] = right[j++];
				continue;
			} 
			if (j == right.length) {
				output[k] = left[i++];
				continue;
			}
			
			if (left[i] < right[j]) {
				output[k] = left[i++];
			} else {
				result += left.length - i;
				output[k] = right[j++];
			}
		}
		
		return output;
		
//		if (left[i] < right[j]) {
//			output[z++] = left[i++];
//			if (i == left.length) return addRest(j, z, output, right);
//		} else {
//			output[z++] = right[j++];
////			result++;
//			if (j == right.length) {
////				result += left.length - i;
//				return addRest(i, z, output, left);
//			}
//		}
		
//		return countAndMmerge(i, j, z, output, left, right);
	}
	
//	private int countSplit(int z, int i, int j, int[] left, int[] right){
//		if (left[i] > right[j]) {
//			z++;
//			i++;
//			if (left.length == i) return z; 
//		} else {
//			j++;
//			if (right.length == j) return z;
//		}
//		return countSplit(z, i, j, left, right);
//	}
//	
//	private int[] addRest(int arrIndex, int outIndex, int[] output, int[] array) {
//		for (int k = arrIndex; k < array.length; k++) {
//			output[outIndex++] = array[k];
//		}
//		return output;
//	}

}
