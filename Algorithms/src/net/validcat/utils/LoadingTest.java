package net.validcat.utils;

import net.validcat.sort.Merge;

public class LoadingTest {
	
	public static double timeTrial(int N) {
		int MAX = 10000;
		int[] a = new int[N];
		for (int i = 0; i < N; i++) 
			a[i] = StdRandom.uniform(-MAX, MAX);
		Stopwatch timer = new Stopwatch();
		
		a = Merge.sort(a);
		
		return timer.stopTimer();
	}
	
	public static void main(String[] args) {
		for (int i = 250; true; i += i) {
			double time = timeTrial(i);
			System.out.printf("%7d %5.1f\n", i, time);
		}
	}

}
