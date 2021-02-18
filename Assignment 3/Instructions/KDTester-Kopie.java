package assignment3;
import java.util.ArrayList;
import java.util.Iterator;

public class KDTester {

	public static void main(String[] args) throws Exception {
		
		//create a datapoint
		/*int[] coord1 = {2, 1, 0};
		Datum dat1 = new Datum(coord1);
		int[] coord2 = {9, 9, 9};
		Datum dat2 = new Datum(coord2);
		int[]coord3 = {12, 7, 2};
		Datum dat3 = new Datum(coord3);
		ArrayList<Datum> dat1Arr = new ArrayList<Datum>();
		dat1Arr.add(dat1);
		dat1Arr.add(dat2);
		dat1Arr.add(dat3);
		//dat1Arr.add(dat3);
		KDTree KD1 = new KDTree(dat1Arr);
		System.out.println("tree height is " + KD1.height());
		System.out.println("num of leaves is " + KD1.numLeaves);
		System.out.println("num of nodes is " + KD1.countNodes());
		System.out.println("mean depth is " + KD1.meanDepth());*/
		
		/*int[] coord4 = {1};
		Datum dat4 = new Datum(coord4);
		ArrayList<Datum> dat2Arr = new ArrayList<Datum>();
		dat2Arr.add(dat4);
		int[] coord5 = {1};
		Datum dat5 = new Datum(coord5);
		dat2Arr.add(dat5);
		System.out.println(dat2Arr.size());
		KDTree KD2 = new KDTree(dat2Arr);*/
		
		int[] cd1 = {0, -1, 1};
		int[] cd2 = {-2, -1, 1};
		int[] cd3 = {1, -2, 1};
		int[] cd4 = {1, 1, -1};
		int[] cd5 = {-2, 0, -2};
		int[] cd6 = {1, 0, -2};
		int[] cd7 = {1, 0, -2};
		int[] cd8 = {-2, 0, 1};
		int[] cd9 = {1, -1, -2};
		int[] cd10 = {-1, -1, 1};
		
		//int[] cd1 = {1};
		//int[] cd2 = {0};
		
		Datum d1 = new Datum(cd1);
		Datum d2 = new Datum(cd2);
		Datum d3 = new Datum(cd3);
		Datum d4 = new Datum(cd4);
		Datum d5 = new Datum(cd5);
		Datum d6 = new Datum(cd6);
		Datum d7 = new Datum(cd7);
		Datum d8 = new Datum(cd8);
		Datum d9 = new Datum(cd9);
		Datum d10 = new Datum(cd10);
		
		ArrayList<Datum> datArr = new ArrayList<Datum>();
		
		
		datArr.add(d1);
		datArr.add(d2);
		datArr.add(d3);
		datArr.add(d4);
		datArr.add(d5);
		datArr.add(d6);
		datArr.add(d7);
		datArr.add(d8);
		datArr.add(d9);
		datArr.add(d10);
		
		
		KDTree KD = new KDTree(datArr);
		System.out.println("tree height is " + KD.height());
		System.out.println("num of leaves is " + KD.numLeaves);
		System.out.println("num of nodes is " + KD.countNodes());
		System.out.println("mean depth is " + KD.meanDepth());
		
		Iterator<Datum> it = KD.iterator();
		
		Datum d = it.next();
		System.out.println(d);
		Datum e = it.next();
		System.out.println(e);
		Datum f = it.next();
		System.out.println(f);
		Datum g = it.next();
		System.out.println(g);
		//Datum h = it.next();
		//System.out.println(h);

	}

}
