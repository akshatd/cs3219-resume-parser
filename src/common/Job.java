package common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Job {
	
    private int id;	
	private Map<String, List<Word>> jobContentMap;
	
	public Job(){
		this.jobContentMap = new HashMap<String, List<Word>>();
	}	
	public void setId(int i) {
		this.id = i;
	}	
	public void setJobContentMap(Map<String, List<Word>> contentMap) {
		this.jobContentMap = contentMap;
	}	
	public int getId() {
		return this.id;
	}	
	public Map<String, List<Word>> getJobContentMap() {
		return this.jobContentMap;
	}
	public String toString(){
		return String.valueOf(this.id) + this.jobContentMap.toString();
	}
	public static Job fromString(String inputString){
		Job tempJob = new Job();
		
		String[] inputArray = inputString.split("\\{");
		tempJob.setId(Integer.parseInt(inputArray[0].trim()));
		
		String[] tempFieldData = inputArray[1].split(" ], ");
		Map<String, List<Word>> tempJobContentMap = new HashMap<String, List<Word>>();
		
		for(int i=0; i< tempFieldData.length; i++){
			String[] tempHashValue = tempFieldData[i].split("=\\[");			
			String tempKey = tempHashValue[0].trim();
			String[] tempValue = tempHashValue[1].split(",");			
			List<Word> tempValueList = new ArrayList<Word>();
			
			for(int j=0; j<tempValue.length;j++){
				Word tempWord = Word.fromString(tempValue[j].trim().split(" ")[0]);
				tempValueList.add(tempWord);
			}
			tempJobContentMap.put(tempKey, tempValueList);
		}
		tempJob.setJobContentMap(tempJobContentMap);
		return tempJob;
	}
}