package com.salesforce.tests.fs;

public class FileSystemHandler {

	private static final String PARENT_DIR = "..";
	
	private Directory currentLocation;
	
	public FileSystemHandler(Directory element) {
		this.currentLocation = element;
	}
	
	public String pwd() {
		return currentLocation.getPath();
	}

	public String quit() {
		throw new QuitedException();
	}

	public String make(String dirName) {
		return create(new Directory(dirName, currentLocation));
	}

	private String create(DirectoryElement element) {
		currentLocation.addElement(element);
		return "created " + element.getPath();
	}
	
	public String touch(String fileName) {
		return create(new File(fileName, currentLocation));
	}

	public String list(boolean recursive) {
		return currentLocation.print(recursive);
	}

	public String changeDirectory(String targetDir) {
		if(PARENT_DIR.equals(targetDir)) {
			Directory parentLocation = currentLocation.getParent();
			if(parentLocation == null) {
				return "already located in root";
			}
			currentLocation = parentLocation;
		} else {
			currentLocation = currentLocation.getDirectoryChild(targetDir);
		}
		return "changed directory";
	}
}
