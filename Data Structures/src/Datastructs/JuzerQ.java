package Datastructs;

import java.util.Queue;

public class JuzerQ<E> extends JuzerList<E> implements Queue<E> {

	@Override
	public E element() {
		E e = get(0);
		return e;
	}

	@Override
	public boolean offer(E arg0) {
		add(arg0);
		return true;
	}

	@Override
	public E peek() {
		if(size() > 0) return get(0);
		return null;
	}

	@Override
	public E poll() {
		if(size() > 0){
			E e = get(0);
			remove(0);
			return e;
			
		}
		return null;
	}

	@Override
	public E remove() {
		E e = get(0);
		remove(0);
		return e;
	}
	
	public static void main(String[] ar){
		JuzerQ<Integer> q = new JuzerQ<Integer>();
		for (int i = 7; i < 70; i++) {
			q.offer(i);
		}
		
		int i = q.remove();
		System.out.println(i);
	}

}
