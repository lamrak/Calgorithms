package net.validcat.structure;

public interface IBinaryTree<Key, Value> {
	int size();
	
	Value get(Key key);
	void put(Key key, Value val);
	
	boolean contains(Key key);
	boolean isEmpty();
	
	Key select(int k);
	
	void delete(Key key);
	void deleteMin();
	void deleteMax();
	
	Key min();
	Key max();
	
	int rank(Key key);
	
	int floor(Key key);
	int ceiling(Key key);
	
}
