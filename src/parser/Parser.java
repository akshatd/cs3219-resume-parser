package parser;

import java.util.List;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class Parser {
	// location where the index will be stored.
	private static final String INDEX_DIR = "src/main/resources/";
	private static final String FILE_NAME = "src/resources/SuranjanaSengupta_Resume.pdf";
	private static final int DEFAULT_RESULT_SIZE = 100;

	private static String[] fileContent;
	private static Map<String, List<String>> fileContentMap = new HashMap<String, List<String>>();
	private static String name;
	private static ArrayList<String> fieldNames = new ArrayList<String>();
	private static List<String> fieldContentList = new ArrayList<String>();

	public static void main(String[] args) throws IOException, ParseException {

		File pdfFile = new File(FILE_NAME);

		PDDocument doc = PDDocument.load(pdfFile);
		fileContent = new PDFTextStripper().getText(doc).split(" |\\\n");

		convertToList();

		setName();
		searchByField();

		printStuff();

		doc.close();
	}

	static void printStuff() {

		for (Map.Entry<String, List<String>> entry : fileContentMap.entrySet()) {
			System.out.println(entry.getKey());
			printList(entry.getValue());
		}
	}

	static void printList(List<String> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i));
		}
		System.out.println();
	}

	public static void setName() throws IOException {

		StringBuffer tempName = new StringBuffer();
		tempName.append(fileContent[0]).append(" ").append(fileContent[1]);

		name = tempName.toString();
		System.out.println("Name : " + name);
	}

	private static void setFieldNames() {
		fieldNames.add("start");
		fieldNames.add("education");
		fieldNames.add("work");
		fieldNames.add("technical");
		fieldNames.add("leadership");
		fieldNames.add("end");
	}

	public static void searchByField() {

		setFieldNames();

		for (int i = 0; i < fieldNames.size() - 1; i++) {
			fileContentMap.put(fieldNames.get(i), getFieldContent(fieldNames.get(i), fieldNames.get(i + 1)));
		}

	}

	private static void convertToList() {

		fieldContentList = Arrays.asList(fileContent);
	}

	public static List<String> getFieldContent(String startField, String endField) {

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

	// Extract text from PDF document
	public static IndexItem index(File file) throws IOException {
		PDDocument doc = PDDocument.load(file);
		String content = new PDFTextStripper().getText(doc);
		doc.close();
		return new IndexItem((long) file.getName().hashCode(), file.getName(), content);
	}

	// Print the results
	private static void print(int result) {
		if (result == 1)
			System.out.println("The document contains the search keyword");
		else
			System.out.println("The document does not contain the search keyword");

	}

}
