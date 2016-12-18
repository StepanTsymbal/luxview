package com.spr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
@Table(name = "line_info")
public class LineInfo {
	
	@Id
	@GeneratedValue
	@Column(name = "line_id")
	private int id;
	
	@Column(name = "line")
	private String line;
	
	@Column(name = "longest_word")
	private String longestWord;
	
	@Column(name = "shortest_word")
	private String shortestWord;
	
	@Column(name = "average_word_length")
	private double averageWordLength;
	
	@JsonBackReference
	@ManyToOne(optional = false)
	@JoinColumn(name = "file_id")
	private FileInfo fileInfo;

	public LineInfo() {
	}

	public LineInfo(String line, String longestWord, String shortestWord, double averageWordLength) {
		this.line = line;
		this.longestWord = longestWord;
		this.shortestWord = shortestWord;
		this.averageWordLength = averageWordLength;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public double getAverageWordLength() {
		return averageWordLength;
	}

	public void setAverageWordLength(double averageWordLength) {
		this.averageWordLength = averageWordLength;
	}

	public FileInfo getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}

	@Override
	public String toString() {
		return "LineInfo [line=" + line + ", longestWord=" + longestWord + ", shortestWord=" + shortestWord
				+ ", averageWordLength=" + averageWordLength + "]";
	}	

}
