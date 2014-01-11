package Datastructs;

import java.util.ArrayList;

public class Heap<E extends Comparable<E>> {

	private ArrayList<E> heap;
	private boolean isMaxHeap;
	private int size;

	{
		heap = new ArrayList<E>();
		size = 0;
	}
	
	private String debString(){
		return heap.toString();
	}

	public Heap(boolean isMaxHeap) {
		this.isMaxHeap = isMaxHeap;
	}

	public Heap() {
		isMaxHeap = true;
	}

	private boolean Compare(E i, E j) {
		if (isMaxHeap) {
			return i.compareTo(j) >= 0;
		} else {
			return i.compareTo(j) <= 0;
		}
	}

	public String toString() {
		String str = "[";

		for (int i = 0; i < size; i++) {

			str += heap.get(i);
			if (!(i + 1 == size))
				str += ", ";
		}
		return str + "]";
	}

	public void Swap(int i, int j) {

		E temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, temp);
	}

	public void Push(E e) {

//		if (heap.size() > 0)
//			heap.add(size, e);
//		else
			heap.add(e);
		size++;
		if (size > 1)
			heapUp(e);

	}

	public E Pop() {
		E e = heap.get(0);
		Swap(0, size - 1);
		// heap.remove(heap.size() - 1);
		size--;
		if (size > 1)
			heapDown(heap.get(0));

		return e;
	}

	private void heapUp(E e) {
		if (indexOf(e) == 0)
			return;

		int i = indexOf(e);

		int j = (i - 1) / 2;

		if (Compare(e, heap.get(j))) {
			Swap(i, j);
			heapUp(e);
		} else {
			return;
		}
	}

	private int left(int index) {
		return (index + 1) * 2 - 1;
	}

	private int right(int index) {
		return (index + 1) * 2;
	}

	private void heapDown(E e) {

		int i = indexOf(e);
		int left = left(i), right = right(i);

		int target;
		if (left >= size)
			return;
		if (right >= size)
			target = left;
		else
			target = Compare(heap.get(right), heap.get(left)) ? right : left;

		if (Compare(heap.get(target), e)) {
			Swap(target, i);
			heapDown(e);
		} else
			return;
	}

	public static void main(String[] ar) {
		ArrayList<Integer> h = new ArrayList<Integer>();

		h.add(2);
		h.add(9);
		h.add(5);
		h.add(13);
		h.add(11);
		h.add(21);
		h.add(7);

		//System.out.println(h);

		Heap.heapSort(h, false);
		
		System.out.println(h);

	}

	public int Size() {
		return size;
	}

	public void set(ArrayList<E> ar) {
		heap = ar;
		size = ar.size();

		for (int i = size / 2; i >= 0; i--) {
			heapDown(heap.get(i));
		}

	}

	public static <E extends Comparable<E>> void heapSort(ArrayList<E> ar, boolean minToMax) {
		Heap<E> h = new Heap<E>(minToMax);
		
		h.set(ar);

		while (h.size > 0) {
			h.Pop();

		}

		
	}

	private int indexOf(E e) {
		for (int i = 0; i < size; i++) {

			if (heap.get(i).equals(e)) {
				return i;
			}
		}
		return -1;
	}
}