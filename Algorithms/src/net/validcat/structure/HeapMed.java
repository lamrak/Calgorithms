package net.validcat.structure;

import java.io.IOException;

import net.validcat.utils.Utils;

/**
 * Added a new data structure collection: HeapMid - always 
 * return a median value from the input data. 
 * Example: input data: 1,3,5; Return value 3. 
 * Realization is based on the two heaps - HeapMin and HeapMax.
 * @author Oleksii Dobrunov
 *
 */
public class HeapMed<T extends Comparable<T>> implements IBinaryHeap<T> {
	private static final long serialVersionUID = -2780946692238382214L;
	HeapMax<T> heapMax; //...1,2,5,6,10
	HeapMin<T> heapMin; //11,14,15,16...
	
	public HeapMed() {
		heapMax = new HeapMax<T>();
		heapMin = new HeapMin<T>();
	}

	@Override
	public void insert(Comparable<T> t) {
		if (heapMax.size() == 0) {
			heapMax.insert(t);
			return;
		}
		//insert a value in one of the heaps
		if (t.compareTo((T) heapMax.get()) > 0) heapMin.insert(t); // t > heapMax.get()
		else heapMax.insert(t);
		//balance
		balance();
	}
	
	private void balance() {
		if (heapMin.size() - heapMax.size() > 1) heapMax.insert(heapMin.extract());
		else if (heapMax.size() - heapMin.size() > 1) heapMin.insert(heapMax.extract());
	}

	@Override
	public T extract() {
		//Wich an element k/2 or (k+1)/2 we take
		int size = heapMin.size() + heapMax.size();
		int index = size % 2 == 0 ? size/2 : (size+1)/2;
		return (heapMax.size() < index) ? heapMin.get() : heapMax.get();
	}

	@Override
	public T get() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		return heapMin.size() + heapMax.size();
	}

	@Override
	public boolean isEmpty() {
		return heapMin.isEmpty() && heapMax.isEmpty();
	}
	
	public static void main(String[] args) throws IOException {
		int[] a = Utils.readArrayFromFile("Median.txt", 10000);
		long mediance = 0;
		long start = System.currentTimeMillis();
		HeapMed<Integer> heapMed = new HeapMed<Integer>();
		for (int i = 0; i < a.length; i++) {
			heapMed.insert(a[i]);
			mediance += heapMed.extract();
		}
		System.out.println(mediance); // correct: 4683...
		System.out.println(System.currentTimeMillis()- start);
	}

}
