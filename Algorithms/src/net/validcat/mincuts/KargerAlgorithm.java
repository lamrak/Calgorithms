package net.validcat.mincuts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.validcat.utils.StdRandom;
import net.validcat.utils.Utils;

public class KargerAlgorithm {
	private static long min = 10000; //this value will be replaced by min cuts value 
	
	public static void main(String[] args) throws IOException {
		KargerAlgorithm ka = new KargerAlgorithm();
		for (int i = 0; i < 40000; i++) {
			List<int[]> output = ka.getMinCuts(Utils.readDataFromFileAsList("D:\\android\\Git\\algorithmes\\Algorithms\\kargerMinCut.txt"));
			long temp = (output.get(0).length-1);
			if (temp < min) {min = temp;}
		}
		System.out.println("Min curs: " + min);
	}
	
	public List<int[]> getMinCuts(List<int[]> in) throws IOException {
		while (in.size() > 2) {
			//Select random vertex A
			int i = StdRandom.uniform(0, in.size()-1); //0; Utils.getRandom(0, input.size()-1);
			int[] row = in.get(i);
			int vertexA = row[0];
			//Select random vertex B
			int j = StdRandom.uniform(1, row.length-1); //1; Utils.getRandom(1, row.length-1);				
			int vertexB = row[j]; 

			contraction(in, i, vertexA, vertexB);
			replaceVertexInWholeList(in, vertexB, vertexA);
			((ArrayList<int[]>)in).trimToSize(); //optional
		}
		
		return in;
	}

	/**
	 * Delete (replace) in list all unused vertex (vertexA instead of vertexB)
	 */
	private void replaceVertexInWholeList(List<int[]> input, int vertexB, int vertexA) {
		for (int z = 0; z < input.size(); z++) {
			int[] arr = input.get(z);
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] == vertexB) {
					arr[i] = vertexA;
				}
			}			
		}
	}

	private void contraction(List<int[]> input, int i, int vertexA, int vertexB) throws IOException {
		int[] rowA = input.get(i);
		int[] rowB = getRowByValue(input, vertexB);
		List<Integer> fuseRows = new ArrayList<Integer>();
		fuseRows.add(vertexA);
		addArrayToListExcudeLoops(fuseRows, rowA, vertexA, vertexB);
		addArrayToListExcudeLoops(fuseRows, rowB, vertexA, vertexB);

		//Delete input.remove(vertexB)		
		input.remove(rowA);
		input.remove(rowB);
		
		//Add new int[] row
		input.add(convertIntegers(fuseRows));
	}

	/**
	 * Fuse Array and remove loops
	 * 
	 */
	private void addArrayToListExcudeLoops(List<Integer> fuseRows, int[] row, int vertexA, int vertexB) {
		for (int i = 1; i < row.length; i++) {
			if (row[i] != vertexA && row[i] != vertexB) {
				fuseRows.add(row[i]);
			}
		}
	}
	
	/**
	 * Convert <code>List<Integers></code> to <code>int[]</code>
	 * @return Array <code>int[]</code>
	 */
	public static int[] convertIntegers(List<Integer> integers) {
	    int[] ret = new int[integers.size()];
	    Iterator<Integer> iterator = integers.iterator();
	    for (int i = 0; i < ret.length; i++) {
	        ret[i] = iterator.next().intValue();
	    }
	    return ret;
	}

	/**
	 * Find corresponding row from the list by vertex value.
	 * @return Array int[] for vertexB 
	 */
	private int[] getRowByValue(List<int[]> input, int vertexB) {
		for (int[] arr : input) {
			if (arr[0] == vertexB) return arr; 
		} 
		return null;
	}

}
