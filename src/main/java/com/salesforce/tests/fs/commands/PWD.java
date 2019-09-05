package com.salesforce.tests.fs.commands;

import com.salesforce.tests.fs.FileSystemHandler;

public class PWD implements FSCommand{
	
	private FileSystemHandler handler;
	
	public PWD(FileSystemHandler handler) {
		this.handler = handler;
	}
	
	
	public String execute(String... args) {
		return handler.pwd();
	}

}
