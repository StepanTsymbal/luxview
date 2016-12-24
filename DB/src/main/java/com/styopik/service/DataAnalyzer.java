package com.styopik.service;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Executes DataFetcher's class object in concurrent mode
 * 
 */
public class DataAnalyzer implements Callable<Integer> {

	private File directory;
	private int counter;
	
	public DataAnalyzer(File directory) {
		this.directory = directory;
	}


	@Override
	public Integer call() throws ExecutionException {
		
		DataFetcher dataFetcher = new DataFetcher();
		
		counter = 0;
		ArrayList<Future<Integer>> results = new ArrayList<Future<Integer>>();
		
		if (directory.isDirectory()) {
			
			File[] files = directory.listFiles();
			results = new ArrayList<Future<Integer>>();
			
			for (File file : files) {
			    if (file.isDirectory()) {
			    	DataAnalyzer counter = new DataAnalyzer(file);
			    	FutureTask<Integer> task = new FutureTask<Integer>(counter);

			    	results.add(task);

			    	Thread t = new Thread(task);
			    	t.start();
			    } else if (file.isFile() && file.getName().endsWith(".txt")) {
			    	dataFetcher.populateDb(file.getAbsolutePath());
			    	counter++;
			    }
			}
		} else {
			if (directory.isFile() && directory.getName().endsWith(".txt")) {
				dataFetcher.populateDb(directory.getAbsolutePath());
				counter++;
			} else if (!directory.exists()) {
				System.out.println("File (Directory) has not been found!");
			}
		}
		for (Future<Integer> result : results) {
		    	try {
					counter += result.get();
				} catch (InterruptedException  e) {
					e.printStackTrace();
				}
		}

		return counter;
	}

}
