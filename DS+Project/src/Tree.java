public class Tree {
	private LeafNode root;

	
	public int Search(int searchFor) {
		LeafNode current = root;

		while (true) {
			if (current.getKey() == searchFor) {
				return current.getFileLine();
			} else if (current.getKey() > searchFor) {
				current = current.getLeft();
			} else {
				current = current.getRight();
			}
		}
	}

	public int height(LeafNode N) {
		if (N == null)
			return 0;

		return N.getHeight();
	}

	public int max(int a, int b) {
		return (a > b) ? a : b;
	}

	public LeafNode rightRotate(LeafNode y) {
		LeafNode x = y.getLeft();
		LeafNode T2 = x.getRight();

		x.setRight(y);
		y.setLeft(T2);

		y.setHeight(max(height(y.getLeft()), height(y.getRight())) + 1);
		x.setHeight(max(height(x.getLeft()), height(x.getRight())) + 1);

		return x;
	}

	public LeafNode leftRotate(LeafNode x) {
		LeafNode y = x.getRight();
		LeafNode T2 = y.getLeft();

		y.setLeft(x);
		x.setRight(T2);

		x.setHeight(max(height(x.getLeft()), height(x.getRight())) + 1);
		y.setHeight(max(height(y.getLeft()), height(y.getRight())) + 1);

		return y;
	}

	public int getBalance(LeafNode N) {
		if (N == null)
			return 0;

		return height(N.getLeft()) - height(N.getRight());
	}

	public LeafNode insert(LeafNode LeafNode, int key) {

		if (LeafNode == null)
			return (new LeafNode(key));

		if (key < LeafNode.getKey())
			LeafNode.setLeft(insert(LeafNode.getLeft(), key));
		else if (key > LeafNode.getKey())
			LeafNode.setRight(insert(LeafNode.getRight(), key));
		else
			return LeafNode;

		LeafNode.setHeight(1 + max(height(LeafNode.getLeft()), height(LeafNode.getRight())));

		int balance = getBalance(LeafNode);

		if (balance > 1 && key < LeafNode.getLeft().getKey())
			return rightRotate(LeafNode);

		if (balance < -1 && key > LeafNode.getRight().getKey())
			return leftRotate(LeafNode);

		if (balance > 1 && key > LeafNode.getLeft().getKey()) {
			LeafNode.setLeft(leftRotate(LeafNode.getLeft()));
			return rightRotate(LeafNode);
		}

		if (balance < -1 && key < LeafNode.getRight().getKey()) {
			LeafNode.setRight(rightRotate(LeafNode.getRight()));
			return leftRotate(LeafNode);
		}

		return LeafNode;
	}

	public LeafNode getRoot() {
		return root;
	}

	public void setRoot(LeafNode node) {
		root = node;
	}

	public void preOrder(LeafNode LeafNode) {
		if (LeafNode != null) {
			System.out.print(LeafNode.getKey() + " ");
			preOrder(LeafNode.getLeft());
			preOrder(LeafNode.getRight());
		}
	}
}