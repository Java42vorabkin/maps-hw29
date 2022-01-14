package telran.util.words;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.*;
public class AutoCompletionMapImpl implements AutoCompletion {
HashMap<Character, TreeSet<String>> words = new HashMap<>(); //key - first character of a word;
// value - collection (TreeSet) of words beginning with the given first character case insensitive
	@Override
	/**
	 * adds word 
	 * with applying the method computeIfAbsent
	 */
	public boolean addWord(String word) {		
		// TODO    Done
		return word.isEmpty() ? false : 
				words.computeIfAbsent(getKey(word), t -> new TreeSet<>(String.CASE_INSENSITIVE_ORDER)).add(word);
	}

	private Character getKey(String word) {
		return word.toLowerCase().charAt(0);
	}

	@Override
	public boolean removeWord(String word) {
		// TODO   Done
		/*
		return word.isEmpty()? false : 
			words.computeIfAbsent(getKey(word), t -> new TreeSet<>()).remove(word);
			*/
		return word.isEmpty() ? false : removeNonEmpty(word);
	}
	private boolean removeNonEmpty(String word) {
		TreeSet<String> tree = words.get(getKey(word));
		return tree==null ? false : tree.remove(word);
	}

	@Override
	public Iterable<String> getCompletionOptions(String prefix) {
		// TODO Auto-generated method stub
		if(prefix.isEmpty()) {
			return new TreeSet<String>();
		}
		TreeSet<String> tree = words.get(getKey(prefix));	
		return tree==null ? new TreeSet<String>() : 
							tree.subSet(prefix, AutoCompletionUtils.getPrefixLimit(prefix));
//							tree.subSet(prefix, getPrefixLimit(prefix));
	}
	/**
	 * removes words matching a given predicate
	 * @param predicate
	 * @return count of the removed words
	 */
	public int removeIf(Predicate<String> predicate) {
		//TODO
		int counter = 0;
		Collection<TreeSet<String>> collection = words.values();
		for(TreeSet<String> treeSet : collection ) {
			int originalSize = treeSet.size();
			treeSet.removeIf(predicate);
			counter += (originalSize-treeSet.size());
		}
		return counter;
	}
	public HashMap<Character, TreeSet<String>> getMap() {
		return words;
	}
}
