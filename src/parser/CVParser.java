package parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.CV;
import common.Word;
import storage.Storage;

public class CVParser extends Parser {

	private CV thisCv;
	private Map<String, List<Word>> cvContentMap = new HashMap<String, List<Word>>();

	public CVParser(String fileName) {
		super(fileName);
	}

	public void setCVDetails() {
		extractDataFromPdf();
		setAnnotations();
		cvContentMap = setContentMap();
		
		thisCv = new CV();
		thisCv.setCvContentMap(cvContentMap);
	}

	public CV getCV() {
		return thisCv;
	}

	public int saveCV() {
		return Storage.saveCV(thisCv);
	}
}
