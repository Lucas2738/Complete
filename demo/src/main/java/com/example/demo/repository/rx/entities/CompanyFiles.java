package com.example.demo.repository.rx.entities;

import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the company_files database table.
 * 
 */
@Entity
@Table(name="company_files")
@NamedQuery(name="CompanyFiles.findAll", query="SELECT c FROM CompanyFiles c")
public class CompanyFiles implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="file_data")
	private byte[] fileData;

	@Column(name="file_name")
	private String fileName;

	@Column(name="mime_type")
	private String mimeType;

	@Column(name="stock_id")
	private Integer stockId;

	public CompanyFiles() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte[] getFileData() {
		return this.fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMimeType() {
		return this.mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public Integer getStockId() {
		return this.stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

}