package test;


import org.junit.Assert;
import org.junit.Test;

import parser.JobParser;

public class JobParserTest {

	private static final String FILENAME = "src/resources/JobExample.pdf";
	private JobParser jobParser = new JobParser(FILENAME);
	private static String content = "0{skills=[C:[NN], Java:[NNP], python:[NN], VHDL:[NN], Photoshop:[NN]]*^*education=[bachelors:[NNS], CS:[NN], CEG:[NN], Location:[NNP], Singapore:[NNP], United:[NNP], States:[NNS]]*^*awards=[]*^*work=[3:[ANK], years:[NNS], developing:[VBG], backend:[NN], medium:[NN], sized:[VBN], web:[NN], app:[NN], helolo:[NN]]*^*profile=[Backend:[NN], developer:[NN]]*^*accomplishments=[]*^*credibility=[]*^*extracurricular=[]*^*misc=[]*^*";

	@Test
	public void testJobParser() {
		Assert.assertNotNull(jobParser);
	}

	@Test
	public void testGetJob() {
		jobParser.setJobDetails();
		Assert.assertTrue(jobParser.getJob().toString().equalsIgnoreCase(content));
	}

}
