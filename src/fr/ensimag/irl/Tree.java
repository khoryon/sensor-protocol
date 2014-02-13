package fr.ensimag.irl;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;


public class Tree implements Comparable<Tree> {
	
	private Tree left;
	private Tree right;
	private float weight;
	
	public Tree(float weight) {
		left = right = null;
		this.weight = weight;
	}
	
	public Tree(Tree left, Tree right) {
		this.left = left;
		this.right = right;
		weight = left.weight + right.weight;
	}

	public List<BitSet> code() {
		List<BitSet> list = new ArrayList<BitSet>();
		if(left == null && right == null) {
			BitSet leaf = new BitSet(0);
			list.add(leaf);
		} else {
			for (BitSet bs : left.code()) {
				BitSet node = new BitSet(bs.length() + 1);
				node.or(bs);
				node.set(node.length() - 1, true);
				list.add(node);
			}
			for (BitSet bs : right.code()) {
				BitSet node = new BitSet(bs.length() + 1);
				node.or(bs);
				node.set(node.length() - 1, false);
				list.add(node);
			}
		}
		return list;
	}

	@Override
	public int compareTo(Tree arg0) {
		if (weight < arg0.weight)
			return -1;
		if (weight > arg0.weight)
			return 1;
		return 0;
	}
	
}
