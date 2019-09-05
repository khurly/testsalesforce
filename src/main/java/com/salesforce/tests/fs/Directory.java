package com.salesforce.tests.fs;

import java.util.HashMap;
import java.util.Map;

import com.salesforce.tests.fs.exceptions.DirectoryElementNotFound;
import com.salesforce.tests.fs.exceptions.DuplicateElementException;

public class Directory extends DirectoryElement {
	private Map<String, DirectoryElement> children;

	public Directory(String name, Directory parent) {
		super(name, parent);
		children = new HashMap<String, DirectoryElement>();
	}

	public void addElement(DirectoryElement element) throws DuplicateElementException {
		if (children.containsKey(element.getName())) {
			throw new DuplicateElementException();
		}
		children.put(element.getName(), element);
	}

	public String print(boolean recursive) {
		return print(recursive, "");
	}

	private String print(boolean recursive, String headerName) {
		StringBuilder resultBuilder = new StringBuilder("");
		for (DirectoryElement element : children.values()) {
			if (!headerName.isEmpty()) {
				resultBuilder.append(headerName).append("/");
			}
			resultBuilder.append(element.getName()).append("\n");
			if (element instanceof Directory) {
				resultBuilder.append(((Directory) element).print(recursive,
						(headerName.isEmpty() ? "" : headerName + "/") + element.getName()));
			}
		}
		return resultBuilder.toString();
	}

	public Directory getDirectoryChild(String targetDir) {
		DirectoryElement directory = children.get(targetDir);
		if (directory == null || !(directory instanceof Directory)) {
			throw new DirectoryElementNotFound();
		}
		return (Directory) directory;
	}
}
