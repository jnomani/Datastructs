package Datastructs;


import java.lang.reflect.Array;
import java.util.*;

public class JuzerList<E> implements List<E> {

	private void setHead(Link<E> h) {
		head = h;
	}

	private void setTail(Link<E> t) {
		tail = t;
	}

	public String toString() {
		String str = "[";
		for (int i = 0; i < size; i++) {

			str += get(i);
			if (size - i > 1)
				str += ", ";
		}
		str += "]";
		return str;
	}
/*
	public static void main(String[] r) {
		JuzerList<Integer> m = new JuzerList<Integer>();
		for (int i = 0; i < 400; i++) {
			m.add(i);
		}
		System.out.println(m);
		
		Collection<Integer> c = new LinkedList<Integer>();
		for(int i = 0; i < 25; i++){
			c.add(i);
		}
		
		
		m.removeAll(c);
		
		System.out.println(m);
	}*/

	private Link<E> head = null;
	private Link<E> tail = null;
	private int size = 0;

	@Override
	public boolean add(E e) {
		// TODO: Simplify Code
		Link<E> link;
		if (tail == null) {
			link = new Link<E>(e, null);
			head = link;
			tail = link;
		} else {
			link = new Link<E>(e, tail);
			tail.setNext(link);
			tail = link;
		}

		size++;
		return true;
	}

	@Override
	public void add(int index, E element) {
		Link<E> add, next = head, prev;

		for (int i = 1; i <= index; i++) {
			next = next.getNext();
		}

		prev = next.getPrev();
		add = new Link<E>(element, prev);
		add.setNext(next);
		prev.setNext(add);
		next.setPrev(add);

		size++;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {

		for (int i = 0; i < c.size(); i++) {
			add(((List<E>) c).get(i));
		}
		return true;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {

		for (int i = 0; i < c.size(); i++, index++) {
			add(index, ((List<E>) c).get(i));
		}

		return true;
	}

	@Override
	public void clear() {
		head = null;
		tail = null;
		size = 0;

	}

	@Override
	public boolean contains(Object o) {

		for (int i = 0; i < size; i++) {
			if (getTarget(i).getData() == o)
				return true;
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {

		for (int i = 0; i < c.size(); i++) {
			int r = -1;
			for (int j = 0; j < size; j++) {
				if (getTarget(j).getData() == ((List<E>) (c)).get(i)) {
					r = j;
					break;
				}
			}
			if (r == -1)
				return false;
		}

		return true;
	}

	@Override
	public E get(int index) {
		Link<E> t = getTarget(index);
		return t.getData();
	}

	@Override
	public int indexOf(Object o) {

		int r = -1;

		for (int i = 0; i < size; i++) {
			if (getTarget(i).getData() == o) {
				r = i;
				break;

			}
		}

		return r;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<E> iterator() {

		return (Iterator<E>) new JuzerListIterator<E>();
	}

	@Override
	public int lastIndexOf(Object o) {
		int r = -1;

		for (int i = size - 1; i >= 0; i--) {
			if (getTarget(i).getData() == o) {
				r = i;
				break;
			}
		}

		return r;
	}

	@Override
	public ListIterator<E> listIterator() {

		return new JuzerListIterator<E>();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		JuzerListIterator<E> m = new JuzerListIterator<E>();
		m.setHead(index);
		return m;
	}

	@Override
	public boolean remove(Object o) {
		int s = size;
		for (int i = 0; i < size; i++) {
			if (getTarget(i).getData() == o) {
				remove(i);
				break;
			}
		}

		return s > size;
	}

	@Override
	public E remove(int index) {
		Link<E> target = getTarget(index), next, prev;

		next = target.getNext();
		prev = target.getPrev();

		if(next != null)
		next.setPrev(prev);
		if (prev != null)
		prev.setNext(next);

		size--;

		return target.getData();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		
		for (int i = 0; i < c.size(); i++) {
			remove(((List<E>) c).get(i));
		}

		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		Iterator it = c.iterator();
		for (int i = 0; i < size; i++) {
			int r = -1;
			for (int j = 0; j < c.size(); j++) {
				if (getTarget(i).getData() == ((List<E>) c).get(j)) {
					r = j;
					break;
				}

			}
		}
		return false;
	}

	@Override
	public E set(int index, E element) {
		
		
		Link<E> target = getTarget(index);
		E e = target.getData();
		target.setData(element);
	
		return e;
	}

	@Override
	public int size() {

		return size;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		JuzerList<E> list = new JuzerList<E>();
		list.setHead(getTarget(fromIndex));
		list.setTail(getTarget(toIndex - 1));
		list.setSize(toIndex - fromIndex);
		return list;
	}

	@Override
	public Object[] toArray() {
		Object[] links = new Object[size];

		for (int i = 0; i < links.length; i++) {
			links[i] = getTarget(i).getData();
		}
		return links;
	}

	@Override
	public <T> T[] toArray(T[] a) {

		if (a.length < size)
			a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
		
		int i = 0;
		
		Object[] result = a;
		
		for (Link<E> e = head.getNext(); e != head; e = e.getNext())
			result[i++] = e.getData();

		if (a.length > size)
			a[size] = null;

		
		return a;
	}

	private Link<E> getTarget(int index) {
		Link<E> target = head;
		for (int i = 1; i <= index; i++) {
			target = target.getNext();
		}
		return target;
	}

	class JuzerListIterator<T> implements ListIterator<T> {

		private Link<T> current = (Link<T>) JuzerList.this.head;
		private int count = 0;

		@Override
		public void add(T e) {
			JuzerList.this.add((E) e);

		}

		@Override
		public boolean hasNext() {

			return current != null;
		}

		@Override
		public boolean hasPrevious() {

			return current.getPrev() != null;
		}

		@Override
		public T next() {
			T t = current.getData();
			current = current.getNext();
			count++;
			return t;
		}

		@Override
		public int nextIndex() {

			return count + 1;
		}

		@Override
		public T previous() {
			T t = current.getData();
			current.getNext();
			count--;
			return t;
		}

		@Override
		public int previousIndex() {

			return count - 1;
		}

		@Override
		public void remove() {
			JuzerList.this.remove(count);
			current = current.getNext();
		}

		@Override
		public void set(T e) {
			JuzerList.this.set(count, (E) e);

		}

		private void setHead(int i) {
			current = (Link<T>) JuzerList.this.getTarget(i);

		}

	}
	
	private void setSize(int i){
		size = i;
	}

	
}
