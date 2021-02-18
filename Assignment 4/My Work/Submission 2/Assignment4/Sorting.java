package Sorting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry; // You may need it to implement fastSort

public class Sorting {

	/*
	 * This method takes as input an HashMap with values that are Comparable. 
	 * It returns an ArrayList containing all the keys from the map, ordered 
	 * in descending order based on the values they mapped to. 
	 * 
	 * The time complexity for this method is O(n^2) as it uses bubble sort, where n is the number 
	 * of pairs in the map. 
	 */
    public static <K, V extends Comparable> ArrayList<K> slowSort (HashMap<K, V> results) {
        ArrayList<K> sortedUrls = new ArrayList<K>();
        sortedUrls.addAll(results.keySet());	//Start with unsorted list of urls

        int N = sortedUrls.size();
        for(int i=0; i<N-1; i++){
			for(int j=0; j<N-i-1; j++){
				if(results.get(sortedUrls.get(j)).compareTo(results.get(sortedUrls.get(j+1))) <0){
					K temp = sortedUrls.get(j);
					sortedUrls.set(j, sortedUrls.get(j+1));
					sortedUrls.set(j+1, temp);					
				}
			}
        }
        return sortedUrls;                    
    }
    
    
	/*
	 * This method takes as input an HashMap with values that are Comparable. 
	 * It returns an ArrayList containing all the keys from the map, ordered 
	 * in descending order based on the values they mapped to. 
	 * 
	 * The time complexity for this method is O(n*log(n)), where n is the number 
	 * of pairs in the map. 
	 */
    public static <K, V extends Comparable> ArrayList<K> fastSort(HashMap<K, V> results) {
    	  	
    	ArrayList<K> sortedUrls = new ArrayList<K>();
        sortedUrls.addAll(results.keySet());	//Start with unsorted list of urls
        
        
        
        sortedUrls = mergeSort(sortedUrls, results);
    	
    	return sortedUrls;
    }
    
    
    
    public static <K, V extends Comparable> ArrayList<K> mergeSort(ArrayList<K> sorted, HashMap<K, V> map) {
    	
    	int N = sorted.size();
    	

        if (N == 1) {
      	  return sorted;
        }
        
        else {
      	  
          int midPoint = (N - 1)/2;
       	  
       	  ArrayList<K> smallerUrls = new ArrayList<K>();
       	  
       	  for (int i = 0; i <= midPoint; i++) {
       		  smallerUrls.add(sorted.get(i));
       	  }
       	  
       	  
       	  
       	  ArrayList<K> largerUrls = new ArrayList<K>();
       	  
       	  for (int i = midPoint + 1; i < N; i++) {
       		  
       		  largerUrls.add(sorted.get(i));
       		  
       	  }
      	 
      	  smallerUrls = mergeSort(smallerUrls,map);
      	  
      	  largerUrls = mergeSort(largerUrls,map);
      	  
      	  return merge(smallerUrls,largerUrls,map);
      	  
        }
    	
    	
    }
    
    public static <K,V extends Comparable> ArrayList<K> merge(ArrayList<K> smallerUrls, ArrayList<K> largerUrls, HashMap<K, V> map) {
    	
    	ArrayList<K> sortedList = new ArrayList<K>();
    	
    	
    	int i = 0;
    	int j = 0;
    	int k = 0;
    	
    	   			
    	while (i != (smallerUrls.size()) && j != (largerUrls.size()))  {
    			
    		if ((map.get(smallerUrls.get(i))).compareTo(map.get(largerUrls.get(j))) < 0) {
    			
    			sortedList.add(largerUrls.get(j));
    			j++;
    			k++;
    			
    		} else {
    			sortedList.add(smallerUrls.get(i));
    			i++;
    			k++;
    		}
    		
    	}
    	
    	
    	if (i == (smallerUrls.size())) {
    		
    		while (j != (largerUrls.size())) {
    			
    			sortedList.add(largerUrls.get(j));
    			j++;
    		}
    		
    	} else {
    		
    		while (i != (smallerUrls.size())) {
    		
    			sortedList.add(smallerUrls.get(i));
    			i++;
    		}
    	}
    			
    	
    	return sortedList;
    	
    	
    }
    
    
    

}