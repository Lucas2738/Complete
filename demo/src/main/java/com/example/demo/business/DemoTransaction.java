package com.example.demo.business;

public interface DemoTransaction {
	
	public void readUncommitted();
	public void phantomRead();
	public void nonRepeatableRead();
}
