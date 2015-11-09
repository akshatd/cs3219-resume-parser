package main;

import common.CV;
import common.Job;
import common.Ranking;
import parser.CVParser;
import parser.JobParser;
import storage.Storage;
import analyser.Analyser;

import java.util.ArrayList;

public class Controller {
	public static void main(String[] args) {
		CVParser p = new CVParser("src/resources/SuranjanaSengupta_Resume.pdf");
		p.setCVDetails();
		
		CV c = p.getCV();
		CV d = CV.fromString(c.toString());
		System.out.println(d.toString());
		int CVId = p.saveCV();
		System.out.println(CVId);
		
		JobParser jp = new JobParser("src/resources/JobExample3.pdf");
		jp.setJobDetails();
		
		Job j = jp.getJob();
		Job k = Job.fromString(j.toString());
		System.out.println(k.toString());
		int jobId = jp.saveJob();
		System.out.println(jobId);
		
		Analyser.analyseCV(CVId);
		
	}
	
	public static void uploadCV(String filePath) {
		CVParser p = new CVParser(filePath);
		p.setCVDetails();
		
		CV c = p.getCV();
		CV d = CV.fromString(c.toString());
		System.out.println(d.toString());
		int CVId = p.saveCV();
		System.out.println(CVId);
		
		Analyser.analyseCV(CVId);
	}
	public static void uploadJob(String filePath) {
		JobParser jp = new JobParser(filePath);
		jp.setJobDetails();
		
		Job j = jp.getJob();
		Job k = Job.fromString(j.toString());
		System.out.println(k.toString());
		int jobId = jp.saveJob();
		System.out.println(jobId);
		
		Analyser.analyseJob(jobId);
	}
	public static ArrayList<Ranking> getRanking(int jobId) {
		try {
			ArrayList<Ranking> rankList = Storage.getRanking(jobId);
			return rankList;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<Job> getAllJobs() {
		try {
			ArrayList<Job> jobList = Storage.getAllJobs();
			return jobList;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}