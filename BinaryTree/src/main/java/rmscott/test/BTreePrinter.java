/**
 * 
 */
package rmscott.test;

import java.util.Collections;
import java.util.List;

import rmscott.util.BinaryTree;

/**
 * @author rmscott
 *
 */
public class BTreePrinter {

	static final int ONE = 1;

	public static String[] LEVEL_NAMES = { "root", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
			"O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

	public BTreePrinter() {

	}

	/**
	 * this method initializes a binary tree for testing
	 * 
	 * @param pLevels
	 *            - how many levels to create
	 * @return BinaryTree
	 */
	static BinaryTree initialzeTestTree(int pTotalLevels) {
		String baseLevelName = BTreePrinter.LEVEL_NAMES[0];
		BinaryTree root = new BinaryTree(baseLevelName);
		if (pTotalLevels > 1) {
			BTreePrinter.initialzeTestTree(root, 1, pTotalLevels);
		}
		return root;

	}

	/**
	 * this method initializes a binary tree for testing
	 * 
	 * @param pLevels
	 *            - how many levels to create
	 * @return BinaryTree
	 */
	static private void initialzeTestTree(BinaryTree pParent, int pCurrentLevel, int pTotalLevels) {
		String baseLevelName = BTreePrinter.LEVEL_NAMES[pCurrentLevel];

		int parentNumber = 1;
		if (pCurrentLevel > 1) {
			try {
				String parentName = (String) pParent.getValue();
				String intPart = parentName.substring(1);
				Integer myInteger = new Integer(intPart);
				parentNumber = myInteger.intValue();
			} catch (Exception exc) {
				System.out.println("can't get int at level : " + pCurrentLevel);
				System.out.println("parentName : " + (String) pParent.getValue());
			}
		}
		int childNumber = parentNumber * 2;
		BinaryTree left = new BinaryTree(baseLevelName + (childNumber - 1));
		BinaryTree right = new BinaryTree(baseLevelName + childNumber);
		pParent.setLeftChild(left);
		pParent.setRightChild(right);

		int newLevel = pCurrentLevel + 1;
		if (newLevel < pTotalLevels) {
			BTreePrinter.initialzeTestTree(left, newLevel, pTotalLevels);
			BTreePrinter.initialzeTestTree(right, newLevel, pTotalLevels);
		}
		return;

	}

	/**
	 * @param args
	 */
	public static void main(String[] pArgs) {
		int treeLevels = 6;
		System.out.println();
		System.out.println("BTreePrinter.main() : starting execution ....");
		System.out.println();
		BinaryTree bTreeRoot = BTreePrinter.initialzeTestTree(treeLevels);
		bTreeRoot.print();
		System.out.println();

		// Print the nodes 1 level at a time, odd ones should be in reverse
		System.out.println("Printing the nodes 1 level at a time,  odd will be in reverse order");
		for (int idx = 0; idx < treeLevels; idx++) {

			List<BinaryTree> nodeList = BinaryTree.getNodesAtLevel(bTreeRoot, idx);
			// check if even,  if so reverse the List
			if( idx % 2 == 0) {
				Collections.reverse(nodeList);
			} 
			for (BinaryTree tree : nodeList) {
				System.out.print(tree.getValue());
				System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println();

		// Exit
		System.out.println();
		System.out.println("BTreePrinter.main() : exiting ....");
		System.out.println();

	} // end of main
}
