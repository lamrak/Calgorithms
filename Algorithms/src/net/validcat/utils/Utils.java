package net.validcat.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class Utils {

	public static int[] readArrayFromFile(String path, int arrayLength)
			throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		int[] inputArray = new int[arrayLength];
		int i = 0;
		String line;
		while ((line = br.readLine()) != null) {
			inputArray[i++] = Integer.parseInt(line);
		}
		br.close();

		return inputArray;
	}

	public static List<int[]> readDataFromFileAsList(String path) throws IOException {
		List<int[]> list = new ArrayList<int[]>(200);

		BufferedReader br = new BufferedReader(new FileReader(path));
		String line;
		while ((line = br.readLine()) != null) {
			int[] row = convertStringArraytoIntArray(line.split("\\s+")); //Integer.parseInt(line);
			list.add(row);
		}
		br.close();

		return list;
	}
	
	public static int[][] readArraysFromFileAsArrays(String path) throws IOException {
		int[][] results = new int[200][];
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line;
		while ((line = br.readLine()) != null) {
			int[] row = convertStringArraytoIntArray(line.split("\\s+"));
			results[row[0]-1] = row; 
			for (int j = 0; j < row.length-1; j++) {
				results[row[0]-1][j+1] = row[j+1];
			}
		}
		br.close();
		
		return results;
	}

	public static int getRandom(int from, int to) {
		Random random = new Random();
		return (int) (((long) (((long) to - (long) from + 1) * random.nextDouble())) + from);
	}

	public static int[] convertStringArraytoIntArray(String[] sarray) {
		if (sarray != null) {
			int intarray[] = new int[sarray.length];
			for (int i = 0; i < sarray.length; i++) {
				intarray[i] = Integer.parseInt(sarray[i]);
			}
			return intarray;
		}
		return null;
	}

}
