package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import storage.Storage;
import common.Job;
import common.Word;

public class JobParser extends Parser {

	private Job thisJob;
	private ArrayList<String> fieldList;
	private Map<String, List<Word>> contentMap = new HashMap<String, List<Word>>();
	private Storage storage;
	
	public JobParser(String fileName) {
		super(fileName);
	}

	public void setJobDetails() {
		extractDataFromPdf();
		setContent();		
	
		setFieldNames(); 
		setFieldContent();

		thisJob = new Job();	
		thisJob.setJobContentMap(contentMap);
	}

	private void setFieldNames() {
		fieldList = new ArrayList<String>();
		fieldList.add("start");
		fieldList.add("title");
		fieldList.add("technical");
		fieldList.add("education");
		fieldList.add("location");
		fieldList.add("experience");
		fieldList.add("end");
	}

	private void setFieldContent() {
		for (int i = 1; i < fieldList.size()-1; i++) {
			if(i == 1){
				contentMap.put(fieldList.get(i), getFieldContent(fieldList.get(i-1), fieldList.get(i + 1)));
			}else{
			contentMap.put(fieldList.get(i), getFieldContent(fieldList.get(i), fieldList.get(i + 1)));
			}
		}
	}
	public Job getJob(){
		return thisJob;
	}
	
	public int saveJob(){
		storage = new Storage();
		return storage.saveJob(thisJob);
	}
}
