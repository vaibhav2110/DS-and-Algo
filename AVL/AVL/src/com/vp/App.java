package com.vp;

import com.vp.AvlTree;
import com.vp.Tree;

public class App {
	public static void main(String args[]) {
		Tree avl = new AvlTree();

		avl.insert(1);
		avl.insert(2);
		avl.insert(5);
		avl.insert(3);
		avl.traverse();
	}
}
