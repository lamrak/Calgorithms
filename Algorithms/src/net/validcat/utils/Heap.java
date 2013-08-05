package net.validcat.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Heap<T extends Comparable> implements Iterable<Comparable>, Iterator<Comparable> {
	private List<Comparable> heap;
	private int position;
	
	public Heap() {
		heap = new ArrayList<Comparable>();
	}

	public void insert(Comparable t) {
		heap.add(t);
		int tInd = heap.size()-1;
		int pInd = (tInd % 2 == 0 && tInd != 0 ? tInd-1 : tInd)/2;
		Comparable p = (Comparable) heap.get(pInd);
		
		while (t.compareTo(p) == -1) {
			heap.remove(pInd);
			heap.add(pInd, t);
			heap.remove(tInd);
			heap.add(tInd, p);
			
			tInd = pInd;
			pInd = tInd/2;
			p = heap.get(pInd);
		}
	}
	
	public Object extractMin() {
		int pInd = 0;
		Object returnValue = heap.get(pInd);
		Comparable p = (Comparable) heap.get(heap.size()-1);
		heap.remove(heap.size()-1);
		if (heap.size() == 0) {
			return returnValue;
		}
		heap.remove(pInd);
		heap.add(pInd, p);
		int tInd = getTInd(pInd);

		Comparable t = (Comparable) heap.get(tInd);
		
		while (t.compareTo(p) == -1 && (2*pInd)+1 < heap.size()-1) {
			heap.remove(tInd);
			heap.remove(pInd);
			heap.add(pInd, t);
			heap.add(tInd, p);
			
			pInd = tInd;
			tInd = getTInd(pInd);
			t = heap.get(tInd);
		}
		
		return returnValue;
	}
	
	private int getTInd(int pInd){
		return 2*pInd+1 > heap.size()-1 ? 0 : 2*(pInd+1) > heap.size()-1  
				? 2*pInd + 1 : heap.get((2*pInd)+1).compareTo(heap.get(2*(pInd+1))) != -1 
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
	public Comparable next() {
		return heap.get(position++);
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<Comparable> iterator() {
		position = 0;
		return this;
	}

	public void removeAll(List<T> temp) {
		position = position - temp.size()-1;
		heap.removeAll(temp);
	}
	

}
