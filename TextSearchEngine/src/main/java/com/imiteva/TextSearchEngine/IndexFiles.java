package com.imiteva.TextSearchEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class IndexFiles {

	public Map<String, Integer> getMapFromFile(File folder, String line ) throws FileNotFoundException {
	
		Map<String, Integer> rankMap =  new HashMap<String, Integer>();
		String[] files = folder.list();
		
		for(String file : files) {
			Map<String, String> mapFileContents =  new HashMap<String, String>();
			try (Scanner in = new Scanner(new BufferedReader(new FileReader(folder +"\\"+ file)))) { 
				 while (in.hasNext()) {
					 
					 String fileWord = in.next(); 
					 mapFileContents.put(Normalize(fileWord), file); 
					 
					 double countMatch = 0; 
					 double inputWords;
					 double rank;
	                 
	                 String[] words = line.split(" ");
	                 
	                 for(String word : words) {
	                	if(mapFileContents.containsKey(Normalize(word))) {
	                		countMatch++; 
	                	}
	                 }
	                 
	                 inputWords= words.length; 
	                 
	                 rank = (countMatch/inputWords)*100; 
	                 
	                 if(!(rank == 0)) {
		                	rankMap.put(file, (int) rank);  
		                } 
				    }
				 }
			}
		
		return rankMap;				
	}
	
	static String Normalize(String word) {
		return word.replaceAll("[^\\p{IsAlphabetic}]", "").toLowerCase();
	}
	}

