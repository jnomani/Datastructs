package Datastructs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class JuzerMap<K extends Comparable, V> implements Map<K, V> {

	private final RBBinarySearchJuzer<MapEntry<K, V>> tree = new RBBinarySearchJuzer<MapEntry<K, V>>();

	private int size = 0;

	@Override
	public void clear() {
		tree.clear();

	}


	
	public static void main(String[] args){
		JuzerMap<String, Integer> m = new JuzerMap<String, Integer>();
		
	}

	@Override
	public boolean containsKey(Object arg0) {
		// TODO Auto-generated method stub
		return !(tree.get(new MapEntry<K,V>((K)arg0)) == null);
	}

	@Override
	public boolean containsValue(Object arg0) {
		ArrayList<MapEntry<K,V>> ar = new ArrayList<MapEntry<K,V>>();
		tree.inOrder(ar);
		V k = null;
		for(MapEntry<K,V> j: ar){
			if(j.getValue() == (V)arg0){
				k = j.getValue();
				break;
			}
		}
		return k != null;
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		TreeSet<Entry<K,V>> tr = new TreeSet<Entry<K,V>>();
		ArrayList<MapEntry<K,V>> m = new ArrayList<MapEntry<K,V>>();
		tree.inOrder(m);
		for(Entry<K,V> map: m){
			tr.add(map);
		}
		return tr;
	}

	@Override
	public V get(Object arg0) throws ClassCastException {

		MapEntry<K, V> target = tree.get(new MapEntry((K) arg0));

		return target.getValue();
	}

	@Override
	public boolean isEmpty() {

		return tree.isEmpty();
	}

	@Override
	public Set<K> keySet() {
		TreeSet<K> tr = new TreeSet<K>();
		ArrayList<MapEntry<K,V>> m = new ArrayList<MapEntry<K,V>>();
		tree.inOrder(m);
		for(MapEntry<K,V> k: m){
			tr.add(k.getKey());
		}
		
		return tr;
	}

	@Override
	public V put(K arg0, V arg1) {
		MapEntry<K, V> m = new MapEntry<K, V>(arg0, arg1);
		tree.add(m);
		size++;
		return arg1;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> arg0) {
		TreeSet<Entry<K,V>> tr = (TreeSet)arg0.entrySet();
		for(Entry<K,V> e: tr){
			tree.add((MapEntry<K,V>)e);
		}
		
		
	}

	@Override
	public V remove(Object arg0) throws ClassCastException {
		MapEntry<K, V> target = tree.get(new MapEntry((K) arg0));

		tree.remove(target);

		size--;
		return target.getValue();
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public Collection<V> values() {

		ArrayList<MapEntry<K, V>> ar = new ArrayList<MapEntry<K, V>>();
		tree.postOrder(ar);
		ArrayList<V> v = new ArrayList<V>();
		for (MapEntry<K, V> e : ar) {
			v.add(e.getValue());
		}

		return (Collection<V>) v;
	}
	
	public String toString(){
		ArrayList<MapEntry<K,V>> ar = new ArrayList<MapEntry<K,V>>();
		tree.inOrder(ar);
		return ar.toString();
	}

	class MapEntry<X extends Comparable, Y> implements
			Comparable<MapEntry<X, Y>>, Entry<X, Y> {

		private X key;
		private Y value;

		private MapEntry(X key) {
			this.key = key;
		}

		public MapEntry(X key, Y value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public X getKey() {
			// TODO Auto-generated method stub
			return key;
		}

		@Override
		public Y getValue() {
			// TODO Auto-generated method stub
			return value;
		}

		@Override
		public Y setValue(Y value) {
			Y y = this.value;
			this.value = value;
			return y;
		}

		@Override
		public int compareTo(MapEntry<X, Y> o) {

			return key.compareTo(o.key);
		}

		public boolean equals(Object o) {
			return key.equals(((MapEntry) o).key);
		}
		
		public String toString(){
			return  getKey() + ": " + getValue() ;
		}
	}

	
}
