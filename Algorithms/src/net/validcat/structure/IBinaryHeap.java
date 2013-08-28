package net.validcat.structure;

public interface IBinaryHeap<T extends Comparable<T>> extends Cloneable {
	
	T extract();
	T get();
	void insert(Comparable<T> t);
	
	int size();
	boolean isEmpty();
	
}
