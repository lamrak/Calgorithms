package net.validcat.interfaces;

import java.util.Iterator;

public interface Heap<T extends Comparable<T>> extends Iterable<T>, Iterator<T> {
	
	T extract();
	T get();
	void insert(Comparable<T> t);
	
	int size();
	boolean isEmpty();
	
}
