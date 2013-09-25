package net.validcat.structure;

public class BinaryTree<Key extends Comparable<Key>, Value> implements IBinaryTree<Key, Value> {
	private Node root;
	
	protected class Node {
		protected Node left;
		protected Node right;
		protected int size = 0;
		
		protected Key key;
		protected Value val;
		
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void put(Key key, Value val) {
		root = put(root, key, val);
	}

	private Node put(Node x, Key key, Value val) {
		if (x == null) return new Node(key, val, 1);
		int t = key.compareTo(x.key);
		if (t > 0) 		x.right = put(x.right, key, val);
		else if (t < 0) x.left = put(x.left, key, val);
		else x.val = val;
		
		x.size = 1 + size(x.left) + size(x.right); 
		return x;
	}

	@Override
	public void delete(Key key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMin() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMax() {
		// TODO Auto-generated method stub
		
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
		String[] in = {"S","E","A","R","C","H","E","X","A","M","P","L","E"};
		IBinaryTree<String, Integer> treeSet = new BinaryTree<String, Integer>();
		
		for (int i = 0; i < in.length; i++) {
			treeSet.put(in[i], i);
		}
		
	}

}
