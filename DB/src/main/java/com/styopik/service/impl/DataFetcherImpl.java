package com.styopik.service.impl;

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

import com.styopik.model.LineInfo;
import com.styopik.service.DataFetcher;
import com.styopik.util.DBConnection;

/**
 * Fetches all Data from text file and puts them into DB
 *
 */
public class DataFetcherImpl implements DataFetcher {
	
	private Connection conn;
	
    private static final String QUERY_FILE_INSERT = "insert into file_info (name, lines_number) values(?, ?)";
    private static final String QUERY_LINE_INSERT = "insert into line_info (file_id, line, longest_word, shortest_word, average_word_length) values(?, ?, ?, ?, ?)";

	/*
	 * Adds Data to both tables
	 */
	public void populateDb(String fileName) {

		int maxFileId;
		List<LineInfo> lineInfoList;
		
		checkConnection();

		try {
			
        	conn.setAutoCommit(false);
        				
			lineInfoList = getAllLinesFromFile(fileName);
			populateFileTable(fileName, lineInfoList.size());
        	maxFileId = getMaxFileId();
			populateLineTable(lineInfoList, maxFileId);
			
			conn.commit();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } 
//		finally {
//        	try {
//        		if (conn != null) {
//    				conn.close();
//        		} 
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//        }
	}
	
	/*
	 * Populates FILE_INFO table
	 */
	public void populateFileTable(String fileName, int lineCount) {
		
        try (PreparedStatement pst = conn.prepareStatement(QUERY_FILE_INSERT);) {
        	
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
	
		try (PreparedStatement pst = conn.prepareStatement(QUERY_LINE_INSERT);) {
			
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
	
	/*
	 * Fetches the maximum id from FILE_INFO table
	 */
	private int getMaxFileId() {
		int maxFileId = 0;
		final String queryMaxFileId = "select max(file_id) as max_file_id from file_info";
		
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(queryMaxFileId);) {

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
	
	/*
	 * Filters Llist<String> by length
	 */
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
    	String longestWord;
    	String shortestWord;
		
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			
            while ((line = br.readLine()) != null) {
            	
            	sortedLineList = getFilteredList(line);
            	
            	if (sortedLineList.size() != 0) {
            		longestWord = sortedLineList.get(sortedLineList.size() - 1);
            		shortestWord = sortedLineList.get(0);
            	} else {
            		longestWord = "";
            		shortestWord = "";
            	}
            	
            	lineInfoList.add(new LineInfo(line, longestWord, shortestWord, getAverageWordLength(sortedLineList)));
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return lineInfoList;
	}
	
	/*
	 * Connects to DB
	 */
	private void checkConnection() {
		conn = DBConnection.getConnection();
		
		if (conn == null) {
			System.out.println("Connection Failed!");
			System.exit(1);
		} 
	}

}
