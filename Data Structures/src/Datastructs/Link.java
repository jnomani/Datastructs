package Datastructs;

public class Link<E> {

	private E data = null;
	private Link<E> next = null;
	private Link<E> prev = null;
	
	
	public Link(E data, Link<E> prev){
		setData(data);
		this.prev = prev;
		
	}
	
	public E getData(){
		return data;
	}
	
	public void setData(E data){
		this.data = data;
	}
	
	public void setNext(Link<E> link){
		next = link;
	}
	
	public Link<E> getNext(){
		return next;
	}
	
	public Link<E> getPrev(){
		return prev;
	}
	
	public void setPrev(Link<E> prev){
		this.prev = prev;
	}
	
	
}
