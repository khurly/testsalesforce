package com.salesforce.tests.fs;

public abstract class DirectoryElement {
	private String name;
	private Directory parent;

	public DirectoryElement(String name, Directory parent) {
		this.name = name;
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return (parent != null ? parent.getPath() : "") + "/" + name;
	}

	public Directory getParent() {
		return parent;
	}
}
