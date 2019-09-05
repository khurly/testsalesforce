package com.salesforce.tests.fs;

import java.io.IOException;

import org.junit.Test;

public class FSTest extends BaseTest {

	@Test
	public void testMake() throws IOException {
		String[] expectedResults = { "created /root/dir1\n" };
		runTest(expectedResults, "make dir1", "quit");
	}

	@Test
	public void testTouch() throws IOException {
		String[] expectedResults = { "created /root/file1\n" };
		runTest(expectedResults, "touch file1", "quit");
	}

	@Test
	public void testLS_empty() throws IOException {
		String[] expectedResults = { "\n" };
		runTest(expectedResults, "ls", "quit");
	}

	@Test
	public void testMakeWithLS() throws IOException {
		String[] expectedResults = { "created /root/a\n", "created /root/b\n", "a\n", "b\n", "\n" };
		runTest(expectedResults, "make a", "touch b", "ls", "quit");
	}

	@Test
	public void testMakeWithLSWithRecursion() throws IOException {
		String[] expectedResults = { "created /root/a\n", "created /root/b\n", "a\n", "b\n", "\n" };
		runTest(expectedResults, "make a", "touch b", "ls -r", "quit");
	}

	@Test
	public void testMakeWithLSWithInvalidArgument() throws IOException {
		String[] expectedResults = { "created /root/a\n", "created /root/b\n", "wrong arguments\n" };
		runTest(expectedResults, "make a", "touch b", "ls -x", "quit");
	}

	@Test
	public void testUnrecognizedCommand() throws IOException {
		String[] expectedResults = { "created /root/a\n", "wrong command\n" };
		runTest(expectedResults, "make a", "edit a", "quit");
	}

	@Test
	public void testMakeWithCD() throws IOException {
		String[] expectedResults = { "created /root/a\n", "changed directory\n" };
		runTest(expectedResults, "make a", "cd a", "quit");
	}

	@Test
	public void testMakeWithCDAndRecursivity() throws IOException {
		String[] expectedResults = { "created /root/a\n", "created /root/b\n", "changed directory\n",
				"created /root/a/c\n", "created /root/a/d\n", "changed directory\n", "created /root/a/d/e\n",
				"changed directory\n", "changed directory\n", "a\n", "a/c\n", "a/d\n", "a/d/e\n", "b\n", "\n" };
		runTest(expectedResults, "make a", "make b", "cd a", "touch c", "make d", "cd d", "touch e", "cd ..", "cd ..",
				"ls -r", "quit");
	}

	@Test
	public void testMakeDuplicate() throws IOException {
		String[] expectedResults = { "created /root/dir1\n", "element already exists\n" };
		runTest(expectedResults, "make dir1", "make dir1", "quit");
	}

	@Test
	public void testAccessRootOfRoot() throws IOException {
		String[] expectedResults = { "already located in root\n" };
		runTest(expectedResults, "cd ..", "quit");
	}
}
