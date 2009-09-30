package ch.hsr.ifs.liquids.util.list;

import org.junit.Test;

import ch.hsr.ifs.liquids.util.list.List;

import static org.junit.Assert.*;

public class ListTest {

	@Test
	public void testAdd() {
		List<String> list = new List<String>(1);
		assertTrue(list.size == 0);

		list.add("Test");
		assertTrue(list.size == 1);

		list.add("Test 2");
		assertTrue(list.size == 2);
	}

	@Test
	public void testSet() {
		List<String> list = new List<String>();

		list.add("Test 0");
		list.add("Test 1");
		list.add("Test 2");

		int index = 1;
		String element = "Test 3";

		list.set(element, index);

		assertEquals(element, list.get(index));
	}

	@Test
	public void testGet() {
		List<String> list = new List<String>();

		String test0 = "Test 0";
		String test1 = "Test 1";
		String test2 = "Test 2";

		list.add(test0);
		list.add(test1);
		list.add(test2);

		assertEquals(test0, list.get(0));
		assertEquals(test1, list.get(1));
		assertEquals(test2, list.get(2));
	}

	@Test
	public void testRemove() {
		List<String> list = new List<String>();

		String test0 = "Test 0";
		String test1 = "Test 1";
		String test2 = "Test 2";

		list.add(test0);
		list.add(test1);
		list.add(test2);

		int index = 1;
		list.remove(index);

		assertEquals(test2, list.get(index));
		assertEquals(list.size, 2);
	}

}
