package parser;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import common.Word;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import org.apache.commons.lang3.StringUtils;

public class Parser {

	private String fileName;
	private String[] fileContent;
	protected List<Word> content;
	Map<String, Set<String>> gazette;
	private final static String gazetteLoc = Parser.class.getProtectionDomain().getCodeSource().getLocation().getPath() + 
				File.separator + "../.." + File.separator + "gazettes" + File.separator;
	private final static String[] FIELDNAMES = { "accomplishments", "awards", "credibility", "education",
			"extracurricular", "misc", "skills", "work" };
	protected final static String PROFILE = "profile";
	private final static Set<String> FIELDSET = new HashSet<String>(Arrays.asList(FIELDNAMES));

	Parser(String fileName) {
		this.fileName = fileName;
		content = new ArrayList<Word>();
		gazette = new HashMap<String, Set<String>>();
		setUpGazetee();
	}

	private void setUpGazetee() {
		for (int i = 0; i < FIELDNAMES.length; i++) {
			Set<String> fileContent = getFileContent(gazetteLoc + FIELDNAMES[i] + ".txt");
			gazette.put(FIELDNAMES[i].toLowerCase(), fileContent);
		}
	}

	private Set<String> getFileContent(String fileName) {
		Set<String> fileContent = new HashSet<String>();
		String line;
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				fileContent.add(line.toLowerCase());
			}

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			String curDir = System.getProperty("user.dir");
			System.out.println(curDir);
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
		return fileContent;
	}

	protected void extractDataFromPdf() {
		try {

			File pdfFile = new File(fileName);

			PDDocument doc = PDDocument.load(pdfFile);
			fileContent = new PDFTextStripper().getText(doc).split(" |\\\n");

			doc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void setAnnotations() {
		POSTagger();
		gazetter();
	}

	private void POSTagger() {
		MaxentTagger tagger = new MaxentTagger("english-bidirectional-distsim.tagger");

		for (int i = 0; i < fileContent.length; i++) {
			if (StringUtils.isNotBlank(fileContent[i])) {
				// fileContent[i] = fileContent[i].replaceAll("[0-9]", "");
				fileContent[i] = fileContent[i].replaceAll("[-+.^:,()<>&]", "");

				if (StringUtils.isNumeric(fileContent[i])) {
					Word tempWord = new Word(fileContent[i]);
					tempWord.addAnnotation("ANK"); // number
					content.add(tempWord);

				} else if (StringUtils.isAlpha(fileContent[i])) {
					Word tempWord = new Word(fileContent[i]);
					String tag = tagger.tagString(fileContent[i]).split("_")[1];
					tempWord.addAnnotation(tag);
					if (isKeyword(tempWord)) {
						content.add(tempWord);
					}
				}
			}
		}
	}

	protected boolean isKeyword(Word word) {
		String POS = word.getAnnotations().get(0);
		if (isNoun(POS) || isVerb(POS)) {
			return true;
		} else
			return false;
	}

	private boolean isNoun(String tag) {
		if (tag.charAt(0) == 'N') {
			return true;
		} else
			return false;
	}

	private boolean isVerb(String tag) {
		if (tag.charAt(0) == 'V') {
			return true;
		} else
			return false;
	}

	private void gazetter() {
		for (int i = 0; i < content.size(); i++) {
			Word tempWord = content.get(i);
			for (int j = 0; j < gazette.size(); j++) {
				if (gazette.get(FIELDNAMES[j]).contains(tempWord.getContent().toLowerCase())) {
					tempWord.addAnnotation(FIELDNAMES[j]);
				}
			}
		}
	}

	protected Map<String, List<Word>> setContentMap() {
		Map<String, List<Word>> contentMap = new HashMap<String, List<Word>>();

		String currentField = PROFILE;
		contentMap.put(currentField, new ArrayList<Word>());
		
		
		for (int i=0; i<FIELDNAMES.length; i++) {
			contentMap.put(FIELDNAMES[i], new ArrayList<Word>());
		}
		
		for (int i = 0; i < content.size(); i++) {
			int len = content.get(i).getAnnotations().size();
			if (FIELDSET.contains(content.get(i).getAnnotations().get(len - 1))) {
				currentField = content.get(i).getAnnotations().get(len - 1);
			} else {
				contentMap.get(currentField).add(content.get(i));
			}
		}

		return contentMap;
	}

}
