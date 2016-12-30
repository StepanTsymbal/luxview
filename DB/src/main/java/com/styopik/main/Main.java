package com.styopik.main;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import com.styopik.analyzer.DataAnalyzer;
import com.styopik.service.impl.DataFetcherImpl;

public class Main {
	
    public static void main( String[] args ) {

        Scanner in = new Scanner(System.in);
        
        System.out.println("Enter file name or directory for scanning");
        
        DataAnalyzer countFiles = new DataAnalyzer(new File(in.nextLine()), new DataFetcherImpl());
        FutureTask<Integer> tsk = new FutureTask<Integer>(countFiles);
        Thread thread = new Thread(tsk);

        thread.start();

        try {
        	System.out.println(tsk.get() + " processed file(s).");
        } catch (InterruptedException | ExecutionException e) {
        	e.printStackTrace();
        }
        System.out.println("Data scanning has been finished");
    }
}
