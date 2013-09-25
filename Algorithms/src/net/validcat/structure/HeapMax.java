package net.validcat.structure;

public class HeapMax<T extends Comparable<T>> extends AbstractHeap<T> implements java.io.Serializable { //implements Heap<T>, Iterable<T>, Iterator<T>
	private static final long serialVersionUID = -6904735977968832161L;
	
	public HeapMax() {
		super();
	}

	public HeapMax(int capacity) {
		super(capacity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean compare(Comparable<T> t, Comparable<T> p) {
		if (getComparator() != null) return (getComparator().compare((T) p, (T) t) < 0);
		return p.compareTo((T) t) < 0 ? true : false; 
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