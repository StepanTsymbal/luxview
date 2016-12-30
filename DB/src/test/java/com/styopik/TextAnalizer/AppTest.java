package com.styopik.TextAnalizer;

import java.util.ArrayList;
import java.util.List;

import com.styopik.model.LineInfo;
import com.styopik.service.impl.DataFetcherImpl;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase {

	public static final String PATH_TO_FILE = "src/test/resources/test.txt";
	
	DataFetcherImpl dataFetcher = new DataFetcherImpl();
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    public void testGetFilteredList() {
    	
    	String testString = "1 333 22";
    	List<String> sortedList = new ArrayList<>();
    	sortedList.add("1");
    	sortedList.add("22");
    	sortedList.add("333");
    	
    	assertEquals(dataFetcher.getFilteredList(testString), sortedList);
    }
    
    public void testGetAverageWordLength() {
    	
    	List<String> sortedList1 = new ArrayList<>();
    	sortedList1.add("1");
    	sortedList1.add("22");
    	sortedList1.add("333");
    	assertEquals(dataFetcher.getAverageWordLength(sortedList1), 2.0);
    	
    	List<String> sortedList2 = new ArrayList<>();
    	sortedList2.add("1");
    	sortedList2.add("");
    	sortedList2.add("333");
    	assertEquals(dataFetcher.getAverageWordLength(sortedList2), 4/3.0);
    }
    
    public void testGetAllLinesFromFile() {
    	
    	List<LineInfo> lineInfoList = dataFetcher.getAllLinesFromFile(PATH_TO_FILE);
    	
    	LineInfo lineInfo1 = new LineInfo("one apple g", "apple", "g", 3.0);
    	LineInfo lineInfo2 = new LineInfo("black white ha-ha-ha !!!", "ha-ha-ha", "!!!", 21/4.0);
    	
    	/*
    	 * test.txt:
    	 * 
    	 * one apple g
		 * black white ha-ha-ha !!!
		 * 
    	 */

    	assertEquals(lineInfoList.size(), 2);
    	assertEquals(lineInfo1, lineInfoList.get(0));
    	assertEquals(lineInfo2, lineInfoList.get(1));
	
    }
    
}
