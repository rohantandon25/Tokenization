import java.util.*;

public class MonteCristo {

	public TreeMap<String, Integer> toTreeMap(ArrayList<String> list){
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		for(int i =0;i< list.size();i++){
			String word = list.get(i);
			int count = 0;
			if(!map.containsKey(word)){
				map.put(word, count);
			}
			else{
				int val = map.get(word) + 1;
				map.put(word, val);
			}
		}
		return map;
	}
	
	
	public TreeMap<String, Integer> sortTreeMap(TreeMap<String, Integer> map){
		TreeMap<String, Integer> treemap = (TreeMap<String, Integer>) sortByValues(map);
		return treemap;
	}
	
	public static <K, V extends Comparable<V>> Map<K, V> sortByValues(final Map<K, V> map) {
	    Comparator<K> valueComparator =  new Comparator<K>() {
	        public int compare(K k1, K k2) {
	            int compare = map.get(k2).compareTo(map.get(k1));
	            if (compare == 0) return 1;
	            else return compare;
	        }
	    };
	    Map<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
	    sortedByValues.putAll(map);
	    return sortedByValues;
	}
}
