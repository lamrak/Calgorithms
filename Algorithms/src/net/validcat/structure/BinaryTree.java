package net.validcat.structure;

import java.util.NoSuchElementException;

public class BinaryTree<Key extends Comparable<Key>, Value> implements IBinaryTree<Key, Value> {
	private Node root;
	
	private class Node {
		private Node left;
		private Node right;
		private int size = 0;
		
		private Key key;
		private Value val;
		
		public Node(Key key, Value val, int size) {
			this.key = key;
			this.val = val;
			this.size = size;
		}
	}
	
	@Override
	public int size() {
		return size(root);
	}
	
	private int size(Node x) {
		if (x == null) return 0;
		return x.size;
	}

	@Override
	public Value get(Key key) {
		return get(root, key);
	}

	private Value get(Node x, Key key) {
		if (x == null) return null;
		int t = key.compareTo(x.key);
		if 		(t > 0) return get(x.right, key);
		else if (t < 0) return get(x.left, key);
		
		return x.val;
	}

	@Override
	public void put(Key key, Value val) {
		root = put(root, key, val);
	}

	private Node put(Node x, Key key, Value val) {
		if (x == null) return new Node(key, val, 1);
		int t = key.compareTo(x.key);
		if 		(t > 0) x.right = put(x.right, key, val);
		else if (t < 0) x.left  = put(x.left, key, val);
		else 	x.val = val;
		
		x.size = 1 + size(x.left) + size(x.right);
		return x;
	}

	@Override
	public void delete(Key key) {
		if (root == null) throw new NoSuchElementException();
		root = delete(root, key);
	}

	private Node delete(Node x, Key key) {
		if (x == null) return null;
		int t = key.compareTo(x.key);
		if 		(t > 0) x.right = delete(x.right, key);
		else if (t < 0) x.left  = delete(x.left, key);
		else {
			if (x.right == null) return x.left;
			if (x.left == null)  return x.right;
			Node p = x;
			x = min(p.right);
			deleteMin(p.right);
			x.left = p.left;
			x.right = p.right;
			
		}
		x.size = 1 + size(x.left) + size(x.right);
		return x;
		// TODO Save a link to the node to be deleted in p
		// TODO Set x to point to its successor min(t.right).
		// TODO Set the right link of x to deleteMin(t.right), the link to the BST containing all the keys that are larger than x.key after the deletion.
		// TODO Set the left link of x (which was null) to t.left (all the keys that are less than both the deleted key and its successor).
	}

	@Override
	public void deleteMin() {
		if (isEmpty()) throw new NoSuchElementException();
		deleteMin(root);
	}

	private Node deleteMin(Node x) {
		if (x.left == null) return x.right; 
		x.left = deleteMin(x.left);
		x.size = 1 + size(x.left) + size(x.right);
		return x;
	}

	@Override
	public void deleteMax() {
		if (isEmpty()) throw new NoSuchElementException();
		deleteMax(root);
	}
	
	private Node deleteMax(Node x) {
		if (x.right == null) return x.left; 
		x.right = deleteMax(x.right);
		x.size = 1 + size(x.left) + size(x.right);
		return x;
	}
	
	public Key min() {
		if (isEmpty()) throw new NoSuchElementException();
		return min(root).key;
	}

	private Node min(Node x) {
		if (x.left == null) return x;
		return min(x.left);
	}

	public Key max() {
		if (isEmpty()) throw new NoSuchElementException();
		return max(root).key;
	}

	private Node max(Node x) {
		if (x.right == null) return x;
		return min(x.right);
	}

	@Override
	public int rank(Key key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int floor(Key key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int ceiling(Key key) {
		// TODO Auto-generated method stub
		return 0;
	}

	// is used for test only
	public static void main(String[] args) {
		String[] in = {"S","E","A","R","C","H","E","X","A","M","P","L","E"}; //,"P","L","E"
		IBinaryTree<String, Integer> treeSet = new BinaryTree<String, Integer>();
		
		for (int i = 0; i < in.length; i++) {
			treeSet.put(in[i], i);
		}
		
//		treeSet.delete("E");
//		treeSet.delete("S");
//		treeSet.delete("A");
//		treeSet.delete("G");
//		treeSet.deleteMin();
//		treeSet.deleteMax();
		
		for (int i = 0; i < in.length; i++) {
			System.out.println(in[i] + ": " + treeSet.get(in[i]));
		}
		
	}

	@Override
	public boolean contains(Key key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Key select(int k) {
		// TODO Auto-generated method stub
		return null;
	}

}
