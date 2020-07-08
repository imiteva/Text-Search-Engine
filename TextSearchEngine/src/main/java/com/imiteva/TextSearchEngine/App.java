package com.imiteva.TextSearchEngine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App 
{
    public static void main( String[] args ){
       if(args.length == 0) {
    	   throw new IllegalArgumentException("No directory given to index."); 
       } 
    	final File indexableDirectory = new File(args[0]); 
    	  	
    	if (!(indexableDirectory.isDirectory())) {
    		System.out.println("Directory doesn't exist!!");
			System.exit(0);
		} 
    	
    	int fileCount = indexableDirectory.list().length; 
    	
    	if (fileCount == 0) {
    		System.out.println("Directory is empty!!");
			System.exit(0);
    	}
    	
    	System.out.println(fileCount+" files read in directory /"+ indexableDirectory.getName());
    	
    	// TODO: Index all files in indexableDirectory
    	IndexFiles index = new IndexFiles(); 
    	
    	Scanner keyboard = new Scanner(System. in ); 
    	
    	while ( true ) {
    		System. out .print( "search> " );
    		final String line = keyboard.nextLine();
    		// TODO: Search indexed files for words in line
    		 if (":quit".equals(line)) {
	                System.out.println("$");
	                System.exit(0);
	            }
    		 
    		 Map<String, Integer> rank = null; 
    		 
    		 try {
				 rank = index.getMapFromFile(indexableDirectory, line);
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
    		
    		 printRank(rank);
    		
    		}
    			
    }
    
    private static void printRank(Map<String, Integer> rank) {
		  
		final int maxSize = 10;
		  
        if (rank.isEmpty()) { 	        	  
      	  System.out.println("no matches found!");
        } else {
		
      	  Map<String, Integer> sortedByRank = rank.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .limit(maxSize)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                            (oldValue, newValue) -> oldValue, LinkedHashMap::new));
      	  
      	  
          for (Map.Entry<String, Integer> entry : sortedByRank.entrySet()) {
      		System.out.println(entry.getKey() + " : " + entry.getValue()+ "%");
      	}
        }  		  
	  }
	    	
}
