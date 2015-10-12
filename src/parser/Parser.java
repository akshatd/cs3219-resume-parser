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
		
		MaxentTagger tagger = new MaxentTagger("src/taggers/english-bidirectional-distsim.tagger");
		
		for(int i=0; i<fileContent.length; i++) {
			if (StringUtils.isNotBlank(fileContent[i])) {
				fileContent[i] = fileContent[i].replaceAll("[0-9]", "");
				fileContent[i] = fileContent[i].replaceAll("[-+.^:,()<>&]","");
				
				if (StringUtils.isAlpha(fileContent[i])) {
					Word tempWord = new Word((fileContent[i]), tagger.tagString(fileContent[i]));
					content.add(tempWord);
				}
			}
		}
	}

	protected List<Word> getFieldContent(String startField, String endField) {
		int tempStartIndex = -1;
		int tempEndIndex = -1;

		for (int i = 0; i < content.size(); i++) {
			if (startField.equalsIgnoreCase("start")) {
				if (content.get(i).getContent().equalsIgnoreCase(endField)) {
					return content.subList(0, i);
				}
			} else if (endField.equalsIgnoreCase("end")) {
				if (content.get(i).getContent().equalsIgnoreCase(startField)) {
					return content.subList(i, content.size() - 1);
				}

			} else {
				if (content.get(i).getContent().equalsIgnoreCase(startField))
					tempStartIndex = i;
				if (content.get(i).getContent().equalsIgnoreCase(endField))
					tempEndIndex = i;
				if (tempStartIndex != -1 && tempEndIndex != -1) {
					return content.subList(tempStartIndex, tempEndIndex);
				}
			}
		}
		return content;
	}
}
