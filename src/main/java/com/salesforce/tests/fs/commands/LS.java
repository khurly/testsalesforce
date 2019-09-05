package com.salesforce.tests.fs.commands;

import com.salesforce.tests.fs.FileSystemHandler;
import com.salesforce.tests.fs.exceptions.TooMuchParamertersException;
import com.salesforce.tests.fs.exceptions.UnrecognizeArgumentException;

public class LS implements FSCommand {

	private static final String RECURSIVE_SHORT_COMMAND = "-r";
	private static final String RECURSIVE_COMMAND = "-Recursive";
	
	private FileSystemHandler handler;

	public LS(FileSystemHandler handler) {
		this.handler = handler;
	}
	
	@Override
	public String execute(String... args) {
		boolean recursive = false;
		if (args.length > 1) {
			throw new TooMuchParamertersException();
		} else if (args.length == 1) {
			recursive = true;
			if(!RECURSIVE_SHORT_COMMAND.endsWith(args[0]) && !RECURSIVE_COMMAND.endsWith(args[0])) {
				throw new UnrecognizeArgumentException();
			}
		}
		
		return handler.list(recursive);
	}

}
