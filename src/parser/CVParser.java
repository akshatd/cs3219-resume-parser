package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.CV;

public class CVParser extends Parser {

	private List<String> fieldList; // needs to be defined here

	private CV cv;
	private Map<String, List<String>> cvContentMap = new HashMap<String, List<String>>();
	

	CVParser(String fileName) {
		super(fileName);
	}

	void setCVDetails() {

		setFieldNames(); // CV Specific Fields
		searchByField();

		cv = new CV();
		setCVContent();

	}

	private void setFieldNames() {
		fieldList = new ArrayList<String>();
		fieldList.add("start");
		fieldList.add("education");
		fieldList.add("work");
		fieldList.add("technical");
		fieldList.add("leadership");
		fieldList.add("end");
	}

	private void searchByField() {

		for (int i = 0; i < fieldList.size() - 1; i++) {
			cvContentMap.put(fieldList.get(i), setFieldContent(fieldList.get(i), fieldList.get(i + 1)));
		}

	}

	private void setCVContent() {

		cv.setFirstName(fileContent[0]);
		cv.setLastName(fileContent[1]);
		cv.setCvContentMap(cvContentMap);
	}

	CV getCV() {
		return cv;
	}

}
