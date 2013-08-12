package net.validcat.datastruct;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TwoSum {
	private static long t;
	
	public static void main(String[] args) {
		
		//TODO read file
		int[] a = new int[100];
		
		for (int i = 0; i < args.length; i++) {
			for (int j = i; j < args.length; j++) {
				if (a[i] + a[j] >= -10000 && a[i] + a[j] <= 10000) t++;
			}
		}

		int total = 9;
		Integer[] S = {1, 4, 7, 2, 3, 4, 6};
		Set<Integer> set = new HashSet<Integer>(Arrays.asList(S));
		for (int i : set)
		    if (set.contains(total - i))
		        System.out.println(i + " + " + (total - i) + " = " + total);
		
		}
	
//	int total = 9;
//	Integer[] S = {1, 4, 7, 2, 3, 4, 6};
//	Set<Integer> set = new HashSet<Integer>(Arrays.asList(S));
//	for (int i : set)
//		for (int j : total)
//			if (set.contains(j - i)) t++;
//				System.out.println(i + " + " + (total - i) + " = " + total);
//	
//	}	

}
