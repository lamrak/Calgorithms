package net.validcat.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
	
	public static long[] readLongArrayFromFile(String path, int arrayLength) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		long[] longArray = new long[arrayLength];
		int i = 0;
		String line;
		while ((line = br.readLine()) != null) {
			longArray[i++] = Long.parseLong(line);
		}
		br.close();
		
		return longArray;
	}
	
	public static List<Long> readLongListFromFile(String path, int arrayLength) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		List<Long> list = new ArrayList<Long>();
		String line;
		while ((line = br.readLine()) != null) {
			list.add(Long.parseLong(line));
		}
		br.close();
		
		((ArrayList<Long>)list).trimToSize();
		return list;
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
	
	public static void printArr(List<int[]> input) {
		for (int[] is : input) {
			System.out.println(Arrays.toString(is));
		}
	}
	
	public static void writeListToFile(List<int[]> input) throws IOException {
		File file = new File("result.txt");
		 
		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}

		PrintWriter writer = new PrintWriter(new FileWriter(file, true));
		
 		for (int[] is : input) {
 			writer.println(Arrays.toString(is));
		}
		
 		writer.close();
	}

	public static void writeToFile(String str) throws IOException {
		File file = new File("result.txt");
		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}
		PrintWriter writer = new PrintWriter(new FileWriter(file, true));
		writer.println(str);
 		writer.close();
	}
	
	public static void createNewFile() throws IOException {
		File file = new File("result.txt");
		file.delete();
	}
	
}
