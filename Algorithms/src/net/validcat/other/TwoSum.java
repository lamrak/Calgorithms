package net.validcat.other;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.validcat.utils.Utils;

public class TwoSum {
	private static long t;
	
	public static void main(String[] args) throws IOException {
		List<Long> a = Utils.readLongListFromFile("2sum.txt", 1000000);

		Set<Long> set = new HashSet<Long>(a);
		System.out.println(set.size());
		for (long j = -10000; j <= 10000; j++) {
			if (checkSet(set, j)) t++;
			System.out.println("j: " + j + " t: " + t);
		} 
		System.out.println("Output" + t);
	}
	
	public static boolean checkSet(Set<Long> set, long j) {
		for (long i : set) {
			if (set.contains(j - i)) return true;
		}
		return false;
	}
	
	public static void findAllPairsWithSumX(long[] a, int x) {
		Arrays.sort(a);
	    for (int i = 0; i < a.length - 1; i++) {
	        long tofind = x - a[i];
	        long returned = Arrays.binarySearch(a, i + 1, a.length - 1, tofind);
	        if (returned > 0) {
	        	t++;
	            System.out.println("Sum " + x + " is found, values the making sum are");
	        }
	    }
	}

}
