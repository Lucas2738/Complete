package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CompanyFiles implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private byte[] fileData;

	private String fileName;

	private String mimeType;

	private Integer stockId;

}