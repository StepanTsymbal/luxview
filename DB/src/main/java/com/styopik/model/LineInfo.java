package com.styopik.model;

public class LineInfo {
	
	private int id;
	private int fileId;
	private String line;
	private String longestWord;
	private String shortestWord;
	private double averageLength;
	
	public LineInfo() {
	}

	public LineInfo(int id, int fileId, String line, String longestWord, String shortestWord, double averageLength) {
		this.id = id;
		this.fileId = fileId;
		this.line = line;
		this.longestWord = longestWord;
		this.shortestWord = shortestWord;
		this.averageLength = averageLength;
	}

	public LineInfo(String line, String longestWord, String shortestWord, double averageLength) {
		this.line = line;
		this.longestWord = longestWord;
		this.shortestWord = shortestWord;
		this.averageLength = averageLength;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getLongestWord() {
		return longestWord;
	}

	public void setLongestWord(String longestWord) {
		this.longestWord = longestWord;
	}

	public String getShortestWord() {
		return shortestWord;
	}

	public void setShortestWord(String shortestWord) {
		this.shortestWord = shortestWord;
	}

	public double getAverageLength() {
		return averageLength;
	}

	public void setAverageLength(double averageLength) {
		this.averageLength = averageLength;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(averageLength);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((line == null) ? 0 : line.hashCode());
		result = prime * result + ((longestWord == null) ? 0 : longestWord.hashCode());
		result = prime * result + ((shortestWord == null) ? 0 : shortestWord.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LineInfo other = (LineInfo) obj;
		if (Double.doubleToLongBits(averageLength) != Double.doubleToLongBits(other.averageLength))
			return false;
		if (line == null) {
			if (other.line != null)
				return false;
		} else if (!line.equals(other.line))
			return false;
		if (longestWord == null) {
			if (other.longestWord != null)
				return false;
		} else if (!longestWord.equals(other.longestWord))
			return false;
		if (shortestWord == null) {
			if (other.shortestWord != null)
				return false;
		} else if (!shortestWord.equals(other.shortestWord))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LineInfo [line=" + line + ", longestWord=" + longestWord + ", shortestWord="
				+ shortestWord + ", averageLength=" + averageLength + "]";
	}
	
}
