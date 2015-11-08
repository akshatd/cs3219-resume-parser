package common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Job {

	private int id;
	private Map<String, List<Word>> jobContentMap;

	public Job() {
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

	public String toString() {
		String jobID = String.valueOf(this.id) + "{";
		String mapData = new String();
		for (Map.Entry<String, List<Word>> entry : jobContentMap.entrySet()) {
			mapData += entry.getKey() + "=" + entry.getValue() + "*^*";
		}
		return jobID + mapData;
	}

	public static Job fromString(String inputString) {
		Job tempJob = new Job();

		String[] inputArray = inputString.split("\\{");
		tempJob.setId(Integer.parseInt(inputArray[0].trim()));

		// need to escape everything :(
		String[] tempFieldData = inputArray[1].split("\\*\\^\\*");
		// go field by field
		Map<String, List<Word>> tempJobContentMap = new HashMap<String, List<Word>>();
		for (int i = 0; i < tempFieldData.length; i++) {
			String[] tempHashValue = tempFieldData[i].split("=");
			// get field key
			String tempKey = tempHashValue[0];
			List<Word> tempValueList = new ArrayList<Word>();
			// remove brackets and split to get values
			if (tempHashValue[1].length() > 2) {
				String[] tempValue = tempHashValue[1].substring(1, tempHashValue[1].length() - 1).split(", ");
				for (int j = 0; j < tempValue.length; j++) {
					Word tempWord = Word.fromString(tempValue[j].trim().split("]}]")[0]);
					tempValueList.add(tempWord);
				}
			}
			tempJobContentMap.put(tempKey, tempValueList);
		}
		tempJob.setJobContentMap(tempJobContentMap);
		return tempJob;
	}
}