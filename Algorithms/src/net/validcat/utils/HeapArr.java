package net.validcat.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class HeapArr<T extends Comparable<T>> implements Iterable<Comparable<T>>, Iterator<Comparable<T>> {
	private T[] heap;
	private int position;
	
	public HeapArr(int n) {
//		heap =  (T[]) Array.newInstance(Comparable.class, n);
		heap =  (T[])(new Comparable[n]);
	}

	public void insert(T t) {
		int tInd = heap.length;
		int pInd = (tInd % 2 == 0 && tInd != 0 ? tInd-1 : tInd)/2;
		heap[pInd] = t;
		T p = (T) heap[pInd];
		
		while (t.compareTo(p) == -1) {
			heap[pInd] = t;
			heap[tInd] = p;
			tInd = pInd;
			pInd = tInd/2;
			p = (T) heap[pInd];
		}
	}
	
	public T extractMin() {
		int pInd = 0;
		T returnValue = (T) heap[pInd];
		Comparable<T> p = (Comparable<T>) heap[heap.length-1];
		heap[heap.length-1] = null;
		if (heap.length == 0) {
			return returnValue;
		}
		heap[pInd] = (T) p;
//		heap.remove(pInd);
//		heap.add(pInd, p);
		int tInd = getTInd(pInd);

		Comparable<T> t = (Comparable<T>) heap[tInd];
		
		while (t.compareTo((T) p) == -1 && (2*pInd)+1 < heap.length-1) {
//			heap.remove(tInd);
//			heap.remove(pInd);
			heap[pInd]= (T) t;
			heap[tInd]= (T) p;
			
			pInd = tInd;
			tInd = getTInd(pInd);
			t = heap[tInd];
		}
		
		return returnValue;
	}
	
	private int getTInd(int pInd){
		return 2*pInd+1 > heap.length-1 ? 0 : 2*(pInd+1) > heap.length-1  
				? 2*pInd + 1 : heap[(2*pInd)+1].compareTo((T) heap[2*(pInd+1)]) != -1 
						? 2*(pInd+1) : 2*pInd+1;
	}

	public boolean isEmpty() {
		if (heap.length == 0) return true;
		else return false;
	}

	@Override
	public boolean hasNext() {
		if (position >= heap.length || heap[position] == null) return false;
		else return true;
	}

	@Override
	public Comparable<T> next() {
		return heap[position++];
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<Comparable<T>> iterator() {
		position = 0;
		return this;
	}

	public void remove(T t) {
		for (int i = 0; i < heap.length; i++) {
			if (heap[i] == t) heap[i] = null;  
		}
	}
	
//	public void removeAll(List<T> temp) {
//		for (int i = 0; i < temp.size(); i++) {
//			
//		}
//		heap.removeAll(temp);
//	}
	

}
