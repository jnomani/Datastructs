package Datastructs;

public class PriorityQ<E> {
	private static int count = 1;

	Heap<PQNode<E>> back = new Heap<PQNode<E>>(false);
	
	public void push(E data, int priority){
		back.Push(new PQNode<E>(data, priority));
	}
	
	public E pop(){
		PQNode<E> n = back.Pop();
		
		return n.data;
	}

	
	public String toString(){
		return back.toString();
	}
	class PQNode<X> implements Comparable<PQNode<X>> {

		private X data;
		private int priority;
		private int order;

		public PQNode(X x, int priority) {
			data = x;
			this.priority = priority;
			this.order = PriorityQ.count;

			PriorityQ.count++;
		}

		@Override
		public int compareTo(PQNode<X> o) {

			if (priority > o.priority) {
				return 1;
			} else if (priority < o.priority) {
				return -1;
			} else {
				return order - o.order;
			}

		}

		public String toString(){
			return data + " : " + priority + " : " + order;
		}
	}

	
	public static void main(String[] r){
		PriorityQ<Integer> p = new PriorityQ<Integer>();
		
		p.push(3, 1);
		p.push(7, 1);
		p.push(11, 2);
		p.push(91, 3);
		p.push(44, 1);
		p.push(8, 2);
		p.push(16, 1);
		p.push(10, 3);
		p.push(5, 3);
		p.push(11, 1);
		p.push(2, 2);
		p.push(6, 1);
		p.push(9, 2);
		
		System.out.println(p);
		
		for(int i = 0; i < 13; i++){
			System.out.println(p.pop());
			System.out.println(p);
		}
	}
}
