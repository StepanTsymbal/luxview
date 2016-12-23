package com.styopik.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import com.styopik.model.LineInfo;
import com.styopik.util.DBConnection;

public class DataAnalyzer implements Callable<Integer> {
	
	Connection conn = null;
//	PreparedStatement pst = null;
//	ResultSet rs = null;
//	Statement stmt = null;
	
	private File directory;
	private int counter;
	
	public DataAnalyzer(File directory) {
		this.directory = directory;
	}

	/*
	 * Adds Data to both tables
	 */
	public void populateDb (String fileName) {

		int maxFileId;
		
		checkConnection();

		try {
			
        	conn.setAutoCommit(false);
        				
			List<LineInfo> lineInfoList = getAllLinesFromFile(fileName);
			populateFileTable(fileName, lineInfoList.size());
        	maxFileId = getMaxFileId();
			populateLineTable(lineInfoList, maxFileId);
			
			conn.commit();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	try {
        		if (conn != null) {
    				conn.close();
        		} 
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
	}
	
	/*
	 * Fetches the maximum id from FILE_INFO table
	 */
	private int getMaxFileId() {
		
		int maxFileId = 0;
		final String queryMaxFileId = "select max(file_id) as max_file_id from file_info";
		
        try (Statement stmt = conn.createStatement();
        		ResultSet rs = stmt.executeQuery(queryMaxFileId);) {

			if (rs.next()) {
				maxFileId = rs.getInt("max_file_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return maxFileId;
	}
	
	/*
	 * Counts an average word's length per row
	 */
	public double getAverageWordLength (List<String> list) {
		
		double totalLength = 0;
		
		for (String word : list) {
			totalLength += word.length();
		}
		
		return (totalLength > 0) ? (double)(totalLength/list.size()) : 0;
	}
	
	public List<String> getFilteredList (String line) {
		
		List<String> sortedList = new ArrayList<String>(Arrays.asList(line.split("\\s+")));
		
		Collections.sort(sortedList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
            	return o1.length() - o2.length();
            }
		});
		
		return sortedList;
	}
	
	/*
	 * Fetches all Data from file
	 */
	public List<LineInfo> getAllLinesFromFile (String fileName) {
		
		String line;
		List<String> sortedLineList = null;
		List<LineInfo> lineInfoList = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {            
            while ((line = br.readLine()) != null) {
            	
            	sortedLineList = getFilteredList(line);
            	
            	lineInfoList.add(new LineInfo(line, sortedLineList.get(sortedLineList.size() - 1), sortedLineList.get(0), getAverageWordLength(sortedLineList)));
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return lineInfoList;
	}
	
	private void checkConnection() {
		
		conn = DBConnection.getConnection();
		
		if (conn == null) {
			System.out.println("Connection Failed!");
			System.exit(1);
		} 
	}

	@Override
	public Integer call() throws ExecutionException {
		
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
			    	populateDb(file.getAbsolutePath());
			    	counter++;
			    }
			}
		} else {
			if (directory.isFile() && directory.getName().endsWith(".txt")) {
				populateDb(directory.getAbsolutePath());
				counter++;
			} else if (!directory.exists()) {
				System.out.println("File (Directory) was not found!");
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
		
	/*
	 * Populates FILE_INFO table
	 */
	public void populateFileTable(String fileName, int lineCount) {
		
        final String queryFileInsert = "insert into file_info (name, lines_number) values(?, ?)";
		
        try (PreparedStatement pst = conn.prepareStatement(queryFileInsert);) {
        	
            pst.setString(1, new File(fileName).getAbsolutePath());
            pst.setInt(2, lineCount);
    		pst.executeUpdate();
    		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Populates LINE_INFO table
	 */
	public void populateLineTable(List<LineInfo> listLineInfo, int maxFileId) {
		
        final String queryLineInsert = "insert into line_info (file_id, line, longest_word, shortest_word, average_word_length) values(?, ?, ?, ?, ?)";
	
		try (PreparedStatement pst = conn.prepareStatement(queryLineInsert);) {
			
			for (LineInfo lineInfo : listLineInfo) {
	            pst.setInt(1, maxFileId);
	            pst.setString(2, lineInfo.getLine());
	            pst.setString(3, lineInfo.getLongestWord());
	            pst.setString(4, lineInfo.getShortestWord());
	            pst.setDouble(5, lineInfo.getAverageLength());
	            
				pst.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
