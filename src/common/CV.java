package common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CV {
	
	private int id;	
	private Map<String, List<Word>> cvContentMap;
	
	public CV(){
		this.cvContentMap = new HashMap<String, List<Word>>();
	}
	public void setId(int i) {
		this.id = i;
	}	
	public void setCvContentMap(Map<String, List<Word>> cvContentMap) {
		this.cvContentMap = cvContentMap;
	}	
	public int getId() {
		return this.id;
	}
	public Map<String, List<Word>> getCvContentMap() {
		return cvContentMap;
	}
	public String toString(){
		return String.valueOf(this.id) + this.cvContentMap.toString();
	}
	public static CV fromString(String inputString){
		CV tempCV = new CV();
		
		String[] inputArray = inputString.split("\\{");
		tempCV.setId(Integer.parseInt(inputArray[0].trim()));
		
		String[] tempFieldData = inputArray[1].split(" ], ");
		Map<String, List<Word>> tempCVContentMap = new HashMap<String, List<Word>>();
		
		for(int i=0; i< tempFieldData.length; i++){
			String[] tempHashValue = tempFieldData[i].split("=\\[");			
			String tempKey = tempHashValue[0].trim();
			String[] tempValue = tempHashValue[1].split(",");			
			List<Word> tempValueList = new ArrayList<Word>();
			
			for(int j=0; j<tempValue.length;j++){
				Word tempWord = Word.fromString(tempValue[j].trim().split(" ")[0]);
				tempValueList.add(tempWord);
			}
			tempCVContentMap.put(tempKey, tempValueList);
		}
		tempCV.setCvContentMap(tempCVContentMap);
		return tempCV;
	}
}