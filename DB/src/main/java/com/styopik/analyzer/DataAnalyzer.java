package com.styopik.analyzer;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import com.styopik.service.DataFetcher;

/**
 * Executes DataFetcher's class object in concurrent mode
 * 
 */
public class DataAnalyzer implements Callable<Integer> {

	private File directory;
	private int counter;
	
	private DataFetcher dataFetcher;
	
	public DataAnalyzer(File directory, DataFetcher dataFetcher) {
		this.directory = directory;
		this.dataFetcher = dataFetcher;
	}


	@Override
	public Integer call() throws ExecutionException {
		
		counter = 0;
		ArrayList<Future<Integer>> results = new ArrayList<Future<Integer>>();
		
		if (directory.isDirectory()) {
			
			File[] files = directory.listFiles();
			results = new ArrayList<Future<Integer>>();
			
			if (files != null) {
				for (File file : files) {
				    if (file.isDirectory()) {
				    	DataAnalyzer counter = new DataAnalyzer(file, dataFetcher);
				    	FutureTask<Integer> task = new FutureTask<Integer>(counter);

				    	results.add(task);

				    	Thread t = new Thread(task);
				    	t.start();
				    } else if (file.isFile() && file.getName().endsWith(".txt")) {
				    	dataFetcher.populateDb(file.getAbsolutePath());
				    	counter++;
				    }
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
