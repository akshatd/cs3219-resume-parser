package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.CV;

public class CVParser extends Parser {

	private CV thisCv;
	private List<String> fieldList; // needs to be defined here
	private Map<String, List<String>> cvContentMap = new HashMap<String, List<String>>();

	CVParser(String fileName) {
		super(fileName);
	}

	public void setCVDetails() {
		extractDataFromPdf();
		setContent();		

		setFieldNames(); // CV Specific Fields
		setFieldContent();

		thisCv = new CV();
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

	private void setFieldContent() {
		for (int i = 0; i < fieldList.size() - 1; i++) {
			cvContentMap.put(fieldList.get(i), getFieldContent(fieldList.get(i), fieldList.get(i + 1)));
		}
	}

	private void setCVContent() {
		thisCv.setFirstName(content.get(0));
		thisCv.setLastName(content.get(0));
		thisCv.setCvContentMap(cvContentMap);
	}
	public CV getCV(){
		return thisCv;
	}
}
