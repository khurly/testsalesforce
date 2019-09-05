package com.salesforce.tests.fs;

import java.util.HashMap;
import java.util.Map;

import com.salesforce.tests.fs.commands.CD;
import com.salesforce.tests.fs.commands.FSCommand;
import com.salesforce.tests.fs.commands.LS;
import com.salesforce.tests.fs.commands.Make;
import com.salesforce.tests.fs.commands.PWD;
import com.salesforce.tests.fs.commands.Quit;
import com.salesforce.tests.fs.commands.Touch;
import com.salesforce.tests.fs.exceptions.UnrecognizedCommandException;

public class CommandInterpreter {

	private Map<String, FSCommand> commands;
	
	public CommandInterpreter(FileSystemHandler fsHandler) {
		commands = new HashMap<>();
		commands.put("quit", new Quit(fsHandler));
		commands.put("pwd", new PWD(fsHandler));
		commands.put("make", new Make(fsHandler));
		commands.put("touch", new Touch(fsHandler));
		commands.put("ls", new LS(fsHandler));
		commands.put("cd", new CD(fsHandler));
	}
	
	public FSCommand resolveCommand(String commandName) {
		FSCommand fsCommand = commands.get(commandName);
		if(fsCommand == null) {
			throw new UnrecognizedCommandException();
		}
		
		return fsCommand;
	}
}
