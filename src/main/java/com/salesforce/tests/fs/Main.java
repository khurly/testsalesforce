package com.salesforce.tests.fs;

import static java.util.Arrays.copyOfRange;

import java.io.PrintWriter;
import java.util.Scanner;

import com.salesforce.tests.fs.commands.FSCommand;
import com.salesforce.tests.fs.exceptions.DuplicateElementException;
import com.salesforce.tests.fs.exceptions.UnrecognizeArgumentException;
import com.salesforce.tests.fs.exceptions.UnrecognizedCommandException;

/**
 * The entry point for the Test program
 */
public class Main {

	public static void main(String[] args) {
		Directory root = new Directory("root", null);
		FileSystemHandler fsHandler = new FileSystemHandler(root);
		CommandInterpreter commandInterpreter = new CommandInterpreter(fsHandler);
		
		PrintWriter out = new PrintWriter(System.out, true);
		try (Scanner scanner = new Scanner(System.in)) {
			while (scanner.hasNext()) {
				String commandLine = scanner.nextLine();
				String[] splitCommand = commandLine.split("\\s+");
				FSCommand fsCommand = commandInterpreter.resolveCommand(splitCommand[0]);
				String outText = fsCommand.execute(copyOfRange(splitCommand, 1, splitCommand.length));
				out.println(outText);
			}
		} catch (QuitedException e) {
			// finished
		} catch (UnrecognizeArgumentException e) {
			out.println("wrong arguments");
		} catch (UnrecognizedCommandException e) {
			out.println("wrong command");
		} catch (DuplicateElementException e) {
			out.println("element already exists");
		}
	}
}
