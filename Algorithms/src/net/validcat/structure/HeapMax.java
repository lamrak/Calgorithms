package net.validcat.structure;

import java.util.Iterator;
import net.validcat.interfaces.Heap;

public class HeapMax<T extends Comparable<T>> implements Heap<T> {
	private static final long serialVersionUID = -6904735977968832161L;
	private transient Object[] heap;
	private int position;
	private int size = 0;
	
	public HeapMax() {
		heap = new Object[10];
	}

	public HeapMax(int capacity) {
		heap = new Object[capacity];
	}

	/**
	 * Insert value in heap.
	 *  
	 * @see <code>extract()</code>
	 */
	@SuppressWarnings("unchecked")
	public void insert(Comparable<T> t) {
		if (size >= heap.length) resize(heap.length*2);
		rangeCheckForAdd(size);
		heap[size++] = t;
		int tInd = size-1;
		int pInd = (tInd % 2 == 0 && tInd != 0 ? tInd-1 : tInd)/2;
		T p = (T) heap[pInd];
		
		while (p.compareTo((T) t) == -1) {
			heap[pInd] = t;
			heap[tInd] = p;
			tInd = pInd;
			pInd = (tInd % 2 == 0 && tInd != 0 ? tInd-1 : tInd)/2;
			p = (T) heap[pInd];
		}
	}
	
	/**
	 * Extract a minimum value from heap. Value will be eliminated from this heap.
	 * 
	 * If you want to get value without deleting, look at <code>get()</code>
	 * @return minimum value from this heap or null if heap is empty
	 * @see <code>isEmpty()</code>, <code>get()</code>
	 */
	@SuppressWarnings("unchecked")
	public T extract() {
		if (size == 0) return null;
		int pInd = 0;
		T returnValue = (T) heap[pInd];
		Comparable<T> p = (Comparable<T>) heap[size-1];
		heap[--size] = null;

		if (size <= heap.length/4) resize(heap.length/2);
		if (size == 0) return returnValue;

		heap[pInd] = p;
		int tInd = getTInd(pInd);

		Comparable<T> t = (Comparable<T>) heap[tInd];
		while (p.compareTo((T) t) == -1 && (2*pInd)+1 <= size-1) {
			heap[pInd] = t;
			heap[tInd] = p;
			pInd = tInd;
			tInd = getTInd(pInd);
			t = (T) heap[tInd];
		}
		
		return returnValue;
	}
	
	@SuppressWarnings("unchecked")
	private int getTInd(int pInd){
		return 2*pInd+1 > size-1 ? 0 : 2*(pInd+1) > size-1  
				? 2*pInd + 1 : ((T) heap[2*(pInd+1)]).compareTo((T) heap[(2*pInd)+1]) != -1 
						? 2*(pInd+1) : 2*pInd+1;
	}
	
	/**
	 * Get a minimum value from this heap without deleting it. 
	 * If you want to extract minimum value from the heap with eliminating, look at <code>extract()</code>
	 * @return minimum value from this heap or null if heap is empty
	 * @see <code>extract()</code>, <code>isEmpty()</code>
	 */
	@SuppressWarnings("unchecked")
	public T get() {
		if (size == 0) return null;
		else return (T) heap[0];
	}
	
	/**
	 * Returns the number of elements in this Heap.
	 * @return the number of elements in this Heap.
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns whether this Heap contains no elements.
	 * @return <code>true</code> if this heap has no elements, false otherwise.
	 * @see <code>size()</code>; 
	 */
	public boolean isEmpty() {
		if (size == 0) return true;
		else return false;
	}

	public boolean hasNext() {
		if (position >= size || heap[position] == null) return false;
		else return true;
	}

	@SuppressWarnings("unchecked")
	public T next() {
		int i = position++;
		return (T) heap[i];
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<T> iterator() {
		position = 0;
		return this;
	}

	private void resize(int newLength) {
		Object[] b = new Object[newLength];
		for (int i = 0; i < size; i++) 
			b[i] = heap[i];
		heap = b;
	}

	private void rangeCheckForAdd(int index) {
		if (index < 0 || index > this.size)
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	}
		
	private String outOfBoundsMsg(int index) {
		return "Index: " + index + ", Size: " + this.size;
	}
	
	public static void main(String[] args) {
		Integer[] a = {1,6,4,7,2,10,9,8,3,5};
		HeapMax<Integer> heap = new HeapMax<Integer>();
		// test insert
		for (int i = 0; i < a.length; i++) {
			heap.insert(a[i]);
		}
		// test comparator
//		for (Integer item : heap) {
//			System.out.println(item);
//		}
		System.out.println("Output");
		// test extract
		for (int i = 0; i < a.length; i++) {
			System.out.println(heap.extract());
		}
	}

}