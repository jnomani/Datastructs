package Datastructs;


	public class objTester implements Comparable<objTester>{
		private String value;
		private int i;
		
		public objTester(String v, int i){
			value = v;
			this.i = i;
		}
		
		@Override
		public int hashCode(){
			return i;
		}
		
		@Override
		public boolean equals(Object o){
			return ((objTester)o).value.equals(value);
		}

		@Override
		public int compareTo(objTester arg0) {
			
			return value.compareTo(arg0.value);
		}
		
		public String toString(){
			return value;
		}
	}

