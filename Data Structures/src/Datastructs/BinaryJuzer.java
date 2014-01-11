package Datastructs;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class BinaryJuzer<E extends Comparable<E>> {

	private JuzerBranch<E> root;

	public BinaryJuzer(JuzerBranch<E> r) {
		root = r;
	}

	public void inOrder(ArrayList<E> ar) {
		inOrder(ar, root);
	}

	private void inOrder(ArrayList<E> ar, JuzerBranch<E> r) {
		if (r == null)
			return;

		inOrder(ar, r.Left());

		ar.add(r.Data());

		inOrder(ar, r.Right());

	}

	public void postOrder(ArrayList<E> ar) {
		postOrder(ar, root);
	}

	private void postOrder(ArrayList<E> ar, JuzerBranch<E> r) {
		if (r == null)
			return;

		postOrder(ar, r.Left());

		postOrder(ar, r.Right());
		ar.add(r.Data());
	}

	public void preOrder(ArrayList<E> ar) {
		preOrder(ar, root);
	}

	private void preOrder(ArrayList<E> ar, JuzerBranch<E> r) {
		if (r == null)
			return;

		ar.add(r.Data());
		preOrder(ar, r.Left());
		preOrder(ar, r.Right());

	}

	public static void main(String[] arg) {

		// Balanced Tree:
		JuzerBranch<Integer> bRoot = new JuzerBranch<Integer>(3);

		JuzerBranch<Integer> bLeft = new JuzerBranch<Integer>(9);
		bRoot.setLeft(bLeft);
		bLeft.setParent(bRoot);

		JuzerBranch<Integer> bRight = new JuzerBranch<Integer>(11);
		bRoot.setRight(bRight);
		bRight.setParent(bRoot);

		JuzerBranch<Integer> bLeftLeft = new JuzerBranch<Integer>(6);
		bLeft.setLeft(bLeftLeft);
		bLeftLeft.setParent(bLeft);

		JuzerBranch<Integer> bLeftRight = new JuzerBranch<Integer>(4);
		bLeft.setRight(bLeftRight);
		bLeftRight.setParent(bLeft);

		JuzerBranch<Integer> bRightLeft = new JuzerBranch<Integer>(21);
		bRight.setLeft(bRightLeft);
		bRightLeft.setParent(bRight);

		JuzerBranch<Integer> bRightRight = new JuzerBranch<Integer>(1);
		bRight.setRight(bRightRight);
		bRightRight.setParent(bRight);

		BinaryJuzer<Integer> balancedTree = new BinaryJuzer<Integer>(bRoot);

		balancedTree.Test();

		balancedTree.rotateLeft(bLeft);

		balancedTree.Test();

		balancedTree.rotateRight(bRoot);

		balancedTree.Test();

		// Unbalanced Right Tree:
		/*
		 * JuzerBranch<Integer> unbrRoot = new JuzerBranch<Integer>(3);
		 * 
		 * JuzerBranch<Integer> unbrRight = new JuzerBranch<Integer>(11);
		 * unbrRoot.setRight(unbrRight);
		 * 
		 * 
		 * JuzerBranch<Integer> unbrRightRight = new JuzerBranch<Integer>(4);
		 * unbrRight.setRight(unbrRightRight);
		 * 
		 * 
		 * JuzerBranch<Integer> unbrRightRightRight = new
		 * JuzerBranch<Integer>(1);
		 * unbrRightRight.setRight(unbrRightRightRight);
		 * 
		 * BinaryJuzer<Integer> unbalancedRightTree = new
		 * BinaryJuzer<Integer>(unbrRoot);
		 * 
		 * unbalancedRightTree.Test();
		 * 
		 * //Unbalanced Left Tree: JuzerBranch<Integer> unblRoot = new
		 * JuzerBranch<Integer>(3);
		 * 
		 * JuzerBranch<Integer> unblLeft = new JuzerBranch<Integer>(11);
		 * unblRoot.setLeft(unblLeft);
		 * 
		 * 
		 * JuzerBranch<Integer> unblLeftLeft = new JuzerBranch<Integer>(4);
		 * unblLeft.setLeft(unblLeftLeft);
		 * 
		 * 
		 * JuzerBranch<Integer> unblLeftLeftLeft = new JuzerBranch<Integer>(1);
		 * unblLeftLeft.setLeft(unblLeftLeftLeft);
		 * 
		 * BinaryJuzer<Integer> unbalancedLeftTree = new
		 * BinaryJuzer<Integer>(unblRoot);
		 * 
		 * unbalancedLeftTree.Test();
		 */
	}

	private void Test() {

		ArrayList<E> bTester = new ArrayList<E>();

		inOrder(bTester);
		System.out.println("inOrder: " + bTester + "\n");
		bTester.clear();

		preOrder(bTester);
		System.out.println("preOrder: " + bTester + "\n");
		bTester.clear();

		postOrder(bTester);
		System.out.println("postOrder: " + bTester + "\n");
		bTester.clear();

	}

	public void rotateLeft(JuzerBranch<E> e) {

		JuzerBranch<E> eParent = e.getParent();
		JuzerBranch<E> eRight = e.Right();

		if (eRight == null)
			return;

		JuzerBranch<E> eNewRight = eRight.Left();

		eRight.setLeft(e);
		e.setParent(eRight);

		e.setRight(eNewRight);
		if (eNewRight != null) {
			eNewRight.setParent(e);
		}

		if (eParent == null) {

			eRight.setParent(null);
			this.root = eRight;

		} else {
			boolean isLeft = eParent.Left().equals(e);
			if (isLeft) {
				eParent.setLeft(eRight);
			} else {
				eParent.setRight(eRight);
			}
		}

	}

	public void rotateRight(JuzerBranch<E> e) {
		JuzerBranch<E> eParent = e.getParent();
		JuzerBranch<E> eLeft = e.Left();

		if (eLeft == null)
			return;

		JuzerBranch<E> eNewLeft = eLeft.Right();

		eLeft.setRight(e);
		e.setParent(eLeft);

		e.setLeft(eNewLeft);
		if (eNewLeft != null) {
			eNewLeft.setParent(e);
		}

		if (eParent == null) {

			eLeft.setParent(null);
			this.root = eLeft;

		} else {
			boolean isLeft = eParent.Left().equals(e);
			if (isLeft) {
				eParent.setLeft(eLeft);
			} else {
				eParent.setRight(eLeft);
			}
		}

		/*PrintStream a = System.out;
		
		a.println("moose");*/
	}
}
