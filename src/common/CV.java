package common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CV {

	private int id;
	private Map<String, List<Word>> cvContentMap;

	public CV() {
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

	public String toString() {
		String cvID = String.valueOf(this.id) + "{";
		String mapData = new String();
		for (Map.Entry<String, List<Word>> entry : cvContentMap.entrySet()) {
			mapData += entry.getKey() + "=" + entry.getValue() + "*^*";
		}
		return cvID + mapData;
	}

	public static CV fromString(String inputString) {
		CV tempCV = new CV();

		String[] inputArray = inputString.split("\\{");
		tempCV.setId(Integer.parseInt(inputArray[0]));

		// need to escape everything :(
		String[] tempFieldData = inputArray[1].split("\\*\\^\\*");
		// go field by field
		Map<String, List<Word>> tempCVContentMap = new HashMap<String, List<Word>>();
		for (int i = 0; i < tempFieldData.length; i++) {
			String[] tempHashValue = tempFieldData[i].split("=");
			// get field key
			String tempKey = tempHashValue[0];
			List<Word> tempValueList = new ArrayList<Word>();
			// remove brackets and split to get values
			if (tempHashValue[1].length() > 2) {
				String[] tempValue = tempHashValue[1].substring(1, tempHashValue[1].length()-1).split(", ");
				for (int j = 0; j < tempValue.length; j++) {
					if (!tempValue[j].isEmpty()) {
						Word tempWord = Word.fromString(tempValue[j]);
						tempValueList.add(tempWord);
					}
				}
			}
			tempCVContentMap.put(tempKey, tempValueList);
		}
		tempCV.setCvContentMap(tempCVContentMap);
		return tempCV;
	}
}