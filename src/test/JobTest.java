package test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import common.Job;
import common.Word;

public class JobTest {
	
	private Job job = new Job();
	private static String content = "0{skills=[C:[NN], Java:[NNP], python:[NN], VHDL:[NN], Photoshop:[NN]]*^*education=[bachelors:[NNS], CS:[NN], CEG:[NN], Location:[NNP], Singapore:[NNP], United:[NNP], States:[NNS]]*^*awards=[]*^*work=[3:[ANK], years:[NNS], developing:[VBG], backend:[NN], medium:[NN], sized:[VBN], web:[NN], app:[NN], helolo:[NN]]*^*profile=[Backend:[NN], developer:[NN]]*^*accomplishments=[]*^*credibility=[]*^*extracurricular=[]*^*misc=[]*^*";
	
	@Test
	public void testJob() {
		Assert.assertNotNull(job);
	}

	@Test
	public void testSetId() {
		int id = 1;
		job.setId(id);
		Assert.assertTrue(id == job.getId());
	}

	@Test
	public void testSetJobContentMap() {
		Map<String, List<Word>> jobContentMap = new HashMap<String, List<Word>>();
		List<Word> tempList = new ArrayList<Word>();
		tempList.add(new Word("java"));
		jobContentMap.put("skills", tempList);
		job.setJobContentMap(jobContentMap);
		Assert.assertEquals(jobContentMap, job.getJobContentMap());
	}


	@Test
	public void testToString() {
		Job tempJob = Job.fromString(content);
		Assert.assertEquals(tempJob.toString(), content);
	}

	@Test
	public void testFromString() {
		Job tempJob = Job.fromString(content);
		Assert.assertEquals(tempJob.getId(), Job.fromString(content).getId());
	}

}
