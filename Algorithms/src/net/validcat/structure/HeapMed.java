package net.validcat.structure;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import net.validcat.utils.Utils;

public class HeapMed {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		int[] a = Utils.readArrayFromFile("Median.txt", 10000);
//		int[] a = {2,1,3,4,9,8,6,5,7};
//		int[] m = new int[10000];
		long mediance = 0;
		long medianceShort = 0;

		long start = System.currentTimeMillis();
		//heap realization
		HeapMax<Integer> heapMax = new HeapMax<Integer>();
		HeapMin<Integer> heapMin = new HeapMin<Integer>();
		for (int i = 0; i < a.length; i++) {
			int value = a[i];
			if (heapMax.size() == 0) {
				heapMax.insert(value);
				mediance += value;
				continue;
			}
			//insert a value in one of the heaps
			if (value > heapMax.getMax()) heapMin.insert(value);
			else heapMax.insert(value);
			
			//balance
			if (heapMin.size() - heapMax.size() > 1) heapMax.insert(heapMin.extract());
			else if (heapMax.size() - heapMin.size() > 1) heapMin.insert(heapMax.extractMax());
			
			//Wich an element k/2 or (k+1)/2 we take
			int size = heapMin.size() + heapMax.size();
			int index = size % 2 == 0 ? size/2 : (size+1)/2;
			//count
			mediance += (heapMax.size() < index) ? heapMin.get() : heapMax.getMax();
		}
		
		System.out.println(mediance);
		System.out.println(System.currentTimeMillis()- start);
		//To compare realization without heap
		start = System.currentTimeMillis();
		ArrayList<Integer> med = new ArrayList<Integer>();
		for (int i = 0; i < a.length; i++) {
			med.add(a[i]);
			Integer[] arr = med.toArray(new Integer[0]);
			Arrays.sort(arr);
			if (arr.length % 2 == 0) {
				medianceShort += arr[(arr.length/2)-1];
			} else {
				medianceShort += arr[((arr.length+1)/2)-1];
			}
		}
		System.out.println(medianceShort);
		System.out.println(System.currentTimeMillis()- start);

	}

}
