package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.MessageFormat;

@Data
@NoArgsConstructor
public class Customer {
	private Integer id;
	private String name;
	private String surname;
	private String email;

	public String toString(){
		return MessageFormat.format("[ id:{0}, name:{1}, surname:{2}, email:{3} ]", id, name, surname, email);
	}
}