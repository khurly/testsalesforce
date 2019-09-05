package com.salesforce.tests.fs.commands;

import com.salesforce.tests.fs.FileSystemHandler;

public class Quit implements FSCommand {

	private FileSystemHandler handler;

	public Quit(FileSystemHandler handler) {
		this.handler = handler;
	}

	@Override
	public String execute(String... args) {
		return handler.quit();
	}

}
