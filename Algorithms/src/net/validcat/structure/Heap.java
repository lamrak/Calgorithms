package net.validcat.structure;

public interface Heap<T extends Comparable<T>> extends Cloneable, java.io.Serializable {
	
	T extract();
	T get();
	void insert(Comparable<T> t);
	
	int size();
	boolean isEmpty();
	
}
