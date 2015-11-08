package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.CV;
import common.Word;
import storage.Storage;

public class CVParser extends Parser {

	private CV thisCv;
	private Map<String, List<Word>> cvContentMap = new HashMap<String, List<Word>>();
	private final static String FULLNAME = "fullname";
	private final static String MOBILE = "mobile";

	public CVParser(String fileName) {
		super(fileName);
	}

	public void setCVDetails() {
		extractDataFromPdf();
		setAnnotations();
		cvContentMap = setContentMap();

		List<Word> profile = cvContentMap.get(PROFILE);
		cvContentMap.put(FULLNAME, getName(profile));
		cvContentMap.put(MOBILE, getMobile(profile));

		thisCv = new CV();
		thisCv.setCvContentMap(cvContentMap);
	}

	private List<Word> getName(List<Word> profile) {
		List<Word> fullName = new ArrayList<Word>();

		for (int i = 0; i < profile.size(); i++) {
			if (profile.get(i).getAnnotations().get(0).equalsIgnoreCase("NN")) {
				fullName.add(profile.get(i));
			} else {
				break;
			}
		}
		removeAttributeFromProfile(profile, fullName);
		return fullName;
	}

	private List<Word> getMobile(List<Word> profile) {
		List<Word> mobile = new ArrayList<Word>();

		for (int i = 0; i < profile.size(); i++) {
			if (profile.get(i).getAnnotations().get(0).equalsIgnoreCase("ANK")) {
				if (profile.get(i).getContent().length() == 8) {
					mobile.add(profile.get(i));
					break;
				}
			}
		}
		removeAttributeFromProfile(profile, mobile);
		return mobile;
	}

	private void removeAttributeFromProfile(List<Word> profile, List<Word> attribute) {
		for (int i = 0; i < attribute.size(); i++) {
			profile.remove(attribute.get(i));
		}
	}

	public CV getCV() {
		return thisCv;
	}

	public int saveCV() {
		return Storage.saveCV(thisCv);
	}
}
