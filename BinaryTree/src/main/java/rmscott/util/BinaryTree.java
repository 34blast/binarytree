/**
 * 
 */
package rmscott.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A BinaryTree Java implementation
 */
public class BinaryTree implements Serializable {
	private static final long serialVersionUID = 4963308648718052280L;

	public Object value;
	private BinaryTree leftChild;
	private BinaryTree rightChild;

	/**
	 * Constructor for BinaryTree.
	 * 
	 * @param pValue
	 *            The value to be placed in the root.
	 * @param pLeftChild
	 *            The left child of the root (may be null).
	 * @param pRightChild
	 *            The right child of the root (may be null).
	 */
	public BinaryTree(Object pValue, BinaryTree pLeftChild, BinaryTree pRightChild) {
		this.value = pValue;
		this.leftChild = pLeftChild;
		this.rightChild = pRightChild;
	}

	/**
	 * Constructor for a BinaryTree leaf node (that is, with no children).
	 * 
	 * @param pValue
	 *            The value to be placed in the root.
	 */
	public BinaryTree(Object pValue) {
		this(pValue, null, null);
	}

	/**
	 * Gets the property value
	 * 
	 * @return Object
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Sets the property value
	 * 
	 * @param value
	 *            The new value.
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * Gets the property leftChild
	 * 
	 * @return BinaryTree
	 */
	public BinaryTree getLeftChild() {
		return leftChild;
	}

	/**
	 * Sets the property leftChild
	 * 
	 * @param pLeftChild
	 *            The node to be added as the new left child.
	 * @throws IllegalArgumentException
	 *             If the operation would cause a loop in the binary tree.
	 */
	public void setLeftChild(BinaryTree pLeftChild) throws IllegalArgumentException {
		if (contains(pLeftChild, this)) {
			throw new IllegalArgumentException("passed in left child " + this + " already contains " + pLeftChild);
		}
		leftChild = pLeftChild;
	}

	/**
	 * Gets the property rightChile
	 * 
	 * @return BinaryTree
	 */
	public BinaryTree getRightChild() {
		return rightChild;
	}

	/**
	 * Sets the property leftChild
	 * 
	 * @param pLeftChild
	 *            The node to be added as the new left child.
	 * @throws IllegalArgumentException
	 *             If the operation would cause a loop in the binary tree.
	 */
	public void setRightChild(BinaryTree pRightChild) throws IllegalArgumentException {
		if (contains(pRightChild, this)) {
			throw new IllegalArgumentException("passed in right child " + this + " already contains " + pRightChild);
		}
		rightChild = pRightChild;
	}

	/**
	 * Tests whether this node is a leaf node.
	 * 
	 * @return <code>true</code> if this BinaryTree node has no children.
	 */
	public boolean isLeaf() {
		boolean isLeaf = false;
		if (leftChild == null && rightChild == null) {
			isLeaf = true;
		}
		return isLeaf;
	}

	/**
	 * Gets all the nodes at a certain level, the root is level 0
	 * 
	 * @param pLevel
	 * @return List<BinaryTree>
	 */
	static public List<BinaryTree> getNodesAtLevel(BinaryTree pBTree, int pLevel) {
		ArrayList<BinaryTree> levelList = new ArrayList<BinaryTree>();
		if (pLevel == 0) {
			levelList.add(pBTree);
		} else {
			BinaryTree leftChild = pBTree.getLeftChild();
			List<BinaryTree> leftChildren = null;
			int nextlevel = pLevel - 1;
			if (leftChild != null) {
				leftChildren = BinaryTree.getNodesAtLevel(leftChild, nextlevel);
				levelList.addAll(leftChildren);
			}
			BinaryTree rightChild = pBTree.getRightChild();
			List<BinaryTree> rightChildren = null;
			if( rightChild != null) {
				rightChildren = BinaryTree.getNodesAtLevel(rightChild, nextlevel);
			}
			levelList.addAll(rightChildren);
		}

		return levelList;
	}

	/**
	 * Prints the binary tree rooted at this BinaryTree node.
	 */
	public void print() {
		print(this, 0);
	}

	/**
	 * Computes a hash code for the complete binary tree rooted at this
	 * BinaryTree node.
	 * 
	 * @return A hash code for the binary tree with this root.
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		int result = value.hashCode();
		if (leftChild != null) {
			result += 3 * leftChild.hashCode();
		}
		if (rightChild != null) {
			result += 7 * leftChild.hashCode();
		}
		return result;
	}

	/**
	 * Tests whether this BinaryTree is equal to the given object.
	 * 
	 * @param pOther
	 * @return boolean
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object pOther) {
		if (pOther == null || !(pOther instanceof BinaryTree)) {
			return false;
		}
		BinaryTree otherTree = (BinaryTree) pOther;
		return equals(value, otherTree.value) && equals(leftChild, otherTree.getLeftChild())
				&& equals(rightChild, otherTree.getRightChild());
	}

	/**
	 * Returns a String representation of this BinaryTree.
	 * 
	 * @see java.lang.Object#toString()
	 * @return A String representation of this BinaryTree.
	 */
	public String toString() {
		if (isLeaf()) {
			return value.toString();
		} else {
			String root = value.toString();
			String left = "null";
			String right = "null";
			if (getLeftChild() != null) {
				left = getLeftChild().toString();
			}
			if (getRightChild() != null) {
				right = getRightChild().toString();
			}
			return root + " (" + left + ", " + right + ")";
		}
	}

	/**
	 * Tests whether the <code>tree</code> argument contains within itself the
	 * <code>targetNode</code> argument.
	 * 
	 * @param pTree
	 *            The root of the binary tree to search.
	 * @param pTargetNode
	 *            The node to be searched for.
	 * @return boolean
	 */
	protected boolean contains(BinaryTree pTree, BinaryTree pTargetNode) {
		if (pTree == null)
			return false;
		if (pTree == pTargetNode)
			return true;
		return contains(pTargetNode, pTree.getLeftChild()) || contains(pTargetNode, pTree.getRightChild());
	}

	/**
	 * Tests whether its two arguments are equal.
	 * 
	 * @param pOne
	 *            The first object to be tested.
	 * @param pTwo
	 *            The second object to be tested.
	 * @return <code>true</code> if the two objects are equal.
	 */
	private boolean equals(Object pOne, Object pTwo) {
		if (pOne == null)
			return pTwo == null;
		return pOne.equals(pTwo);
	}

	private void print(BinaryTree root, int indent) {
		for (int i = 0; i < indent; i++) {
			System.out.print("   ");
		}
		if (root == null) {
			System.out.println("null");
			return;
		}
		System.out.println(root.value);
		if (root.isLeaf())
			return;
		print(root.leftChild, indent + 1);
		print(root.rightChild, indent + 1);
	}
}
