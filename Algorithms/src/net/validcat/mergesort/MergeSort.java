package net.validcat.mergesort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class MergeSort {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		MergeSort ms = new MergeSort();
		int[] a = {5, 3, 8, 9, 1, 7, 0, 2, 6, 4};
		System.out.println(Arrays.toString(ms.divide(a)));
	}
	
	private int[] readFile() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("D:\\Mine\\Algorithmes\\IntegerArray.txt"));
		int[] inputArray = new int[100000];
		int i = 0;
		String line;
		while ((line = br.readLine()) != null) {
			inputArray[i++] = Integer.parseInt(line);
		}
		br.close();
		
		return inputArray;
	}
	
	private int[] divide(int[] input){
		if (input.length <= 1) {
			return input;
		}
		
		int right[] = divide(Arrays.copyOfRange(input, input.length/2, input.length));
		int left[] = divide(Arrays.copyOfRange(input, 0, input.length/2));

		int[] output = merge(new int[input.length], left, right);

		return output;
	}
	
	private int[] merge(int[] output, int left[], int right[]){
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
				output[k] = right[j++];
			}
		}
		
		return output;
	}
	
}
