package net.validcat.structure;

import java.util.Comparator;
import java.util.Iterator;

abstract class AbstractHeap<T extends Comparable<T>> implements IBinaryHeap<T>, Iterable<T>, Iterator<T> {
	private transient Object[] heap;
	private int position;
	private int size = 0;
	private Comparator<T> c;
	
	public AbstractHeap() {
		heap = new Object[10];
	}

	public AbstractHeap(int capacity) {
		heap = new Object[capacity];
	}

	/**
	 * Insert value in the heap.
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
		
		while (compare(t, p)) {
			heap[pInd] = t;
			heap[tInd] = p;
			tInd = pInd;
			pInd = (tInd % 2 == 0 && tInd != 0 ? tInd-1 : tInd)/2;
			p = (T) heap[pInd];
		}
	}
	
	abstract boolean compare(Comparable<T> t, Comparable<T> p);

	/**
	 * Extract a min or max value from heap. Value will be eliminated from this heap.
	 * 
	 * If you want to get value without deleting, look at <code>get()</code>
	 * @return min or max value from this heap or null if heap is empty
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
		while (compare(t, p) && (2*pInd)+1 <= size-1) {
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
				? 2*pInd + 1 : !compare((T) heap[(2*pInd)+1], (T) heap[2*(pInd+1)]) 
						? 2*(pInd+1) : 2*pInd+1;
	}
	
	/**
	 * Get a min or max value from this heap without deleting it. 
	 * If you want to extract value from the heap with eliminating, look at <code>extract()</code>
	 * @return min or max value from this heap or null if heap is empty
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
	
	public void setComparator(Comparator<T> c) {
		this.c = c;
	}
	
	public Comparator<T> getComparator() {
		return c;
	}

	@Override
	public boolean hasNext() {
		if (position >= size || heap[position] == null) return false;
		else return true;
	}

	@Override
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

	/**
	 * Resize an array when capacity is not enough for insertions values.
	 * Shrink if array's capacity is excess.  
	 * @param newLength
	 */
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
	
	//TODO Fixed capacity
}
