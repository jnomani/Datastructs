package Programs;



import Datastructs.JuzerList;

public class Sorter {

	public static void mergeSort(JuzerList<Comparable> target, int begin, int end){
		if(end - begin < 2) return;
		int mid = (end + begin) / 2;
		
		mergeSort(target, begin, mid); //breaks down array 
		mergeSort(target, mid, end);
		//sorts each individual array and merges the values together.
		merge(target, begin, mid, end);
	}
	
	private static void merge(JuzerList<Comparable> target, int begin, int mid, int end){
		//temp arrays
		Comparable[] tem1 = new Comparable[mid - begin]; //temp arrays
		Comparable[] tem2 = new Comparable[end - mid];
		
		//populate temp arrays
		int d = 0;
	for(int i = begin; i < mid && d < tem1.length; i++, d++){
		tem1[d] = target.get(i);
		
	}
	int c = 0;
	for(int i = mid; i < end && c < tem2.length; i++, c++){
		tem2[c] = target.get(i);
	}
	
	
	int t1i = 0;
	int t2i = 0;
	//Compare values
	for(int i = begin; i < end && t1i < tem1.length ||  t2i < tem2.length; i++){
		if(t1i >= tem1.length){// Checks to see whether the entire array has been added, if so it adds the rest of the other array.
			target.set(i, tem2[t2i]);
			t2i++; 
		}else if(t2i >= tem2.length){
			target.set(i, tem1[t1i]);
			t1i++;
		}else{
	if(tem1[t1i].compareTo(tem2[t2i]) <= 0 ){ //If the values is less, it adds it to target and increments only its index.
		target.set(i, tem1[t1i]);
		t1i++;
	}else{
		target.set(i, tem2[t2i]);
		t2i++;
	}
		}
	}
	//System.out.println(target);
	}
	public static void main(String[] args) {
		JuzerList<Comparable> t= new JuzerList<Comparable>();
		for(int i =0; i < 20; i++){
			t.add((int)(Math.random() * 300));
		}
		System.out.println(t);
		mergeSort(t, 0, 20);
		System.out.println(t);
	}

}
