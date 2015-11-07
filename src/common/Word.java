package common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Word {
	private String content;
	private List<String> annotations;

	public Word(String newContent) {
		content = newContent;
		annotations = new ArrayList<String>();
	}

	public String getContent() {
		return content;
	}

	public List<String> getAnnotations() {
		return annotations;
	}

	public void addAnnotation(String newAnnotation) {
		annotations.add(newAnnotation.trim());
	}

	public String toString() {
		return content + ":" + annotations.toString();
	}

	public static Word fromString(String input) {
		String[] inputs = input.split(":");
		Word returnWord = new Word(inputs[0]);
		String[] wordAnnotations  = String.valueOf(Arrays.copyOfRange(inputs[1].toCharArray(), 1, inputs[1].length()-1)).split(", ");
		for(int i = 0; i<wordAnnotations.length; i++){
			returnWord.addAnnotation(wordAnnotations[i]);
		}
		return returnWord;
	}
}
