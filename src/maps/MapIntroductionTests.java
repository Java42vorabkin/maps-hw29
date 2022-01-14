package maps;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
class MapIntroductionTests {
HashMap<String, Integer> hashMap;
TreeMap<String, Integer> treeMap;
LinkedHashMap<String, Integer> linkedHashMap;
String keys[] = {"abc", "lmn", "drt", "lmn", "abcd"};
Integer values[] = {10, 20, 30, 40, 50};
	@BeforeEach
	void setUp() throws Exception {
		hashMap = new HashMap<>();
		treeMap = new TreeMap<>();
		linkedHashMap = new LinkedHashMap<>();
		fillMap(hashMap);
		fillMap(treeMap);
		fillMap(linkedHashMap);
	}

	private void fillMap(Map<String, Integer> map) {
		for (int i = 0; i < keys.length; i++) {
			map.put(keys[i], values[i]);
		}
		
	}

	@Test
	void testPut() {
		int sizeExpected = keys.length - 1;
		int valueExpected = 40;
		assertEquals(sizeExpected, hashMap.size());
		assertEquals(sizeExpected, treeMap.size());
		assertEquals(sizeExpected, linkedHashMap.size());
		assertEquals(valueExpected, hashMap.get("lmn"));
		assertEquals(valueExpected, treeMap.get("lmn"));
		assertEquals(valueExpected, linkedHashMap.get("lmn"));
		
		
	}
	@Test
	void testIterable() {
		//Map is not Iterable
//		for(String str: treeMap) {
//			
//		}
//		String keys[] = {"abc", "lmn", "drt", "lmn", "abcd"};
		Iterable<String> treeIterableExpected = Arrays.asList("abc", "abcd","drt","lmn");
		Iterable<String> linkedHashExpected = Arrays.asList("abc", "lmn", "drt",  "abcd");
		assertIterableEquals(treeIterableExpected, treeMap.keySet());
		assertIterableEquals(linkedHashExpected, linkedHashMap.keySet());
	}

}
