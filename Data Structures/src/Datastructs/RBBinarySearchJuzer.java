package Datastructs;

import java.util.*;

public class RBBinarySearchJuzer<E extends Comparable<E>> {

	private final JuzerBranch.Color RED = JuzerBranch.Color.Red;
	private final JuzerBranch.Color BLACK = JuzerBranch.Color.Black;

	private JuzerBranch<E> root;

	public void clear() {
		root = null;
	}

	public void inOrderRB(ArrayList<String> ar) {
		inOrderRB(ar, root);
	}

	public void OrderRB(ArrayList<JuzerBranch<E>> ar) {
		OrderRB(ar, root);
	}

	private void OrderRB(ArrayList<JuzerBranch<E>> ar, JuzerBranch<E> r) {
		if (r == null)
			return;

		OrderRB(ar, r.Left());

		ar.add(r);

		OrderRB(ar, r.Right());

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

	private void inOrderRB(ArrayList<String> ar, JuzerBranch<E> r) {
		if (r == null)
			return;

		inOrderRB(ar, r.Left());

		ar.add(r.toString());

		inOrderRB(ar, r.Right());

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

	public boolean isEmpty() {
		return root == null;
	}

	private void preOrder(ArrayList<E> ar, JuzerBranch<E> r) {
		if (r == null)
			return;

		ar.add(r.Data());
		preOrder(ar, r.Left());
		preOrder(ar, r.Right());

	}

	public void add(E e) {
		if (root == null)
			root = new JuzerBranch<E>(e);

		else
			add(root, new JuzerBranch<E>(e));

	}

	private void add(JuzerBranch<E> n, JuzerBranch<E> target) {

		if (n.Data().compareTo(target.Data()) > 0) {
			if (n.Left() == null) {
				n.setLeft(target);
				target.setParent(n);
			} else {
				add(n.Left(), target);
			}
		} else if (n.Data().compareTo(target.Data()) < 0) {
			if (n.Right() == null) {
				n.setRight(target);
				target.setParent(n);
			} else {
				add(n.Right(), target);
			}
		}
	}

	public E get(E e) {
		if (getTarget(e) == null)
			return null;
		return getTarget(e).Data();

	}

	public void addRedBlack(E e) {
		this.add(e);
		JuzerBranch<E> target = this.getTarget(e);

		checkRoot(target);
	}

	private void checkRoot(JuzerBranch<E> e) {
		if (e == root)
			e.changeColor(JuzerBranch.Color.Black);
		else
			fixParent(e);
	}

	private void fixParent(JuzerBranch<E> e) {
		if (e.getParent().isRed()) {
			fixUncle(e);
		} else {
			return;
		}
		// checkRoot(e.getParent());
	}

	private void fixUncle(JuzerBranch<E> e) {
		JuzerBranch<E> parent = e.getParent();
		JuzerBranch<E> gparent = e.getGParent();

		if (gparent == null) {

		} else {
			JuzerBranch<E> uncle = e.getUncle();
			if (uncle != null && uncle.isRed()) {
				parent.changeColor(JuzerBranch.Color.Black);
				uncle.changeColor(JuzerBranch.Color.Black);
				gparent.changeColor(JuzerBranch.Color.Red);
				checkRoot(gparent);
			} else {
				rotateA(e);
			}
		}
	}

	private void rotateA(JuzerBranch<E> e) {
		JuzerBranch<E> gparent = e.getGParent();

		if (e == e.getParent().Right() && e.getParent() == gparent.Left()) {
			rotateLeft(e.getParent());
			e = e.Left();
		} else if (e == e.getParent().Left()
				&& e.getParent() == gparent.Right()) {
			rotateRight(e.getParent());
			e = e.Right();
		}
		rotateB(e);
	}

	private void rotateB(JuzerBranch<E> e) {
		JuzerBranch<E> gparent = e.getGParent();

		// System.out.println(gparent);
		e.getParent().changeColor(JuzerBranch.Color.Black);
		gparent.changeColor(JuzerBranch.Color.Red);

		if (e == e.getParent().Left()) {
			rotateRight(gparent);
		} else {
			rotateLeft(gparent);
		}
	}

	public E removeRB(E e) {

		JuzerBranch<E> target = getTarget(e);
		
		if(target.isLeaf()){
			
				remove(e);
		}else{

		JuzerBranch<E> current = getLeftMost(target.Right());

		
		
		if (current == null) {
			
			

			
		} else if (current.isLeaf()) {
			if (current.isRed()) {

				remove(current.Data());
				current.changeColor(target.getColor());

				current.setLeft(target.Left());
				current.setRight(target.Right());
				current.setParent(target.getParent());

				if (target.isLeftChild()) {
					target.getParent().setLeft(current);
				} else {
					target.getParent().setRight(current);
				}

			} else {
				if (current.getSibling().isLeaf()) {

					current.getSibling().changeColor(RED);
					current.getParent().changeColor(RED);
					current.changeColor(RED);

					current.setLeft(target.Left());
					current.setRight(target.Right());
					current.setParent(target.getParent());

					if (target.isLeftChild()) {
						target.getParent().setLeft(current);
					} else {
						target.getParent().setRight(current);
					}

				} else {

				}

			}
		} else {

		}
		
		}
		return e;

	}

	public E remove(E e) {

		if (root == null)
			return null;

		JuzerBranch<E> target = getTarget(e);

		if (target == null)
			return null;

		JuzerBranch<E> parent = target.getParent();
		// System.out.println("Target:" + target.Data());

		if (parent != null) {

			if (target.Left() == null && target.Right() == null) {

				if (parent.Left() == null) {
					parent.setRight(null);
				} else if (parent.Right() == null) {
					parent.setLeft(null);
				} else if (parent.Left().Data().compareTo(e) == 0) {
					parent.setLeft(null);
				} else {
					parent.setRight(null);
				}
				target.setParent(null);
				return target.Data();

			}
			if (target.Right() == null) {
				JuzerBranch<E> left = target.Left();

				if (parent.Left() == null) {
					parent.setRight(left);
				} else if (parent.Right() == null) {
					parent.setLeft(left);
				} else if (parent.Left().Data().compareTo(e) == 0) {
					parent.setLeft(left);
				} else {
					parent.setRight(left);
				}

				left.setParent(parent);
				target.setParent(null);
				return target.Data();
			}
			if (target.Left() == null) {
				JuzerBranch<E> right = target.Right();

				if (parent.Left() == null) {
					parent.setRight(right);
				} else if (parent.Right() == null) {
					parent.setLeft(right);
				} else if (parent.Left().Data().compareTo(e) == 0) {
					parent.setLeft(right);
				} else {
					parent.setRight(right);
				}

				right.setParent(parent);
				target.setParent(null);
				return target.Data();
			}
			JuzerBranch<E> next = getLeftMost(target.Right());

			if (parent.Left() == null) {
				parent.setRight(next);
			} else if (parent.Right() == null) {
				parent.setLeft(next);
			} else if (parent.Left().Data().compareTo(e) == 0) {
				parent.setLeft(next);
			} else {
				parent.setRight(next);
			}

			target.setParent(null);
			return target.Data();
		} else {

			if (root.Left() == null && root.Right() == null) {
				root = null;
				return target.Data();
			}

			else if (target.Right() == null) {
				root.Left().setParent(null);
				root = root.Left();
				return target.Data();
			} else if (target.Left() == null) {

				root.Right().setParent(null);
				root = root.Right();

				return target.Data();
			}
			JuzerBranch<E> next = getLeftMost(target.Right());

			if (next != target.Right()) {

				next.getParent().setLeft(next.Right());
				next.Right().setParent(next.getParent());
				next.setParent(null);
				next.setLeft(target.Left());
				next.setRight(target.Right());

			} else {
				next.setParent(parent);
				next.setLeft(target.Left());

			}

			target.Left().setParent(next);
			target.Right().setParent(next);
			root = next;
			return target.Data();
		}

	}

	private void rotateLeft(JuzerBranch<E> e) {

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
			if (e != this.root)
				throw new RuntimeException("What the moose?");
			else {
				// eRight.setParent(null);
				this.root = eRight;
			}
		} else {
			boolean isLeft = eParent.Left() != null && eParent.Left() == e;
			if (isLeft) {
				eParent.setLeft(eRight);

			} else {
				eParent.setRight(eRight);
			}
		}
		eRight.setParent(eParent);

	}

	private void rotateRight(JuzerBranch<E> e) {
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

			// eLeft.setParent(null);
			this.root = eLeft;

		} else {
			boolean isLeft = eParent.Left() != null && eParent.Left() == e;
			if (isLeft) {
				eParent.setLeft(eLeft);
			} else {
				eParent.setRight(eLeft);
			}
		}
		eLeft.setParent(eParent);

		// System.out.println(e.getParent());

		/*
		 * PrintStream a = System.out;
		 * 
		 * a.println("moose");
		 */
	}

	private JuzerBranch<E> getLeftMost(JuzerBranch<E> right) {
		if (right == null)
			return null;
		if (right.Left() == null)
			return right;
		return getLeftMost(right.Left());

	}

	private JuzerBranch<E> getTarget(E e) {
		return getTarget(e, root);
	}

	private JuzerBranch<E> getTarget(E e, JuzerBranch<E> n) {
		if (n == null)
			return null;
		if (n.Data().compareTo(e) == 0)
			return n;

		if (n.Data().compareTo(e) < 0)
			return getTarget(e, n.Right());
		else
			return getTarget(e, n.Left());

	}

	public E removeRedBlack(E e) {

		JuzerBranch<E> target = getTarget(e);

		JuzerBranch<E> successor = getLeftMost(target.Right());

		return null;

	}

	private JuzerBranch<E> getRightMost(JuzerBranch<E> target) {
		if (target == null)
			return null;
		if (target.Right() == null)
			return target;
		return getLeftMost(target.Right());
	}

	public static void main(String[] args) {
		RBBinarySearchJuzer<Integer> b = new RBBinarySearchJuzer<Integer>();

		ArrayList<Integer> t = new ArrayList<Integer>();
		t.add(3);
		t.add(11);
		t.add(27);
		t.add(18);
		t.add(19);
		t.add(23);
		t.add(41);
		t.add(13);
		t.add(26);
		t.add(37);
		t.add(31);

		for (int i : t) {
			b.addRedBlack(i);
			/*
			 * ArrayList<JuzerBranch<Integer>> jb = new
			 * ArrayList<JuzerBranch<Integer>>(); b.OrderRB(jb);
			 * 
			 * for(JuzerBranch<Integer> j: jb){ System.out.println(j + ": " +
			 * "Parent: " + j.getParent() + ", Gparent: " + j.getGParent() +
			 * "\n"); }
			 */
		}

		System.out.println(b.toStringRB());

	}

	public String toString() {
		ArrayList<E> ar = new ArrayList<E>();

		inOrder(ar);

		return ar.toString();
	}

	public String toStringRB() {
		ArrayList<String> ar = new ArrayList<String>();
		inOrderRB(ar);

		return ar.toString();
	}
}
