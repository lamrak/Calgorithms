package net.validcat.structure;

public interface IBinaryTree<Key, Value> {
	int size();
	
	Value get(Key key);
	void put(Key key, Value val);
	
	void delete(Key key);
	void deleteMin();
	void deleteMax();
	
	int rank(Key key);
	
	int floor(Key key);
	int ceiling(Key key);
	
}
