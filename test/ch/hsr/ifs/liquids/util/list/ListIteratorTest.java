package ch.hsr.ifs.liquids.util.list;

import java.util.Iterator;

import org.junit.Test;

import ch.hsr.ifs.liquids.util.list.List;
import static org.junit.Assert.*;

public class ListIteratorTest {

	@Test
	public void testHasNext() {
		List<String> list = new List<String>();
		Iterator<String> iterator = list.iterator();

		assertFalse(iterator.hasNext());

		list.add("Test");

		assertTrue(iterator.hasNext());
	}

	@Test
	public void testNext() {
		List<String> list = new List<String>();
		Iterator<String> iterator = list.iterator();

		String test1 = "Test 1";
		String test2 = "Test 2";
		String test3 = "Test 3";

		list.add(test1);
		list.add(test2);
		list.add(test3);

		assertEquals(test1, iterator.next());
		assertEquals(test2, iterator.next());
		assertEquals(test3, iterator.next());
	}

	@Test
	public void testRemove() {
		List<String> list = new List<String>();
		Iterator<String> iterator = list.iterator();

		String test0 = "Test 0";
		String test1 = "Test 1";
		String test2 = "Test 2";

		list.add(test0);
		list.add(test1);
		list.add(test2);

		iterator.next();
		iterator.next();
		iterator.remove();

		assertEquals(test0, list.get(0));
		assertEquals(test2, list.get(1));
	}

	@Test
	public void testIterate() {
		List<String> list = new List<String>();

		String test0 = "Test 0";
		String test1 = "Test 1";
		String test2 = "Test 2";

		int i = 0;
		for (String string : list) {
			switch (i) {
			case 0:
				assertEquals(test0, string);
				break;
			case 1:
				assertEquals(test1, string);
				break;
			case 2:
				assertEquals(test2, string);
				break;
			default:
				fail();
				break;
			}
		}
	}

}
