package com.styopik.model;

public class FileInfo {
	
	private int id;
	private String name;
	private int linesNumber;
	
	public FileInfo() {
		
	}

	public FileInfo(int id, String name, int linesNumber) {
		super();
		this.id = id;
		this.name = name;
		this.linesNumber = linesNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLinesNumber() {
		return linesNumber;
	}

	public void setLinesNumber(int linesNumber) {
		this.linesNumber = linesNumber;
	}

	@Override
	public String toString() {
		return "FileInfo [id=" + id + ", name=" + name + ", linesNumber=" + linesNumber + "]";
	}

}
