package net.validcat.structure;

public class HeapMin<T extends Comparable<T>> extends AbstractHeap<T> implements java.io.Serializable  { //implements Heap<T>, Iterable<T>, Iterator<T>
	private static final long serialVersionUID = 4909408597188159120L;
	
	public HeapMin() {
		super();
	}

	public HeapMin(int capacity) {
		super(capacity);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean compare(Comparable<T> t, Comparable<T> p) {
		if (getComparator() != null) return (getComparator().compare((T) t, (T) p) < 0);
		else return t.compareTo((T) p) < 0 ? true : false; 
	}
	
	public static void main(String[] args) {
		Integer[] a = {1,6,4,7,2,10,9,8,3,5};
		HeapMin<Integer> heap = new HeapMin<Integer>();
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