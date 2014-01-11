package Datastructs;

import java.util.ArrayList;

public class JuzerStack<E> extends ArrayList<E> {

	public boolean empty(){
		return size() == 0;
	}
	
	public E peek(){
		return get(size() - 1);
	}
	
	public E pop(){
		E e = get(size() - 1);
		remove(size() - 1);
		return e;
		
	}
	
	public E push(E e){
		add(e);
		return e;
	}
	
	public int search(E e){
		int i = indexOf(e) + 1;
		
		return i;
	}
	
	public static void main(String[] arg){
		JuzerStack<Integer> s = new JuzerStack<Integer>();
		
		for (int j = 0; j < 25; j++) {
			s.push(j);
		}
		
		int i = s.pop();
		System.out.println(i);
	}
}
