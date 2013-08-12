package net.validcat.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings("unchecked")
public class HeapMin<T extends Comparable<T>> implements Iterable<Comparable<T>>, Iterator<Comparable<T>> {
	private List<Comparable<T>> heap;
	private int position;
	
	public HeapMin() {
		heap = new ArrayList<Comparable<T>>();
	}

	public void insert(T t) {
		heap.add(t);
		int tInd = heap.size()-1;
		int pInd = (tInd % 2 == 0 && tInd != 0 ? tInd-1 : tInd)/2;
		T p = (T) heap.get(pInd);
		
		while (t.compareTo(p) == -1) {
			heap.set(pInd, t);
			heap.set(tInd, p);
			tInd = pInd;
			pInd = (tInd % 2 == 0 && tInd != 0 ? tInd-1 : tInd)/2;
			p = (T) heap.get(pInd);
		}
	}
	
	public T extractMin() {
		int pInd = 0;
		T returnValue = null;
		try {
			returnValue = (T) heap.get(pInd);
		} catch (Exception e) {
			return null;
		}
		Comparable<T> p = (Comparable<T>) heap.get(heap.size()-1);
		heap.remove(heap.size()-1);
		if (heap.size() == 0) {
			return returnValue;
		}
		heap.set(pInd, p);
		int tInd = getTInd(pInd);

		Comparable<T> t = (Comparable<T>) heap.get(tInd);
		
		while (t.compareTo((T) p) == -1 && (2*pInd)+1 <= heap.size()-1) {
			heap.set(pInd, t);
			heap.set(tInd, p);
			
			pInd = tInd;
			tInd = getTInd(pInd);
			t = heap.get(tInd);
		}
		
		return returnValue;
	}
	
	private int getTInd(int pInd){
		return 2*pInd+1 > heap.size()-1 ? 0 : 2*(pInd+1) > heap.size()-1  
				? 2*pInd + 1 : heap.get((2*pInd)+1).compareTo((T) heap.get(2*(pInd+1))) != -1 
						? 2*(pInd+1) : 2*pInd+1;
	}

	public boolean isEmpty() {
		if (heap.size() == 0) return true;
		else return false;
	}

	@Override
	public boolean hasNext() {
		if (position >= heap.size() || heap.get(position) == null) return false;
		else return true;
	}

	@Override
	public Comparable<T> next() {
		return heap.get(position++);
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

	public void removeAll(List<T> temp) {
		heap.removeAll(temp);
	}
	
	public T getMin() {
		return (T) heap.get(0);
	}
	
	public int size() {
		return heap.size();
	}
	
	public static void main(String[] args) {
		Integer[] a = {1,6,4,7,2,10,9,8,3,5,11};
		HeapMin<Integer> heap = new HeapMin<Integer>();
		for (int i = 0; i < a.length; i++) {
			heap.insert(a[i]);
		}
		
		for (int i = 0; i < a.length; i++) {
			System.out.println(heap.extractMin());
		}
		
	}
	

}
