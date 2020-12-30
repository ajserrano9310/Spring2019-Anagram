package assignment04;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * @author Alejandro Rubio
 * @author Alejandro Serrano
 */
public class AnagramUtil {
	/**
	* Reports whether the two input strings are anagrams of each other. *
	* @param s1 the first candidate string
	* @param s2 the second candidate string
	* @return true iff {@code s1} and {@code s2} are anagrams of each other */
	public static boolean areAnagrams(String s1, String s2) { 
		/*
		 * Sorts both words to be compared. 
		 */
		// sorts characters from string s1
		s1=sort(s1);
		// sorts characters from string s2
		s2=sort(s2);
		// compares if strings are equal
		return s1.equals(s2);
	}
	/**
	* Returns the largest group of anagrams from the list of words in the given
	* file, in no particular order. *
	* It is assumed that the file contains one word per line. If the file is empty,
	* the method returns an empty list because there are no anagrams. *
	* @param filename file to read strings from
	* @return largest group of anagrams in the input file */
	public static List<String> getLargestAnagramGroup(String filename) {
		// create a list of type string to add sorted words that are not repeated
		List<String> l = new ArrayList <String>();
		// read words from file
		SimpleReader file = new SimpleReader1L(filename);
		/*
		 * Creates a list from the given file. 
		 */
		// variable for when we reach the end of the file
		boolean end=false;
		// while it is not the end of the file
		while(!end) {
			// get the word
			String word = file.nextLine();
			// add it to our list
			l.add(word);
			// if we are at the end of the file
			if(file.atEOS()) {
				// set the variable end to true so we can stop the loop
				end=true;
			}
		}
		// close the file
		file.close();
		
		// call helper method
		return getLargestAnagramGroup(l);
	}
	/**
	* Returns the largest group of anagrams in the input list of words, in no
	* particular order. *
	* @param input list of strings
	* @return largest group of anagrams in {@code input}
	*/
	public static List<String> getLargestAnagramGroup(List<String> input) {
		List<String> listOfOriginalWords = new ArrayList<>();
		// if the size of the list is 0
		if(input.size()==0) {
			// return empty list
			return listOfOriginalWords;
		}
		/*
		 * Copies words original to listOfOriginalWords.
		 */
		// go from the first word of the list to the last one
		for(int x =0; x<input.size();x++) {
			// add the word to the list so we can return original words later
			listOfOriginalWords.add(input.get(x));
		}
		/*
		 * Sets the words with sorted characters
		 * in words list.
		 */
		// copy list for the words coming from input 
		List <String> words = new ArrayList<>();
		// go from the first word of the list to the last one
		for(int i =0; i<input.size();i++) {
			// get the word and sort it
			String sortedWord= sort(input.get(i));
			// adds the word to the list
			words.add(sortedWord);
		}
		// sorts the words in alphabetical order
		insertionSort(words, (s1, s2) -> s1.compareTo(s2));
		return sortLargest(words, listOfOriginalWords);
	}
	/**
	 * Helper method for getLargestAnagramGroup. Does two things: 
	 * 1) creates a list of a a single anagram as a 
	 * representation of the words that share the common letters.
	 * 2) Compares the previous list with list containing original 
	 * words to create a list with largest set of anagrams. 
	 * @param input list of words with their sorted characters. 
	 * @return the largest group of Anagrams in a list of words. 
	 */
	private static List<String> sortLargest(List<String> input, 
			List <String> originals){
		// variable for the anagram that will repeat the most
		String maxWord = input.get(0);
		// count of times the anagram repeats
		int maxCount =0;
		// variable to hold the current word that will be compared
		String currentWord = input.get(0);
		// counter for the current word
		int currentCount = 1;
		// goes from next word to the size of list
		for (int i = 1; i <input.size(); i++) {
			// will repeat while i is less than size of input and the words are equal 
			while(i< input.size() && input.get(i).equals(currentWord) ) {
				// if words are equal adds to the current count
				currentCount ++;
				// skips to the next word
				i++;
			}
			// checks if the count of the current word is greater than the previous max
			if(currentCount > maxCount) {
				// if true, maxWord is now the current word
				maxWord = currentWord;
				// currentWord is the new maxCount
				maxCount = currentCount;
			}
			// checks if i is still within the boundaries of the list
			if(i == input.size()) {
				break;
			}
			// otherwise it just resets the values to keep comparing
			else {
				currentWord = input.get(i);
				currentCount = 1;
			}
		}
		// list that will hold the largest group of anagrams
		List<String> largestSetOfAnagrams = new ArrayList<>();
		// goes from 0 to size of input
		for(int j = 0; j < input.size(); j++) {
			// compares if both words are anagrams
			if(areAnagrams(maxWord, originals.get(j))) {
				// puts them in the list that will be returned
				largestSetOfAnagrams.add(originals.get(j));
			}
		}
		
		return largestSetOfAnagrams;
	}
	
	/**
	* Sorts the input list using an insertion sort and the input {@code Comparator}
	* object. *
	* @param <T> type of the element of the list
	* @param list input list
	* @param order comparator used to sort elements *
	* @modifies {@code list} */
	public static <T> void  insertionSort ( List <T> list, Comparator<? super T> order) {
		// go from the first element of the list to the last one
	for(int i = 1; i< list.size(); i++) {
		// set the unsorted value as the element in position i
		T unsortedValue = list.get(i);
		// set the current index as i
		int index = i;
		// go from i-1 to 0 decreasing by 1
		for(int j = i - 1; j >= 0; j--) {
			// set element j as a sorted value
			T sortedVal = list.get(j);
			// call comparator to get the compare value
			int compareValue = order.compare(sortedVal, unsortedValue);
			// if the value of the comparator is greater than 0
			if(compareValue > 0 ) {
				// set the unsorted value at position j
				list.set(j, unsortedValue);
				// set the sorted value at position index
				list.set(index, sortedVal);		
				}
			else {
				break;
			}
			// decrease index by 1
			index--;
			}
		}
	}
	/**
	* Returns a case-insensitive sorted version of the input String. For example,
	* if {@code str = "Utah"}, it returns {@code "ahtu"}. This sorting must be
	* done using insertion sort. *
	* @param str string to be sorted
	* @return sorted string */
	 public static String sort(String e) {
		 // create list for characters of each word
		List<Character> listOfCharacters = new ArrayList <Character>();
		// convert the word to lower case
		e=e.toLowerCase();
		// set the sorted word as empty
		String sortedWord = "";
		// go trough the characters of the word
		for(int i = 0; i < e.length(); i ++) {
			// get the character and add it to the list
			listOfCharacters.add(e.charAt(i));
		}
		// call insertion sort method with the list of characters and the comparator
		insertionSort(listOfCharacters, (s1, s2) -> s1.compareTo(s2));
		/*
		 *  Sets the characters in a new String 
		 *  in alphabetical order. 
		 */
		// go trough the first character to the last one
		for(int j = 0; j <listOfCharacters.size(); j++) {
			// get the character and add it to a string so we can return it later
			sortedWord = sortedWord + listOfCharacters.get(j);
			}
		// return the word with sorted characters
		return sortedWord;
		}
	public static void main(String[] args) {
		// location of the file
		String fileLocation = "data/words.txt";
		// create simplewriter for output
		SimpleWriter out = new SimpleWriter1L();
		// call method
		out.println(getLargestAnagramGroup(fileLocation));
		// close simplewriter
		out.close();
	}
}