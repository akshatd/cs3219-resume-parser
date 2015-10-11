package parser;

import java.util.List;
import java.io.*;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;


import org.apache.commons.lang3.StringUtils;

public class Parser {

	private String fileName;
	protected String[] fileContent;

	Parser(String fileName) {
		this.fileName = fileName;
		extractDataFromPdf();
	}

	void extractDataFromPdf() {
		try {

			File pdfFile = new File(fileName);

			PDDocument doc = PDDocument.load(pdfFile);
			fileContent = new PDFTextStripper().getText(doc).split(" |\\\n");

			doc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	List<String> setFieldContent(String startField, String endField) {
		
		List<String> fieldContentList = new ArrayList<String>();
		
		
		for(int i=0; i<fileContent.length; i++) {
			if (StringUtils.isNotBlank(fileContent[i])) {
				fileContent[i] = fileContent[i].replaceAll("[0-9]", "");
				fileContent[i] = fileContent[i].replaceAll("[-+.^:,()<>&]","");
				
				if (StringUtils.isAlpha(fileContent[i])) {
					fieldContentList.add(fileContent[i]);
				}
			}
		}
		

		int tempStartIndex = -1;
		int tempEndIndex = -1;

		for (int i = 0; i < fieldContentList.size(); i++) {
			if (startField.equalsIgnoreCase("start")) {
				if (fieldContentList.get(i).equalsIgnoreCase(endField)) {
					return fieldContentList.subList(0, i);
				}
			} else if (endField.equalsIgnoreCase("end")) {
				if (fieldContentList.get(i).equalsIgnoreCase(startField)) {
					return fieldContentList.subList(i, fieldContentList.size() - 1);
				}

			} else {
				if (fieldContentList.get(i).equalsIgnoreCase(startField))
					tempStartIndex = i;
				if (fieldContentList.get(i).equalsIgnoreCase(endField))
					tempEndIndex = i;
				if (tempStartIndex != -1 && tempEndIndex != -1) {
					return fieldContentList.subList(tempStartIndex, tempEndIndex);
				}
			}
		}
		return fieldContentList;
	}

}
