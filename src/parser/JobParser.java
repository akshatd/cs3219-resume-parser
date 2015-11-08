package parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import storage.Storage;
import common.Job;
import common.Word;

public class JobParser extends Parser {

	private Job thisJob;
	private Map<String, List<Word>> jobContentMap = new HashMap<String, List<Word>>();
	
	public JobParser(String fileName) {
		super(fileName);
	}

	public void setJobDetails() {
		extractDataFromPdf();
		setAnnotations();
		jobContentMap = setContentMap();
		
		thisJob = new Job();	
		thisJob.setJobContentMap(jobContentMap);
	}

	public Job getJob(){
		return thisJob;
	}
	
	public int saveJob(){
		return Storage.saveJob(thisJob);
	}
}
