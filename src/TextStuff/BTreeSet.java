package TextStuff;

import java.util.Arrays;
import java.util.Objects;

public final class BTreeSet {
		
	private Node root;
	private int size;
	
	private final int minKeys;  // At least 1, equal to degree-1
	private final int maxKeys;  // At least 3, odd number, equal to minKeys*2+1
	
	// The degree is the minimum number of children each non-root internal node must have.
	public BTreeSet(int degree) {
		if (degree < 2)
			throw new IllegalArgumentException("Degree must be at least 2");
		if (degree * 2L > Integer.MAX_VALUE)  // In other words, need maxChildren <= INT_MAX
			throw new IllegalArgumentException("Degree too large");
		minKeys = degree - 1;
		maxKeys = degree * 2 - 1;
		clear();
	}
	
	public int size() {
		return size;
	}
	public void clear() {
		root = new Node(maxKeys, true);
		size = 0;
	}
	
	public BPlusTree getElement(String key) {
		Objects.requireNonNull(key);
		// Walk down the tree
		Node node = root;
		while (true) {
			int index = node.search(key);
			BPlusTree element = node.getFromNode(key);
			if (index >= 0)
				return element;
			else if (node.isLeaf())
				return null;
			else  // Internal node
				node = node.children[~index];
		}
	}
	
	public boolean add(BPlusTree newTree) {
		Objects.requireNonNull(newTree);
		
		// Special preprocessing to split root node
		if (root.numKeys == maxKeys) {
			Node child = root;
			root = new Node(maxKeys, false);  // Increment tree height
			root.children[0] = child;
			root.splitChild(0);
		}
		// Walk down the tree
		Node node = root;
		while (true) {
			// Search for index in current node
			assert node.numKeys < maxKeys;
			assert node == root || node.numKeys >= minKeys;
			int index = node.search(newTree.letter);
			if (index >= 0)
				return false;  // Key already exists in tree
			index = ~index;
			assert index >= 0;
			
			if (node.isLeaf()) {  // Simple insertion into leaf
				if (size == Integer.MAX_VALUE)
					throw new IllegalStateException("Maximum size reached");
				node.insertKeyAndChild(index, newTree, -1, null);
				size++;
				return true;
				
			} else {  // Handle internal node
				Node child = node.children[index];
				if (child.numKeys == maxKeys) {  // Split child node
					node.splitChild(index);
					int cmp = newTree.letter.compareTo(node.keys[index].letter);
					if (cmp == 0)
						return false;  // Key already exists in tree
					else if (cmp > 0)
						child = node.children[index + 1];
				}
				node = child;
			}
		}
	}
	private static final class Node {
		
		public final BPlusTree[] keys;  // Length equal to maxKeys, not null
		public final Node[] children;  // Null if leaf node, length maxKeys+1 if internal node
		public int numKeys;  // Range is [0, maxKeys] for root, but [minKeys, maxKeys] for all other nodes
				
		public Node(int maxKeys, boolean leaf) {
			assert maxKeys >= 3 && maxKeys % 2 == 1;
			keys = new BPlusTree[maxKeys];
			children = leaf ? null : new Node[maxKeys + 1];
			numKeys = 0;
		}
				
		private int minKeys() {
			return keys.length / 2;
		}
		
		private int maxKeys() {
			return keys.length;
		}
		
		public boolean isLeaf() {
			return children == null;
		}

		public BPlusTree getFromNode(String key) {
			int i = 0;
			while (i < numKeys) {
				int cmp = key.compareTo(keys[i].letter);
				if (cmp == 0) {
					assert 0 <= i && i < numKeys;
					return keys[i];  // Key found
				} else if (cmp > 0)
					i++;
				else  // cmp < 0
					break;
			}
			assert 0 <= i && i <= numKeys;
			return null;  // Not found, caller should recurse on child
		}
		// Searches this node's keys array and returns i (non-negative) if obj equals keys[i],
		// otherwise returns ~i (negative) if children[i] should be explored. For simplicity,
		// the implementation uses linear search. It's possible to replace it with binary search for speed.
		public int search(String key) {
			int i = 0;
			while (i < numKeys) {
				int cmp = key.compareTo(keys[i].letter);
				if (cmp == 0) {
					assert 0 <= i && i < numKeys;
					return i;  // Key found
				} else if (cmp > 0)
					i++;
				else  // cmp < 0
					break;
			}
			assert 0 <= i && i <= numKeys;
			return ~i;  // Not found, caller should recurse on child
		}
		
		// Inserts the given key and child into this node's arrays at the given indices, incrementing the number of keys.
		public void insertKeyAndChild(int keyIndex, BPlusTree newTree, int childIndex, Node child) {
			assert 0 <= numKeys && numKeys < maxKeys() && newTree != null;
			assert 0 <= keyIndex && keyIndex <= numKeys;
			
			// Handle children array
			if (isLeaf())
				assert childIndex == -1 && child == null;
			else {
				assert 0 <= childIndex && childIndex <= numKeys + 1 && child != null;
				System.arraycopy(children, childIndex, children, childIndex + 1, numKeys + 1 - childIndex);
				children[childIndex] = child;
			}
			
			// Handle keys array
			System.arraycopy(keys, keyIndex, keys, keyIndex + 1, numKeys - keyIndex);
			keys[keyIndex] = newTree;
			numKeys++;
		}
		/*-- Methods for insertion --*/
		// For the child node at the given index, this moves the right half of keys and children to a new node,
		// and adds the middle key and new child to this node. The left half of child's data is not moved.
		public void splitChild(int index) {
			assert !this.isLeaf() && 0 <= index && index <= this.numKeys && this.numKeys < maxKeys();
			Node left = this.children[index];
			assert left.numKeys == maxKeys();
			Node right = new Node(maxKeys(), left.isLeaf());
			int minKeys = minKeys();
			// Handle children
			if (!left.isLeaf()) {
				System.arraycopy(left.children, minKeys + 1, right.children, 0, minKeys + 1);
				Arrays.fill(left.children, minKeys + 1, left.children.length, null);
			}
			// Handle keys
			BPlusTree middleKey = left.keys[minKeys];
			System.arraycopy(left.keys, minKeys + 1, right.keys, 0, minKeys);
			Arrays.fill(left.keys, minKeys, left.keys.length, null);
			left.numKeys = minKeys;
			right.numKeys = minKeys;
			
			this.insertKeyAndChild(index, middleKey, index + 1, right);
		}	
	}	
}