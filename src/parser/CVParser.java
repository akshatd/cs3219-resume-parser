package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import common.CV;
import common.Word;
import storage.Storage;

public class CVParser extends Parser {

	private CV thisCv;
	private List<String> fieldList; // needs to be defined here
	private Map<String, List<Word>> cvContentMap = new HashMap<String, List<Word>>();
	private Storage storage;

	public CVParser(String fileName) {
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
		fieldList.add("firstname");
		fieldList.add("lastname");
		fieldList.add("education");
		fieldList.add("work");
		fieldList.add("technical");
		fieldList.add("leadership");
		fieldList.add("end");
	}

	private void setFieldContent() {
		for (int i = 2; i < fieldList.size() - 1; i++) {
			if (i == 2) {
				List<Word> temp = getFieldContent(fieldList.get(i - 2), fieldList.get(i + 1));
				cvContentMap.put(fieldList.get(1), temp.subList(0, 1));
				cvContentMap.put(fieldList.get(2), temp.subList(1, 2));
			} else {
				cvContentMap.put(fieldList.get(i), getFieldContent(fieldList.get(i), fieldList.get(i + 1)));
			}
		}
	}

	private void setCVContent() {
		thisCv.setCvContentMap(cvContentMap);
	}

	public CV getCV() {
		return thisCv;
	}

	void saveCV() {
		storage = new Storage();
		storage.saveCV(thisCv);
	}
}
