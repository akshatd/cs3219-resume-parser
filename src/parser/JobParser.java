package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.Job;

public class JobParser extends Parser {

	private Job thisJob;
	private ArrayList<String> fieldList;
	private Map<String, List<String>> contentMap = new HashMap<String, List<String>>();
	
	public JobParser(String fileName) {
		super(fileName);
	}

	public void setJobDetails() {
		extractDataFromPdf();
		setContent();		
	
		setFieldNames(); 
		setFieldContent();

		thisJob = new Job();	
		thisJob.setContentMap(contentMap);
	}

	private void setFieldNames() {
		fieldList = new ArrayList<String>();
		fieldList.add("start");
		fieldList.add("title");
		fieldList.add("skills");
		fieldList.add("education");
		fieldList.add("location");
		fieldList.add("experience");
		fieldList.add("end");
	}

	private void setFieldContent() {
		for (int i = 0; i < fieldList.size()-1; i++) {
			contentMap.put(fieldList.get(i), getFieldContent(fieldList.get(i), fieldList.get(i + 1)));
		}
	}
	public Job getJob(){
		return thisJob;
	}
}
