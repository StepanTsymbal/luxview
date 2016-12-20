package com.spr.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonManagedReference;

@Entity
@Table(name = "FILE_INFO")
public class FileInfo {
	
	@Id
	@GeneratedValue
	@Column(name = "file_id")
	private int id;
	
	@Column(name = "name")
	private String fileName;
	
	@Column(name = "lines_number")
	private double linesNumber;
	
	@JsonManagedReference
    @OneToMany(mappedBy = "fileInfo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<LineInfo> lineInfoList = new ArrayList<>();
	
	public FileInfo() {
	}

	public FileInfo(int id, String fileName, double linesNumber) {
		this.id = id;
		this.fileName = fileName;
		this.linesNumber = linesNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public double getLinesNumber() {
		return linesNumber;
	}

	public void setLinesNumber(double linesNumber) {
		this.linesNumber = linesNumber;
	}

	public List<LineInfo> getLineInfoList() {
		return lineInfoList;
	}

	public void setLineInfoList(List<LineInfo> lineInfoList) {
		this.lineInfoList = lineInfoList;
	}

	@Override
	public String toString() {
		return "FileInfo [id=" + id + ", fileName=" + fileName + ", linesNumber=" + linesNumber + "]";
	}

}
