package parser;

import java.io.IOException;

import common.CV;

public class CVTest {
	
	public static void main(String args[]) throws IOException {
		CVParser p = new CVParser("src/resources/SuranjanaSengupta_Resume.pdf");
		p.setCVDetails();
		
		CV c = p.getCV();
		System.out.println(c.getCvContentMap().get("education"));
	}

}
