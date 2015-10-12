package test;

import java.io.*;

import common.CV;
import parser.CVParser;

public class CVTest {	
	public static void main(String args[]) throws IOException {
		CVParser p = new CVParser("src/resources/SuranjanaSengupta_Resume.pdf");
		p.setCVDetails();
		
		CV c = p.getCV();
		CV d = CV.fromString(c.toString());
		System.out.println(d.toString());
	}

}
