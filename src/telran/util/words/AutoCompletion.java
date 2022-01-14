package telran.util.words;

public interface AutoCompletion {
	boolean addWord(String word); //adds word, returns true if added,

	boolean removeWord(String word); //removes word returns true if removed
	Iterable<String> getCompletionOptions(String prefix); //returns any Iterable of words beginning from a given prefix

	default    String getPrefixLimit(String prefix) {
		char lastChar = prefix.charAt(prefix.length() - 1);
		char limitChar = (char) (lastChar + 1);
		return prefix.substring(0, prefix.length() - 1) + limitChar;
	}
}
