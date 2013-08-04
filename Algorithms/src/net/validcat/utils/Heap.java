package net.validcat.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.validcat.models.Edge;
import net.validcat.models.IHeapObject;

public class Heap implements Iterable<IHeapObject>, Iterator<IHeapObject> {
	private List<IHeapObject> heap;
	private int position;
	
	public Heap() {
		heap = new ArrayList<IHeapObject>();
	}

	public void insert(IHeapObject t) {
		int tInd = heap.size();
		int pInd = tInd/2;
		heap.add(t);
		IHeapObject p = heap.get(pInd);
		
		while (t.getKey() < p.getKey()) {
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
		IHeapObject p = heap.get(heap.size());
		heap.add(pInd, p);
		
		int tInd = (heap.get(2*pInd+1) == null || heap.get(2*pInd).getKey() < heap.get(2*pInd+1).getKey()) ? 2*pInd : (2*pInd+1); 
		IHeapObject t = heap.get(tInd);
		
		while (p.getKey() > t.getKey() && 2*pInd < heap.size()) {
			heap.add(pInd, t);
			heap.add(tInd, p);
			
			pInd = tInd;
			tInd = (2*pInd+1 < heap.size() && heap.get(2*pInd).getKey() < heap.get(2*pInd+1).getKey()) ? 2*pInd : (2*pInd+1);
			t = heap.get(tInd);
		}
		
		return returnValue;
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
	public IHeapObject next() {
		return heap.get(position++);
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<IHeapObject> iterator() {
		return this;
	}

	public void removeAll(List<Edge> temp) {
		heap.removeAll(temp);
	}
	

}
