package Datastructs;

public class JuzerBranch<E extends Comparable<E>>{

	private JuzerBranch<E> left;
	private JuzerBranch<E> right;
	private JuzerBranch<E> parent;
	private E data;
	
	{
		color = Color.Red;
	}
	
	public enum Color{
		Black, Red;
	}
	
	private Color color;
	
	public void changeColor(Color c){
		color = c;
	}
	
	public JuzerBranch<E> getParent() {
		return parent;
	}

	public void setParent(JuzerBranch<E> parent) {
		this.parent = parent;
	}

	public JuzerBranch(E e, JuzerBranch<E> p){
		data = e;
		parent = p;
	}
	
	public JuzerBranch(E e){
		data = e;
		
	}
	
	public void setLeft(JuzerBranch<E> l){
		left = l;
	}
	
	public void setRight(JuzerBranch<E> eRightLeft){
		right = eRightLeft;
	}

	public JuzerBranch<E> Left() {
		return left;
		
	}

	public E Data() {
		// TODO Auto-generated method stub
		return data;
	}

	public JuzerBranch<E> Right() {
		// TODO Auto-generated method stub
		return right;
	}
	
	public String toString(){
		return color + " - " + data;
	}
	
	public boolean isRed(){
		return color == Color.Red;
	}
	
	public JuzerBranch<E> getGParent(){
		if(parent != null) return parent.parent;
		else return null;
	}
	
	public JuzerBranch<E> getUncle(){
		JuzerBranch<E> gp = getGParent();
		if(gp != null){
			if(parent == gp.left)
				return gp.right;
			else return gp.left;
		}
		else return null;
		}
	
	public JuzerBranch<E> getSibling(){
		JuzerBranch<E> parent = getParent();
		
		if(this == parent.right){
			return parent.left;
		}else{
			return parent.right;
		}

	}
	
	public boolean isLeaf(){
		if(right == null && left == null){
			return true;
		}else return false;
	}
	
	public boolean isLeftChild(){
		return this == parent.left;
	}
	
	public Color getColor(){
		return color;
	}
}
