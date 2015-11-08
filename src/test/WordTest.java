package test;

import org.junit.Assert;
import org.junit.Test;

import common.Word;

public class WordTest {

	private String content = "programming";
	private Word word = new Word(content);
	
	@Test
	public void testWord() {		
		Assert.assertNotNull(word);
	}

	@Test
	public void testGetContent() {
		Assert.assertTrue(word.getContent().equalsIgnoreCase(content));
	}
	
	@Test
	public void testAddAnnotation() {
		word.addAnnotation("NNP");
		Assert.assertTrue(word.getAnnotations().get(0).equalsIgnoreCase("NNP"));
	}

	@Test
	public void testGetAnnotations() {
		word.addAnnotation("NNP");
		Assert.assertTrue(word.getAnnotations().get(0).equalsIgnoreCase("NNP"));
	}

	

	@Test
	public void testToString() {
		String annotatedWord = "programming:[NNP]";
		word.addAnnotation("NNP");
		Assert.assertTrue(annotatedWord.equalsIgnoreCase(word.toString()));
	}

	@Test
	public void testFromString() {
		String annotatedWord = "programming:[NNP]";
		word.addAnnotation("NNP");
		Assert.assertEquals(word.getContent(), Word.fromString(annotatedWord).getContent());;
	}

}
