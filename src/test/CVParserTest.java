package test;


import org.junit.Assert;
import org.junit.Test;

import parser.CVParser;
import parser.Parser;

public class CVParserTest {

	private static final String FILENAME = "src/resources/SuranjanaSengupta_Resume.pdf";
	private CVParser cvParser;
	private static String content = "0{skills=[Engineering:[NNP], focus:[NN], Software:[NNP], Engineering:[NNP], Expected:[VBN], date:[NN], graduation:[NN], June:[NNP], 2016:[ANK], Python:[NNP], Ubuntu:[NNP], Environment:[NNP], scaled:[VBN], prototype:[NN], incorporates:[VBZ], localization:[NN], mapping:[NN], features:[NNS], Robot:[NN], using:[VBG], Raspberry:[NN], Pi:[NN], Advanced:[NNP], C:[NN], C:[NN], JAVA:[NN], Excel:[NNP], VBA:[NN], Intermediate:[NNP], Ruby:[NNP], Arduino:[NNP], microcontroller:[NN], Time:[NN], Operating:[NN], Systems:[NNP], RTOS:[NN], Beginner:[NN], ObjectiveC:[NN], Python:[NNP], Web:[NN], Design:[NN], Intermediate:[NNP], CSS:[NN], Javascript:[NN], Bootstrap:[NNP], Coffeescript:[NN], AJAX:[NN], PHP:[NN], SQL:[NN], Software:[NNP], Development:[NNP], Advanced:[NNP], Object:[VB], Oriented:[VBN], Intermediate:[NNP], Software:[NNP], Development:[NNP], Life:[NN], Cycles:[NNS], Agile:[NNP], Scrum:[NNP], Methodologies:[NNS], Xtreme:[NN], Beginner:[NN], Google:[NNP], Testing:[VBG], Framework:[NN], Test:[NN], Driven:[VBN], Development:[NNP], TDD:[NN]]*^*education=[National:[NNP], University:[NNP], Singapore:[NNP], NUS:[NN]]*^*awards=[]*^*work=[Advanced:[NNP], Micro:[NNP], Devices:[NNP], AMD:[NNP], Singapore:[NNP], Software:[NNP], Engineering:[NNP], Intern:[NNP], January:[NNP], June:[NNP], 2015:[ANK], Worked:[VBN], team:[NN], comprising:[VBG], members:[NNS], help:[NN], maintain:[VB], enhance:[VB], System:[NNP], Level:[NN], Test:[NN], SLT:[NN], software:[NN], equipment:[NN], used:[VBN], validating:[VBG], tests:[NNS], quantifying:[VBG], results:[NNS], APUs:[NNS], Programmed:[VBN], Agile:[NNP], environment:[NN], exploring:[VBG], Scrum:[NNP], Methodologies:[NNS], Analyzed:[VBN], user:[NN], requirements:[NNS], issue:[NN], tracking:[NN], JIRA:[NN], validated:[VBN], quality:[NN], attributes:[NNS], using:[VBG], SonarQube:[NN], software:[NN], integration:[NN], using:[VBG], Subversion:[NN], Collaborated:[VBN], AMD:[NNP], Product:[NN], Development:[NNP], Group:[NNP], help:[NN], solve:[VB], defects:[NNS], assemble:[VB], software:[NN], architectures:[NNS], designs:[NNS], patterns:[NNS], testing:[NN], equipment:[NN], Tinkerbox:[NN], Studios:[NNP], Singapore:[NNP], Rails:[NNPS], Developer:[NNP], Intern:[NNP], JuneAugust:[NN], 2014:[ANK], Developed:[VBN], maintained:[VBN], handled:[VBN], integrated:[VBN], system:[NN], testing:[NN], existing:[VBG], web:[NN], application:[NN], Ruby:[NNP], Rails:[NNPS], framework:[NN], Worked:[VBN], team:[NN], comprising:[VBG], members:[NNS], analyze:[VB], user:[NN], requirements:[NNS], constraints:[NNS], deploy:[VB], IOS:[NN], application:[NN], using:[VBG], Rubymotion:[NN], tool:[NN], chain:[NN], Studied:[VBN], Version:[NN], Control:[NN], Github:[NN], validation:[NN], testing:[NN], CircleCI:[NN], code:[NN], quality:[NN], Codeclimate:[NN], Design:[NN], Centric:[NNP], Programme:[NNP], National:[NNP], University:[NNP], Singapore:[NNP], Robotics:[NNS], Team:[NNP], August:[NNP], Studied:[VBN], problem:[NN], solving:[VBG], using:[VBG], design:[NN], principles:[NNS], understand:[VB], product:[NN], life:[NN], cycles:[NNS], develop:[VB], prototype:[NN], address:[NN], given:[VBN], case:[NN], designing:[VBG], guiding:[VBG], vehicle:[NN], combat:[NN], navigation:[NN], difficulties:[NNS], largescale:[NN], supermarkets:[NNS], National:[NNP], University:[NNP], Singapore:[NNP], NUS:[NN], Literary:[NNP], Society:[NNP], VicePublications:[NNS], Executive:[NNP], 20122013:[ANK], NUS:[NN], Indian:[NNP], Dance:[NNP], Ticketing:[VBG], Head:[VB], Treasurer:[NNP], 20142015:[ANK], Worked:[VBN], team:[NN], 13:[ANK], members:[NNS], organize:[VB], stage:[NN], Concert:[NN], Krishna:[NNP], Katha:[NNP], University:[NNP], Centre:[NNP], Exxon:[NNP], Mobil:[NNP], Campus:[NN], Concert:[NN], Shaamiyana:[NN]]*^*profile=[Mobile:[NNP], 65:[ANK], Email:[VB], LinkedIn:[NN]]*^*accomplishments=[]*^*mobile=[94514216:[ANK]]*^*credibility=[]*^*fullname=[SURANJANA:[NN], SENGUPTA:[NN]]*^*extracurricular=[]*^*misc=[]*^*";

	@Test
	public void testCVParser() {
		Parser.setGazetteFilePath("src/gazettes/");
		cvParser = new CVParser(FILENAME);
		Assert.assertNotNull(cvParser);
	}


	@Test
	public void testGetCV() {
		Parser.setGazetteFilePath("src/gazettes/");
		cvParser = new CVParser(FILENAME);
		cvParser.setCVDetails();
		Assert.assertTrue(cvParser.getCV().toString().equalsIgnoreCase(content));
	}

}
