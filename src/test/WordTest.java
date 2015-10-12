package test;

import java.io.IOException;

import common.Word;

public class WordTest {
	public static void main(String args[]) throws IOException{
		Word tempWord = Word.fromString("tempWord:NN");
		System.out.println(tempWord);
	}
}
