package Datastructs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HashJuzer<K extends Comparable, V> implements Map<K, V> {

	private MapEntry[] structure = new MapEntry[50];
	private int size = 0;

	@Override
	public void clear() {
		structure = new MapEntry[50];
		size = 0;
	}

	@Override
	public boolean containsKey(Object arg0) {
		K k = (K) arg0;

		return get(k) != null;
	}

	@Override
	public boolean containsValue(Object arg0) {

		for (MapEntry<K, V> m : structure) {
			if (m != null) {
				if (m.value.equals((V) arg0))
					return true;
			}
		}
		return false;
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		HashSet<Entry<K, V>> hM = new HashSet<Entry<K, V>>();

		for (MapEntry<K, V> m : structure) {
			if (m != null)
				hM.add(m);
		}
		return (Set<java.util.Map.Entry<K, V>>) hM;

	}

	@Override
	public boolean isEmpty() {
		for (MapEntry<K, V> m : structure) {
			if (m != null)
				return false;
		}
		return true;
	}

	@Override
	public Set<K> keySet() {
		HashSet<K> hK = new HashSet<K>();
		for (MapEntry<K, V> m : structure) {
			if(m != null)
			hK.add(m.key);
		}
		return (Set<K>) hK;
	}

	@Override
	public V put(K k, V v) {
		size++;
		MapEntry<K, V> m = new MapEntry<K, V>(k, v);
		int index = k.hashCode() % structure.length;
		if (structure[index] == null) {
			structure[index] = m;
		} else {
			size--;
			if (structure[index].equals(m)) {
				structure[index] = m;
			} else {
				rebuild();
				put(k, v);
			}
		}

		return v;
	}

	public static void main(String[] ar) {
		HashJuzer<objTester, Integer> m = new HashJuzer<objTester, Integer>();

		m.put(new objTester("fth", 3), 7);
		m.put(new objTester("Juzer", 503), 32);
		m.put(new objTester("Juz6er", 1003), 96);
		m.put(new objTester("Jrtur", 2003), 75);
		m.put(new objTester("Juzer", 503), 66);

		System.out.println(m);
		System.out.println(m.size());
		System.out.println(m.get(new objTester("Juzer", 95)));
		System.out.println(m.containsKey(new objTester("Juer", 2003)));

	}

	private void rebuild() {
		size = 0;
		MapEntry<K, V> tree[] = new MapEntry[structure.length * 2];
		MapEntry<K, V> old[] = structure;
		structure = tree;

		for (MapEntry<K, V> e : old) {
			if (e != null) {
				put(e.key, e.value);

			}
		}
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> arg0) {
		Set<K> sK = (Set<K>) arg0.keySet();
		for (K k : sK) {
			put(k, arg0.get(k));
		}

	}

	@Override
	public V remove(Object arg0) {
		int index = arg0.hashCode() % structure.length;
		V v = (V) structure[index].value;
		structure[index] = null;
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	}

	@Override
	public Collection<V> values() {
		ArrayList<V> r = new ArrayList<V>();
		for (MapEntry<K, V> m : structure) {
			if (m != null)
				r.add(m.value);
		}
		return (Collection<V>) r;
	}

	public String toString() {
		String str = "[";
		for (int i = 0; i < structure.length; i++) {

			if (structure[i] != null)
				str += structure[i].toString() + " , ";

		}
		str = str.substring(0, str.length() - 3);
		str += "] count: " + structure.length;
		return str;
	}

	class MapEntry<X extends Comparable, Y> implements
			Comparable<MapEntry<X, Y>>, Entry<X, Y> {

		X key;
		Y value;

		private MapEntry(X x) {
			key = x;
		}

		public MapEntry(X x, Y y) {
			key = x;
			value = y;
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
			// TODO Auto-generated method stub
			return key.compareTo(o.key);
		}

		@Override
		public boolean equals(Object o) {
			return key.equals(((MapEntry<X, Y>) o).key);
		}

		public String toString() {
			if (value == null)
				return "null";
			return value.toString();
		}
	}

	@Override
	public V get(Object arg0) {
		if (structure[(arg0.hashCode() % structure.length)] == null)
			return null;
		return (V) structure[(arg0.hashCode() % structure.length)].getValue();
	}

}
