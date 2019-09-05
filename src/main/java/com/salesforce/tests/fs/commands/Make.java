package com.salesforce.tests.fs.commands;

import com.salesforce.tests.fs.FileSystemHandler;
import com.salesforce.tests.fs.exceptions.MissingParameterException;
import com.salesforce.tests.fs.exceptions.TooMuchParamertersException;

public class Make implements FSCommand {

	private FileSystemHandler handler;

	public Make(FileSystemHandler handler) {
		this.handler = handler;
	}
	
	@Override
	public String execute(String... args) {
		if(args.length == 0) {
			throw new MissingParameterException();
		} else if (args.length > 1) {
			throw new TooMuchParamertersException();
		}
		return handler.make(args[0]);
	}
}
