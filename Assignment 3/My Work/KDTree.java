package Assignment3;

/*
*   STUDENT NAME      :  Ryan Sowa
*   STUDENT ID        :  260886668
*/

import java.util.ArrayList;
import java.util.Iterator;
public class KDTree implements Iterable<Datum>{ 

	KDNode 		rootNode;
	int    		k; 
	int			numLeaves;
	
	// constructor

	public KDTree(ArrayList<Datum> datalist) throws Exception {

		Datum[]  dataListArray  = new Datum[ datalist.size() ]; 

		if (datalist.size() == 0) {
			throw new Exception("Trying to create a KD tree with no data");
		}
		else
			this.k = datalist.get(0).x.length;

		int ct=0;
		for (Datum d :  datalist) {
			dataListArray[ct] = datalist.get(ct);
			ct++;
		}
		
	//   Construct a KDNode that is the root node of the KDTree.

		rootNode = new KDNode(dataListArray);
	}
	
	//   KDTree methods
	
	public Datum nearestPoint(Datum queryPoint) {
		return rootNode.nearestPointInNode(queryPoint);
	}
	

	public int height() {
		return this.rootNode.height();	
	}

	public int countNodes() {
		return this.rootNode.countNodes();	
	}
	
	public int size() {
		return this.numLeaves;	
	}

	//-------------------  helper methods for KDTree   ------------------------------

	public static long distSquared(Datum d1, Datum d2) {

		long result = 0;
		for (int dim = 0; dim < d1.x.length; dim++) {
			result +=  (d1.x[dim] - d2.x[dim])*((long) (d1.x[dim] - d2.x[dim]));
		}
		// if the Datum coordinate values are large then we can easily exceed the limit of 'int'.
		return result;
	}

	public double meanDepth(){
		int[] sumdepths_numLeaves =  this.rootNode.sumDepths_numLeaves();
		return 1.0 * sumdepths_numLeaves[0] / sumdepths_numLeaves[1];
	}
	
	class KDNode { 

		boolean leaf;
		Datum leafDatum;           //  only stores Datum if this is a leaf
		
		//  the next two variables are only defined if node is not a leaf

		int splitDim;      // the dimension we will split on
		int splitValue;    // datum is in low if value in splitDim <= splitValue, and high if value in splitDim > splitValue  

		KDNode lowChild, highChild;   //  the low and high child of a particular node (null if leaf)
		  //  You may think of them as "left" and "right" instead of "low" and "high", respectively

		KDNode(Datum[] datalist) throws Exception{
			
			

			/*
			 *  This method takes in an array of Datum and returns 
			 *  the calling KDNode object as the root of a sub-tree containing  
			 *  the above fields.
			 */
			
			
			int datalistLength = datalist.length;
			
			if (datalistLength == 0) {
				
				throw new Exception();
			}
			
			
			if (datalistLength == 1) {
				
				leaf = true;
				
				leafDatum = datalist[0];
				
				numLeaves++;
				
				return;
											
			} 
									
			
			
			int[] min = new int[k];
			int[] max = new int[k];
			
			System.arraycopy(datalist[0].x, 0, min, 0, k);
			System.arraycopy(datalist[0].x, 0, max, 0, k);
			
			
			
			for (Datum d: datalist) {
				

				for (int j = 0; j < k; j++) {
				
					if (d.x[j] > max[j] ) {
						
						max[j] = d.x[j];
						
					}
						
				}
				
			}
			
			
			
			for (Datum d: datalist) {
				
				for (int l = 0; l < k; l++) {
				
					if (d.x[l] < min[l] ) {

						min[l] = d.x[l];
						
					}
						
				}
				
			}
			
			
			
			int range = 0;
			int difference;
			
			for (int m = 0; m < k; m++) {
				
				difference = max[m] - min[m];
				
				if (difference > range) {
					
					range = difference;
					
					this.splitDim = m;
					
				}
				
			
			}
			
			
			
			if (range == 0) {
						
				leaf = true;
					
				leafDatum = datalist[0];
								
				numLeaves++;	
					
				return;
				
				
			}
			
			
			
				splitValue = ((min[splitDim]) + max[splitDim])/2; 
				
				if (splitValue < 0 ) {
					
					splitValue = splitValue - 1;
					
				}
				
				if (splitValue == 0 && min[splitDim] == -1) {
					
					splitValue = -1;
				}


			Datum[] lowerOversizedArray = new Datum[datalist.length];
			Datum[] upperOversizedArray = new Datum[datalistLength];

			int lowerArrayIndex = 0;
			int upperArrayIndex = 0;

			for (Datum datum: datalist) {
				if (datum.x[splitDim] <= splitValue) {
					lowerOversizedArray[lowerArrayIndex] = datum;
					lowerArrayIndex++;
				} else {
					upperOversizedArray[upperArrayIndex] = datum;
					upperArrayIndex++;
				}
			}

			Datum[] lowerArray = new Datum[lowerArrayIndex];
			Datum[] upperArray= new Datum[upperArrayIndex];

			System.arraycopy(lowerOversizedArray, 0, lowerArray, 0, lowerArrayIndex);
			System.arraycopy(upperOversizedArray, 0, upperArray, 0, upperArrayIndex);

			
			
				lowChild = new KDNode(lowerArray);
				highChild = new KDNode(upperArray);
				
			
		
		}

		public Datum nearestPointInNode(Datum queryPoint) {
			Datum nearestPoint, nearestPoint_otherSide;
					
				if (this.leaf) {
					
					nearestPoint = leafDatum;
					
					return nearestPoint;
										
					
				}
				
				double splitDistance = Math.pow(((double)queryPoint.x[splitDim] - splitValue),2);
				
				if (queryPoint.x[splitDim] <= splitValue) {
									
					nearestPoint = lowChild.nearestPointInNode(queryPoint);
														
					long distance = distSquared(nearestPoint,queryPoint);
					
					if (splitDistance < distance) {
						
						nearestPoint_otherSide = highChild.nearestPointInNode(queryPoint);
						
						long distOtherSide = distSquared(nearestPoint_otherSide,queryPoint);
						
						if (distOtherSide < distance) {
							
							return nearestPoint_otherSide;
							
						}
						
					}
					
					return nearestPoint;
		
					
				}
				
				else {
					
					
					nearestPoint = highChild.nearestPointInNode(queryPoint);
																				
					long distance = distSquared(nearestPoint,queryPoint);
					
					if (splitDistance < distance) {
						
						nearestPoint_otherSide = lowChild.nearestPointInNode(queryPoint);
						
						long distOtherSide = distSquared(nearestPoint_otherSide,queryPoint);
						
						if (distOtherSide < distance) {
							
							return nearestPoint_otherSide;
							
						}
						
								
					}
					
					return nearestPoint;
		
					
				}
				
				
				
						
								
		}
		
		// -----------------  KDNode helper methods (might be useful for debugging) -------------------

		public int height() {
			if (this.leaf) 	
				return 0;
			else {
				return 1 + Math.max( this.lowChild.height(), this.highChild.height());
			}
		}

		public int countNodes() {
			if (this.leaf)
				return 1;
			else
				return 1 + this.lowChild.countNodes() + this.highChild.countNodes();
		}
		
		/*  
		 * Returns a 2D array of ints.  The first element is the sum of the depths of leaves
		 * of the subtree rooted at this KDNode.   The second element is the number of leaves
		 * this subtree.    Hence,  I call the variables  sumDepth_size_*  where sumDepth refers
		 * to element 0 and size refers to element 1.
		 */
				
		public int[] sumDepths_numLeaves(){
			int[] sumDepths_numLeaves_low, sumDepths_numLeaves_high;
			int[] return_sumDepths_numLeaves = new int[2];
			
			/*     
			 *  The sum of the depths of the leaves is the sum of the depth of the leaves of the subtrees, 
			 *  plus the number of leaves (size) since each leaf defines a path and the depth of each leaf 
			 *  is one greater than the depth of each leaf in the subtree.
			 */
			
			if (this.leaf) {  // base case
				return_sumDepths_numLeaves[0] = 0;
				return_sumDepths_numLeaves[1] = 1;
			}
			else {
				sumDepths_numLeaves_low  = this.lowChild.sumDepths_numLeaves();
				sumDepths_numLeaves_high = this.highChild.sumDepths_numLeaves();
				return_sumDepths_numLeaves[0] = sumDepths_numLeaves_low[0] + sumDepths_numLeaves_high[0] + sumDepths_numLeaves_low[1] + sumDepths_numLeaves_high[1];
				return_sumDepths_numLeaves[1] = sumDepths_numLeaves_low[1] + sumDepths_numLeaves_high[1];
			}	
			return return_sumDepths_numLeaves;
		}
		
	}

	public Iterator<Datum> iterator() {
		return new KDTreeIterator();
	}
	
			
	
	private class KDTreeIterator implements Iterator<Datum> {
		
		
		
		public void inOrder(KDNode root) {
			
						
				
			if (root.leaf) {
									
					
					
				arrayListLeaves.add(root.leafDatum);
				
				
				return; 
			}
			
				
			inOrder(root.lowChild);
			inOrder(root.highChild);
					
				
					

				
				
				
			
			
		}
		
		
		int i = 0;
		
		
		ArrayList<Datum> arrayListLeaves = new ArrayList<Datum>();
		
	
		// Construct a constructor for KDTreeIterator
		
		public KDTreeIterator() {
					
			inOrder(rootNode);
						
		}
			
		
			
		
		public boolean hasNext() {
			
			
			
			
			// if the size of leavesArray is greater than or equal to the index + 1
				// return true
			
			if (arrayListLeaves.size() >= i + 1) {
				
				return true;
				
			} else {
			
			
				return false;
			}
			
		}

		
		public Datum next() {
			
											
			Datum nextNode = arrayListLeaves.get(i);
			
			// increment index
			
			i++;
			
			// return nextNode
			
			
			
			return nextNode;
		}
		
		
		

	}

}

