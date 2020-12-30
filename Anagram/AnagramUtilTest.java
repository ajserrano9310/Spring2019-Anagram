package assignment04;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;
/**
 * @author Alejandro Rubio
 * @author Alejandro Serrano
 */
public class AnagramUtilTest {
	
	/**
	 * Testing sort Method
	 */
	@Test
	public void testingSortMethod() {
		
		String e = "Utah"; 
		String expectedStr = "ahtu";
		assertEquals(AnagramUtil.sort(e), expectedStr);
	}
	@Test
	public void testingSortMethodWithAllLowerCase () {
		String e = "valor";
		String expectedStr = "alorv";
		assertEquals(AnagramUtil.sort(e), expectedStr);
	}
	@Test
	public void testingSortWithAllCapitalLetters() {
		String e = "PERU";
		String expS = "epru";
		assertEquals(AnagramUtil.sort(e), expS);
	}
	@Test
	public void testingAnAlreadySortedWord() {
		String e = "mossy"; 
		String expS = "mossy";
		assertEquals(AnagramUtil.sort(e), expS);
	}
	@Test
	public void testingSortMethodWithEmptyString() {
		String emptyString = " ";
		String expS = " ";
		assertEquals(AnagramUtil.sort(emptyString), expS);
		
	}
	/**
	 * Testing areAnagrams method
	 */
	
	@Test
	public void testingAreAnagramsTrue() {
		String e = "eat";
		String expS = "ate"; 
		assertEquals(AnagramUtil.areAnagrams(e, expS), true);
	}
	
	@Test
	public void testingAreAnagramsFalse() {
		String e = "eats";
		String expS = "ate"; 
		assertEquals(AnagramUtil.areAnagrams(e, expS), false);
	}
	@Test
	public void testingEqualWords () {
		String e = "hello";
		String expS = "hello"; 
		assertEquals(AnagramUtil.areAnagrams(e, expS), true);
	}
	@Test
	public void testingOrderedEqualWords() {
		String e = "air";
		String expS = "air";
		assertEquals(AnagramUtil.areAnagrams(e, expS), true);
	}
	
	/*
	 * Testing Insertion sort
	 */
	@Test
	public void orderingIntegers() {
		List <Integer> s = new ArrayList<>();
		
		s.add(28);
		s.add(1);
		s.add(420);
		s.add(13);
		
		List <Integer> orderedList = new ArrayList<>();
		orderedList.add(1);
		orderedList.add(13);
		orderedList.add(28);
		orderedList.add(420);
		
		AnagramUtil.insertionSort(s, (s1, s2)-> Integer.compare(s1, s2));
		
		assertEquals(s, orderedList);
	}
	@Test
	public void orderingRandomIntegers() {
		List <Integer> s = new ArrayList<>();
		List <Integer> orderedList = new ArrayList<>();
		Random rand = new Random();
		for(int i = 0;i<100;i++) {
			Integer number = rand.nextInt(1000);
			s.add(number);
			orderedList.add(number);
		}
		
		AnagramUtil.insertionSort(s, (s1, s2)-> Integer.compare(s1, s2));
		orderedList.sort((s1, s2)-> Integer.compare(s1, s2));
		assertEquals(s, orderedList);
	}
	@Test
	public void orderingStrings() {
		List <String> s = new ArrayList<>();
		List <String> orderedList = new ArrayList<>();
		s.add("jason");
		s.add("alejandro");
		s.add("hannah");
		s.add("pedro");
		s.add("nicolas");
		orderedList.add("alejandro");
		orderedList.add("hannah");
		orderedList.add("jason");
		orderedList.add("nicolas");
		orderedList.add("pedro");
		AnagramUtil.insertionSort(s, (s1, s2)-> s1.compareTo(s2));
		
		assertEquals(s, orderedList);
	}
	
	@Test
	public void orderingAlreadyOrderedStrings() {
		List <String> s = new ArrayList<>();
		List <String> orderedList = new ArrayList<>();
		s.add("jason");
		s.add("alejandro");
		s.add("hannah");
		s.add("pedro");
		s.add("nicolas");
		orderedList.add("alejandro");
		orderedList.add("hannah");
		orderedList.add("jason");
		orderedList.add("nicolas");
		orderedList.add("pedro");
		AnagramUtil.insertionSort(s, (s1, s2)-> s1.compareTo(s2));
		
		assertEquals(s, orderedList);
	}
	
	/*
	 * Testing getLargestAnagramGroup with files
	 */
	@Test
	public void testingGetLargestAnagramGroupWithFile() {
		List <String> expGroup = new ArrayList<>();
		expGroup.add("carets");
		expGroup.add("Caters");
		expGroup.add("caster");
		expGroup.add("crates");
		expGroup.add("Reacts");
		expGroup.add("recast");
		expGroup.add("traces");
		assertEquals(AnagramUtil.getLargestAnagramGroup("data/words.txt"), expGroup);
	}
	
	@Test
	public void testingGetLargestAnagramGroupWithModerateEnglishWords() {
		List <String> expGroup = new ArrayList<>();
		expGroup.add("act");
		expGroup.add("cat");
		assertEquals(AnagramUtil.getLargestAnagramGroup("data/moderate_word_list.txt"), expGroup);
		}
	
//	@Test
//	public void testingGetLargestAnagramGroupWithWordsEnglishFile() {
//		List <String> expGroup = new ArrayList<>();
//		expGroup.add("apers");
//		expGroup.add("apres");
//		expGroup.add("asper");
//		expGroup.add("pares");
//		expGroup.add("parse");
//		expGroup.add("pears");
//		expGroup.add("rapes");
//		expGroup.add("reaps");
//		expGroup.add("spare");
//		expGroup.add("spear");
//		assertEquals(AnagramUtil.getLargestAnagramGroup("data/words_english.txt"), expGroup);
//	}
	@Test
	public void testingGetLargestAnagramGroupWithEmptyFile() {
		List <String> expGroup = new ArrayList<>();
		assertEquals(AnagramUtil.getLargestAnagramGroup("data/emptyFile.txt"), expGroup);
		}
	
	
	
	
	
}