package net.validcat.mincuts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.validcat.utils.Utils;

public class KargerAlgorithm {
	private static long min = 10000;
	public static void main(String[] args) throws IOException {
		List<int[]> input = Utils.readDataFromFileAsList("D:\\Mine\\Algorithmes\\kargerMinCutTest.txt");
		
		KargerAlgorithm ka = new KargerAlgorithm();
		for (int i = 0; i < 40000; i++) {
			List<int[]> output = ka.getMinCuts(new ArrayList<int[]>(input));
//			System.out.println(Arrays.toString(output.get(0)));
//			System.out.println(Arrays.toString(output.get(1)));
			long temp = (output.get(0).length-1);
			System.out.println("Результат: " + temp);
			if (temp < min) {min = temp;}
		}
		System.out.println("Min curs: " + min);
	}
	
	public List<int[]> getMinCuts(List<int[]> input) {
		if (input.size() < 2) {
			return input;
		}
		
		//Select random vertex A
		int i = Utils.getRandom(0, input.size()-1);
		int[] row = input.get(i);
		int vertexA = row[0];
		
		//Select random vertex B 
		int j = Utils.getRandom(1, row.length-1);
		int vertexB = 0;
		try {
			vertexB = row[j];			
		} catch (Exception e) {
			System.out.println("Cейчас min: " + min);
			System.out.println("Ошибка: " + j);
			System.out.println("Random: 1 till " + (row.length-1));
			e.printStackTrace();
		}

		
		//Add elements from input.get(vertexB) to input.get(vertexA)
 		fuse(input, i, vertexA, vertexB);
		
		//Replace in whole map vertexA instead of vertexB
		replaceVertexInWholeList(input, vertexB, vertexA);
		((ArrayList<int[]>)input).trimToSize();
		return getMinCuts(input);
	}

	private void replaceVertexInWholeList(List<int[]> input, int vertexB, int vertexA) {
		for (int[] arr : input) for (int i = 0; i < arr.length; i++) if (arr[i] == vertexB) arr[i] = vertexA;
	}

	private void fuse(List<int[]> input, int i, int vertexA, int vertexB) {
		int[] rowA = input.get(i);
		int j = 0;
		int[] rowB = getRowByValue(input, vertexB, j);
//		System.out.println("Стягиваем: " + vertexA + " и " + vertexB);
		List<Integer> fuseRows = new ArrayList<Integer>(); 
		//Copy rowA in rowB exclude vertexA and vertexB endpoints (loop)
		fuseRows.add(vertexA);
		addArrayToListExcludeLoop(fuseRows, rowA, vertexA, vertexB);
		addArrayToListExcludeLoop(fuseRows, rowB, vertexA, vertexB);
		
		//Delete input.remove(vertexB)		
		input.remove(rowA);
		input.remove(rowB);
		
		//Add new int[] row
		if (input.size() == 2 && fuseRows.size() < 2) {
			System.out.println();
		}
		input.add(convertIntegers(fuseRows));
	}

	/**
	 * Fuse Array and  delete loops
	 * @param fuseRows
	 * @param row, vertexA, vertexB
	 */
	private void addArrayToListExcludeLoop(List<Integer> fuseRows, int[] row, int vertexA, int vertexB) {
		for (int k : row) if (k != vertexA && k != vertexB) fuseRows.add(k);
	}
	
	/**
	 * Convert <code>List<Integers></code> to <code>int[]</code>
	 * @param integers
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
	 * Find row where first element value values arr[0] and vertexB are equals.
	 * @param input
	 * @param vertexB
	 * @return Array int[] that is correspond vertexB 
	 */
	private int[] getRowByValue(List<int[]> input, int vertexB, int j) {
		for (int[] arr : input) {
			++j;
			if (arr[0] == vertexB) return arr; 
		} 
		return null;
	}

}
