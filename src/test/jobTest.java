package test;

import java.io.*;

import common.Job;
import parser.JobParser;

public class jobTest {
	public static void main(String args[]) throws IOException{
		JobParser jp = new JobParser("src/resources/JobExample.pdf");
		jp.setJobDetails();
		
		Job j = jp.getJob();
		Job k = Job.fromString(j.toString());
		System.out.println(k.toString());
	}
}
