package com.vp;

public class AvlTree implements Tree {
	
	private Node root;
	
	private Node rightRotation(Node node) {
		System.out.println("Rotating to the right on node: " + node);
		Node tempLeftNode = node.getLeftNode();
		Node t = tempLeftNode.getRightNode();
		
		tempLeftNode.setRightNode(node);
		node.setLeftNode(t);
		
		node.setHeight(Math.max(height(node.getLeftNode()), height(node.getRightNode())+1));
		tempLeftNode.setHeight(Math.max(height(tempLeftNode.getLeftNode()), height(tempLeftNode.getRightNode())+1));
		
		return tempLeftNode;
	}
	
	private Node leftRotation(Node node) {
		System.out.println("Rotating to the left on node: " + node);
		Node tempRightNode = node.getRightNode();
		Node t = tempRightNode.getLeftNode();
		
		tempRightNode.setLeftNode(node);
		node.setRightNode(t);
		
		node.setHeight(Math.max(height(node.getLeftNode()), height(node.getRightNode())+1));
		tempRightNode.setHeight(Math.max(height(tempRightNode.getLeftNode()), height(tempRightNode.getRightNode())+1));
		
		return tempRightNode;
	}
	
	public int height(Node node) {
		if(node == null) {
			return -1;
		}
		return node.getHeight();
	}
	
	public int getBalance(Node node) {
		if(node == null) {
			return 0;
		}
		return height(node.getLeftNode()) - height(node.getRightNode());
	}
	@Override
	public void insert(int data) {
		root = insert(root, data);

	}

	private Node insert(Node node, int data) {
		// TODO Auto-generated method stub
		if(node==null) {
			return new Node(data);
		}
		if(data < node.getData()) {
			node.setLeftNode(insert(node.getLeftNode(), data));
		}
		else {
			node.setRightNode(insert(node.getRightNode(), data));
		}
		
		node.setHeight(Math.max(height(node.getLeftNode()), height(node.getRightNode()))+1);
		return settleViolation(node, data);
	}

	private Node settleViolation(Node node, int data) {
		// TODO Auto-generated method stub
		int balance = getBalance(node);
		if(balance > 1 && data < node.getLeftNode().getData()) {
			System.out.println("Tree is left left heavy...");
			return rightRotation(node);
		}
		else if(balance < 1 && data > node.getRightNode().getData()) {
			System.out.println("Tree is right right heavy...");
			return leftRotation(node);
		}
		else if(balance > 1 && data > node.getLeftNode().getData()) {
			System.out.println("Tree is left right heavy...");
			node.setLeftNode(leftRotation(node.getLeftNode()));
			return rightRotation(node);
		}
		else if(balance < 1 && data < node.getRightNode().getData()) {
			System.out.println("Tree is right left heavy...");
			node.setRightNode(rightRotation(node.getRightNode()));
			return leftRotation(node);
		}
		return node;
	}

	@Override
	public void traverse() {
		// TODO Auto-generated method stub
		if(root == null) {
			return;
		}
		inOrderTraversal(root);
	}

	private void inOrderTraversal(Node node) {
		// TODO Auto-generated method stub
		if(node.getLeftNode() != null) {
			inOrderTraversal(node.getLeftNode());
		}
		System.out.println(node);
		if(node.getRightNode() != null) {
			inOrderTraversal(node.getRightNode());
		}
	}

}
