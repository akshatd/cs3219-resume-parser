package parser;

import java.util.List;
import java.io.*;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import common.Word;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import org.apache.commons.lang3.StringUtils;

public class Parser {

	private String fileName;
	private String[] fileContent;
	protected List<Word> content;

	Parser(String fileName) {
		this.fileName = fileName;
		content = new ArrayList<Word>();
	}

	protected void extractDataFromPdf() {
		try {

			File pdfFile = new File(fileName);

			PDDocument doc = PDDocument.load(pdfFile);
			fileContent = new PDFTextStripper().getText(doc).split(" |\\\n");

			doc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void setContent() {
		POSTagger();
		gazetter();
	}

	private void POSTagger(){
		MaxentTagger tagger = new MaxentTagger("src/taggers/english-bidirectional-distsim.tagger");

		for (int i = 0; i < fileContent.length; i++) {
			if (StringUtils.isNotBlank(fileContent[i])) {
//				fileContent[i] = fileContent[i].replaceAll("[0-9]", "");
				fileContent[i] = fileContent[i].replaceAll("[-+.^:,()<>&]", "");

				if (StringUtils.isNumeric(fileContent[i])) {
					Word tempWord = new Word(fileContent[i]);
					tempWord.addAnnotation("ANK"); //number
					content.add(tempWord);
					
				}else if (StringUtils.isAlpha(fileContent[i])) {
					Word tempWord = new Word(fileContent[i]);
					String tag = tagger.tagString(fileContent[i]).split("_")[1];
					tempWord.addAnnotation(tag);
					if (isKeyword(tempWord)) {
						content.add(tempWord);
					}
				}
			}
		}
	}
	
	private void gazetter(){
		
	}

	protected List<Word> getFieldContent(String startField, String endField) {
		int tempStartIndex = -1;
		int tempEndIndex = -1;

		for (int i = 0; i < content.size(); i++) {
			if (startField.equalsIgnoreCase("start")) {
				if (content.get(i).getContent().equalsIgnoreCase(endField)) {
					return content.subList(1, i);
				}
			} else if (endField.equalsIgnoreCase("end")) {
				if (content.get(i).getContent().equalsIgnoreCase(startField)) {
					return content.subList(i+1, content.size() - 1);
				}

			} else {
				if (content.get(i).getContent().equalsIgnoreCase(startField))
					tempStartIndex = i;
				if (content.get(i).getContent().equalsIgnoreCase(endField))
					tempEndIndex = i;
				if (tempStartIndex != -1 && tempEndIndex != -1) {
					return content.subList(tempStartIndex+1, tempEndIndex);
				}
			}
		}
		return content;
	}
	
	protected boolean isKeyword(Word word) {
		String POS = word.getAnnotations().get(0);
		if (isNoun(POS) || isVerb(POS)) {
			return true;
		} else
			return false;
	}

	private boolean isNoun(String tag) {
		if (tag.charAt(0) == 'N') {
			return true;
		} else
			return false;
	}

	private boolean isVerb(String tag) {
		if (tag.charAt(0) == 'V') {
			return true;
		} else
			return false;
	}
}
