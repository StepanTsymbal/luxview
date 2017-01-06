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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + linesNumber;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		FileInfo other = (FileInfo) obj;
		if (linesNumber != other.linesNumber)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FileInfo [id=" + id + ", name=" + name + ", linesNumber=" + linesNumber + "]";
	}

}
